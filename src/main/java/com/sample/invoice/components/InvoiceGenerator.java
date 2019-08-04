package com.sample.invoice.components;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sample.invoice.apimodel.EmployeeBill;
import com.sample.invoice.apimodel.GeneratedInvoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.List;

/**
 * created by julian
 */
@Component
public class InvoiceGenerator {

    public final String DEFAULT_MIME_TYPE = "data:application/pdf;base64,";

    private Logger logger = LoggerFactory.getLogger(InvoiceGenerator.class);

    private DecimalFormat moneyFormat = new DecimalFormat("#,##0.00");

    private String[] headers = new String[]{
            "EmployeeID", "Number of Hours", "Unit Price", "Cost"
    };


    public GeneratedInvoice generateInvoice(String company, List<EmployeeBill> employeeBills) {
        GeneratedInvoice invoice = new GeneratedInvoice(company, employeeBills);

        byte[] invoiceData = generatePdf(invoice);
        invoice.setInvoiceDataEncoded(DEFAULT_MIME_TYPE + new String(Base64.getEncoder().encode(invoiceData)));

        return invoice;
    }


    private byte[] generatePdf(GeneratedInvoice invoice) {
        Document document = new Document();
        ByteArrayOutputStream pdfByteStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, pdfByteStream);

            document.open();

            populateDocument(document, invoice);

            document.close();
            writer.close();
        }
        catch (DocumentException e) {
            logger.error(e.getMessage(), e);
        }

        return pdfByteStream.toByteArray();
    }


    private void populateDocument(Document document, GeneratedInvoice invoice) throws DocumentException {
        document.add(new Paragraph(String.format("Company: %s", invoice.getCompanyName())));

        PdfPTable table = initializeTable();

        writeTableHeader(table);
        writeTableBody(table, invoice);
        writeTableFooter(table, invoice);

        document.add(table);
    }


    private PdfPTable initializeTable() {
        PdfPTable table = new PdfPTable(4);

        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.setWidthPercentage(100);

        return table;
    }


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell;

        for (String header : headers) {
            cell = new PdfPCell(new Paragraph(header));
            cell.setBackgroundColor(BaseColor.BLUE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell);
        }
    }


    private void writeTableBody(PdfPTable table, GeneratedInvoice invoice) {
        List<EmployeeBill> employeeBills = invoice.getEmployeeBills();

        PdfPCell cell;
        for (EmployeeBill employeeBill : employeeBills) {
            cell = new PdfPCell(new Paragraph(String.valueOf(employeeBill.getEmployeeId())));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell);


            cell = new PdfPCell(new Paragraph(String.valueOf(employeeBill.getNumberOfHours())));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell);


            cell = new PdfPCell(new Paragraph(String.format("N %s", moneyFormat.format(employeeBill.getBillableRate()))));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell);


            cell = new PdfPCell(new Paragraph(String.format("N %s", moneyFormat.format(employeeBill.getCost()))));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell);
        }
    }


    private void writeTableFooter(PdfPTable table, GeneratedInvoice invoice) {
        PdfPCell cell = new PdfPCell(new Paragraph(""));
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(""));
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total"));
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(String.format("N %s", moneyFormat.format(invoice.getTotalCost()))));
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(cell);
    }


}
