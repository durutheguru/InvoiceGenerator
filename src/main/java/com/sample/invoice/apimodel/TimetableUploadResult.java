package com.sample.invoice.apimodel;


import java.util.List;

/**
 * created by julian
 */
public class TimetableUploadResult {


    private List<GeneratedInvoice> generatedInvoices;


    public TimetableUploadResult(List<GeneratedInvoice> generatedInvoices) {
        this.generatedInvoices = generatedInvoices;
    }


    public List<GeneratedInvoice> getGeneratedInvoices() {
        return generatedInvoices;
    }


}
