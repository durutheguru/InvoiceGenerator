package com.sample.invoice.apimodel;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

/**
 * created by julian
 */
public class GeneratedInvoice {

    private String companyName;

    @JsonIgnore
    private List<EmployeeBill> employeeBills;

    private BigDecimal totalCost;

    private String invoiceDataEncoded;

    public GeneratedInvoice() {
    }

    public GeneratedInvoice(String companyName, List<EmployeeBill> employeeBills) {
        this.companyName = companyName;
        this.employeeBills = employeeBills;

        initialize();
    }


    public String getCompanyName() {
        return companyName;
    }

    public GeneratedInvoice setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public List<EmployeeBill> getEmployeeBills() {
        return employeeBills;
    }

    public GeneratedInvoice setEmployeeBills(List<EmployeeBill> employeeBills) {
        this.employeeBills = employeeBills;
        return this;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public GeneratedInvoice setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public String getInvoiceDataEncoded() {
        return invoiceDataEncoded;
    }

    public GeneratedInvoice setInvoiceDataEncoded(String invoiceDataEncoded) {
        this.invoiceDataEncoded = invoiceDataEncoded;
        return this;
    }


    private void initialize() {
        if (employeeBills == null || employeeBills.isEmpty()) {
            return;
        }

        totalCost = new BigDecimal(0);
        employeeBills.forEach(e -> totalCost = totalCost.add(e.compute().getCost()));
    }


}
