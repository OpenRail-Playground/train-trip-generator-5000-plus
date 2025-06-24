package ch.hack4rail.traintripgenerator.services;


import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.opencsv.enums.CSVReaderNullFieldIndicator.BOTH;

@Service
@RequiredArgsConstructor
public class ParsingService {

    public <K> List<K> parseCSV(String fileName, Class<K> clazz) throws IOException {
        val file = ResourceUtils.getFile("classpath:data/" + fileName);
        try (InputStream stream = new FileInputStream(file)) {
            HeaderColumnNameMappingStrategy<K> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);
            val reader = getBomLessReader(stream, file);
            val csvToBean = new CsvToBeanBuilder<K>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
    }

    private Reader getBomLessReader(InputStream stream, File file) throws IOException {
        byte[] bom = new byte[3];
        int n = stream.read(bom, 0, bom.length);

        if (n == 3 && (bom[0] & 0xFF) == 0xEF && (bom[1] & 0xFF) == 0xBB && (bom[2] & 0xFF) == 0xBF) {
            return new InputStreamReader(stream, StandardCharsets.UTF_8);
        }
        return new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
    }


}
