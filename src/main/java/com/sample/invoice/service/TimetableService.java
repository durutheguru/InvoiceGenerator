package com.sample.invoice.service;


import com.sample.invoice.apimodel.TimetableUploadResult;
import com.sample.invoice.components.CSVFileExtractor;
import com.sample.invoice.components.InvoiceGenerator;
import com.sample.invoice.exception.ServiceException;
import com.sample.invoice.service.command.GenerateInvoiceCommand;
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
