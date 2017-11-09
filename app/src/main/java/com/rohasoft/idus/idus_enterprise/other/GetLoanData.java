package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class GetLoanData {

    String loanId,cusId,totalLoanAmount,loanOption,loanDuration,startDate,endDate,nextdueDate,nextDueAmount,paidLoan,balanceLoan;


    public GetLoanData(String loanId, String cusId, String totalLoanAmount, String loanOption, String loanDuration, String startDate, String endDate, String nextdueDate, String nextDueAmount, String paidLoan, String balanceLoan) {
        this.loanId = loanId;
        this.cusId = cusId;
        this.totalLoanAmount = totalLoanAmount;
        this.loanOption = loanOption;
        this.loanDuration = loanDuration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nextdueDate = nextdueDate;
        this.nextDueAmount = nextDueAmount;
        this.paidLoan = paidLoan;
        this.balanceLoan = balanceLoan;
    }

    public GetLoanData(String cusId) {
        this.cusId = cusId;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getCusId() {
        return cusId;
    }

    public String getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public String getLoanOption() {
        return loanOption;
    }

    public String getLoanDuration() {
        return loanDuration;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getNextdueDate() {
        return nextdueDate;
    }

    public String getNextDueAmount() {
        return nextDueAmount;
    }

    public String getPaidLoan() {
        return paidLoan;
    }

    public String getBalanceLoan() {
        return balanceLoan;
    }
}
