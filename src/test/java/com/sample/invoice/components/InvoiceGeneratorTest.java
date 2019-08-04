package com.sample.invoice.components;


import com.sample.invoice.apimodel.EmployeeBill;
import com.sample.invoice.apimodel.GeneratedInvoice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * created by julian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceGeneratorTest {


    @Autowired
    private InvoiceGenerator invoiceGenerator;

    private List<EmployeeBill> employeeBills = Arrays.asList(
            new EmployeeBill()
                    .setEmployeeId(1l)
                    .setProject("Flutterwave")
                    .setBillableRate(200)
                    .setDate(LocalDate.of(2019, 5, 4))
                    .setStartTime(LocalTime.of(4, 0))
                    .setEndTime(LocalTime.of(8, 0)),

            new EmployeeBill()
                    .setEmployeeId(6l)
                    .setProject("Flutterwave")
                    .setBillableRate(500)
                    .setDate(LocalDate.of(2019, 7, 4))
                    .setStartTime(LocalTime.of(17, 0))
                    .setEndTime(LocalTime.of(22, 0))
    );


    @Test
    public void testInvoiceGeneration() throws Exception {
        GeneratedInvoice invoice = invoiceGenerator.generateInvoice("Flutterwave", employeeBills);

        Assert.assertNotNull(invoice);
        Assert.assertEquals(
                InvoiceGenerator.DEFAULT_MIME_TYPE,
                invoice.getInvoiceDataEncoded().substring(0, InvoiceGenerator.DEFAULT_MIME_TYPE.length())
        );
        Assert.assertEquals(invoice.getTotalCost(), new BigDecimal(3300));
    }


}
