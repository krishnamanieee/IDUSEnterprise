package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class CollectLoanList {


    String CusName,CusId,phone,city,loanId,loanOption,totalAmount,paidAmount,balanceAmount,dueDate,dueAmount;



    public CollectLoanList(String cusName, String cusId, String phone, String city, String loanId, String loanOption, String totalAmount, String paidAmount, String balanceAmount, String dueDate, String dueAmount) {
        CusName = cusName;
        CusId = cusId;
        this.phone = phone;
        this.city = city;
        this.loanId = loanId;
        this.loanOption=loanOption;

        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.balanceAmount = balanceAmount;
        this.dueDate = dueDate;
        this.dueAmount = dueAmount;
    }

    public String getCusName() {
        return CusName;
    }

    public String getCusId() {
        return CusId;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public String getLoanOption() {
        return loanOption;
    }
}
