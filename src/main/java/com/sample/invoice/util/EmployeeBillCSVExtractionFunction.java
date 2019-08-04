package com.sample.invoice.util;


import com.sample.invoice.apimodel.EmployeeBill;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


/**
 * created by julian
 */
public class EmployeeBillCSVExtractionFunction implements Function<List<String>, Optional<EmployeeBill>> {

    private final static int TOKEN_LIST_SIZE = 6;

    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


    @Override
    public Optional<EmployeeBill> apply(List<String> tokens) {
        if (!validTokenArgs(tokens)) {
            return Optional.empty();
        }
        else {
            return Optional.of(
                    new EmployeeBill()
                            .setEmployeeId(Long.valueOf(tokens.get(0)))
                            .setBillableRate(Integer.valueOf(tokens.get(1)))
                            .setProject(tokens.get(2))
                            .setDate(LocalDate.parse(tokens.get(3), dateFormatter))
                            .setStartTime(LocalTime.parse(tokens.get(4), timeFormatter))
                            .setEndTime(LocalTime.parse(tokens.get(5), timeFormatter))
            );
        }
    }


    public boolean validTokenArgs(List<String> tokens) {
        return tokens != null && tokens.size() >= TOKEN_LIST_SIZE && StringUtils.isNumeric(tokens.get(0));
    }


}
