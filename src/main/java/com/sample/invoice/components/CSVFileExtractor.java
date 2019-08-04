package com.sample.invoice.components;


import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * created by julian
 */
@Component
public class CSVFileExtractor {


    public <T> List<T> extract(MultipartFile file, Function<List<String>, Optional<T>> conversionFunction) throws IOException {
        Assert.notNull(file, "Input File cannot be empty");
        Assert.notNull(conversionFunction, "Row conversion function is required");

        List<T> extracts = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] fileRow;

            Optional<T> extract;
            while ((fileRow = reader.readNext()) != null) {
                fileRow = sanitize(fileRow);

                extract = conversionFunction.apply(Arrays.asList(fileRow));
                extract.ifPresent(extracts::add);
            }
        }

        return extracts;
    }


    private String[] sanitize(String[] strings) {
        if (strings == null) {
            return null;
        }

        String[] result = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            result[i] = strings[i].trim()
                    .replace("\n", "")
                    .replace("\t", "")
                    .replace("'", "")
                    .replace("’", "");
        }

        return result;
    }


}
