package com.sample.billablehours.service;


import com.sample.billablehours.apimodel.TimetableUploadResult;
import com.sample.billablehours.components.CSVFileExtractor;
import com.sample.billablehours.components.InvoiceGenerator;
import com.sample.billablehours.exception.ServiceException;
import com.sample.billablehours.service.command.GenerateInvoiceCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * created by julian
 */
@Service
public class TimetableService {

    private final CSVFileExtractor csvFileExtractor;

    private final InvoiceGenerator invoiceGenerator;


    public TimetableService(CSVFileExtractor csvFileExtractor, InvoiceGenerator invoiceGenerator) {
        this.csvFileExtractor = csvFileExtractor;
        this.invoiceGenerator = invoiceGenerator;
    }


    public TimetableUploadResult processUpload(MultipartFile file) throws ServiceException {
        return new GenerateInvoiceCommand(file, csvFileExtractor, invoiceGenerator).execute();
    }


}
