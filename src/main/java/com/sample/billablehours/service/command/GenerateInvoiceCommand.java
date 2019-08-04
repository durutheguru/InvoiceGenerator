package com.sample.billablehours.service.command;


import com.sample.billablehours.apimodel.EmployeeBill;
import com.sample.billablehours.apimodel.GeneratedInvoice;
import com.sample.billablehours.apimodel.TimetableUploadResult;
import com.sample.billablehours.components.CSVFileExtractor;
import com.sample.billablehours.components.InvoiceGenerator;
import com.sample.billablehours.exception.ServiceException;
import com.sample.billablehours.util.EmployeeBillCSVExtractionFunction;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
public class GenerateInvoiceCommand implements Command<TimetableUploadResult> {

    public final MultipartFile multipartFile;

    public CSVFileExtractor fileExtractor;

    public InvoiceGenerator invoiceGenerator;

    private List<GeneratedInvoice> generatedInvoices;


    public GenerateInvoiceCommand(MultipartFile multipartFile, CSVFileExtractor fileExtractor, InvoiceGenerator invoiceGenerator) {
        this.multipartFile = multipartFile;
        this.fileExtractor = fileExtractor;
        this.invoiceGenerator = invoiceGenerator;

        this.generatedInvoices = new ArrayList<>();
    }


    @Override
    public TimetableUploadResult execute() throws ServiceException {
        try {
            List<EmployeeBill> employeeBills = fileExtractor.extract(multipartFile, new EmployeeBillCSVExtractionFunction());

            employeeBills.stream()
                    .collect(Collectors.groupingBy(EmployeeBill::getProject))
                    .forEach((project, bills) -> generatedInvoices.add(invoiceGenerator.generateInvoice(project, bills)));

            return new TimetableUploadResult(generatedInvoices);
        }
        catch (IOException e) {
            throw new ServiceException("Error occurred while parsing uploaded file");
        }
    }


}
