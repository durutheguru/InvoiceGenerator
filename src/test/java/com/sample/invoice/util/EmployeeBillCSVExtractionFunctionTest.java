package com.sample.invoice.util;


import com.sample.invoice.apimodel.EmployeeBill;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Optional;

/**
 * created by julian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeBillCSVExtractionFunctionTest {

    private EmployeeBillCSVExtractionFunction function = new EmployeeBillCSVExtractionFunction();


    // tokens that can be converted to an Employee Bill
    String[] validTokens = new String[]{"2", "100", "Facebook", "2019-07-01", "11:00", "16:00"};

    // tokens that cannot be converted to an Employee Bill
    String[][] invalidTokens = new String[][]{
            {""},
            {"","","","","",""},
            {"Employee ID",	"Billable Rate (per hour)",	"Project Date",	"Start Time", "End Time"}
    };


    @Test
    public void testEmployeeBillIsExtractedFromValidTokens() throws Exception {

        Optional<EmployeeBill> billOptional = function.apply(Arrays.asList(validTokens));

        Assert.assertTrue(billOptional.isPresent());

        EmployeeBill bill = billOptional.get();

        Assert.assertEquals(bill.getEmployeeId(), Long.valueOf(2));
        Assert.assertEquals(bill.getBillableRate(), Integer.valueOf(100));
        Assert.assertEquals(bill.getProject(), "Facebook");
        Assert.assertEquals(bill.getDate(), LocalDate.of(2019, Month.JULY, 1));
        Assert.assertEquals(bill.getStartTime(), LocalTime.of(11, 0));
        Assert.assertEquals(bill.getEndTime(), LocalTime.of(16, 0));

    }


    @Test
    public void testExtractionWithInvalidTokens() throws Exception {
        for (String[] tokens : invalidTokens) {
            Assert.assertFalse(function.apply(Arrays.asList(tokens)).isPresent());
        }
    }


}
