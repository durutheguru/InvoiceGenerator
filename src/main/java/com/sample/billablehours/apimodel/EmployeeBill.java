package com.sample.billablehours.apimodel;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * created by julian
 */
public class EmployeeBill {

    private Long employeeId;

    private Integer billableRate;

    private String project;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer numberOfHours;

    private BigDecimal cost;


    public Long getEmployeeId() {
        return employeeId;
    }

    public EmployeeBill setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Integer getBillableRate() {
        return billableRate;
    }

    public EmployeeBill setBillableRate(Integer billableRate) {
        this.billableRate = billableRate;
        return this;
    }

    public String getProject() {
        return project;
    }

    public EmployeeBill setProject(String project) {
        this.project = project;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public EmployeeBill setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public EmployeeBill setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public EmployeeBill setEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public EmployeeBill compute() {
        this.numberOfHours = (endTime.toSecondOfDay() - startTime.toSecondOfDay()) / (60 * 60);
        this.cost = new BigDecimal(this.numberOfHours * billableRate);

        return this;
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public BigDecimal getCost() {
        return cost;
    }


}
