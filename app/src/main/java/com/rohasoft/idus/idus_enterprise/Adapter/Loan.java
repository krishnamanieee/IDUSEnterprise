package com.rohasoft.idus.idus_enterprise.Adapter;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class Loan {

    String cusName,phone,loanId,loanTot,paidAmount,balAmt,loanOption,loanDurations;
    String startDate,endDate,dueDate,dueAmt;

    public Loan(String cusName, String phone, String loanId, String loanTot, String paidAmount, String balAmt, String loanOption, String loanDurations, String startDate, String endDate, String dueDate, String dueAmt) {
        this.cusName = cusName;
        this.phone = phone;
        this.loanId = loanId;
        this.loanTot = loanTot;
        this.paidAmount = paidAmount;
        this.balAmt = balAmt;
        this.loanOption = loanOption;
        this.loanDurations = loanDurations;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.dueAmt = dueAmt;
    }

    public String getCusName() {
        return cusName;
    }

    public String getPhone() {
        return phone;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getLoanTot() {
        return loanTot;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public String getBalAmt() {
        return balAmt;
    }

    public String getLoanOption() {
        return loanOption;
    }

    public String getLoanDurations() {
        return loanDurations;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDueAmt() {
        return dueAmt;
    }
}
