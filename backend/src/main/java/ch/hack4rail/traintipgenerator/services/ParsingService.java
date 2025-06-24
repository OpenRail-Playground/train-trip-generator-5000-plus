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
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParsingService {


    final public <K> List<K> parseCSV(String fileName, Class<K> clazz) throws IOException {

        val stream = new FileInputStream(ResourceUtils.getFile("classpath:data/" + fileName));

        try (Reader reader = new BufferedReader(new InputStreamReader(stream))) {
            // creating the strategy object
            HeaderColumnNameMappingStrategy<K> strategy = new HeaderColumnNameMappingStrategy<>();
            // setting the format of the data representation in the header
            strategy.setType(clazz);
            // Creating instance of CSVTOBEAN class responsable of the mapping
            CsvToBean<K> csvToBean = new CsvToBeanBuilder<K>(reader)
                    // Setting the startegy
                    .withMappingStrategy(strategy)
                    // Ignore empty lines and leading spaces
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            // parse the data into the Objects with type T
            return csvToBean.parse();
        }
    }


}
