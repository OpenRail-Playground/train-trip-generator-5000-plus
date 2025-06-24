package ch.hack4rail.traintipgenerator.services;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParsingService {


    final public <K> List<K> parseCSV(String fileName, Class<K> clazz) throws IOException {
        val path = ResourceUtils.getFile("classpath:data/" + fileName);
        try (InputStream stream = new FileInputStream(path)) {

            byte[] bom = new byte[3];
            int n = stream.read(bom, 0, bom.length);

            Reader reader;
            if (n == 3 && (bom[0] & 0xFF) == 0xEF && (bom[1] & 0xFF) == 0xBB && (bom[2] & 0xFF) == 0xBF) {
                reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            } else {
                reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            }

            HeaderColumnNameMappingStrategy<K> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);
            CsvToBean<K> csvToBean = new CsvToBeanBuilder<K>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
    }


}
