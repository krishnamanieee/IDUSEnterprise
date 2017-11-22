package com.rohasoft.idus.idus_enterprise.other;

import android.widget.Button;

/**
 * Created by Ayothi selvam on 04-11-2017.
 */

public class CollectLoan {
    String customerName,phone,city,loanId,loanOption,loanTrem,totalAmount,paidAmount,balanceAmount,dueDate,
            dueAmount,duePaidDate,duePaidAmount,status,cusImg,user,rating,pendingAmt,extraAmt;

    public CollectLoan(String customerName, String phone, String city, String loanId,String loanOption,String loanTrem,
                       String totalAmount, String paidAmount, String balanceAmount, String dueDate,
                       String dueAmount, String duePaidDate, String duePaidAmount, String status,
                       String cusImg, String user, String rating,String pendingAmt,String extraAmt) {
        this.customerName = customerName;
        this.phone = phone;
        this.city = city;
        this.loanId = loanId;
        this.loanOption = loanOption;
        this.loanTrem = loanTrem;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.balanceAmount = balanceAmount;
        this.dueDate = dueDate;
        this.dueAmount = dueAmount;
        this.duePaidDate = duePaidDate;
        this.duePaidAmount = duePaidAmount;
        this.status = status;
        this.cusImg = cusImg;
        this.user = user;
        this.rating = rating;
        this.pendingAmt = pendingAmt;
        this.extraAmt = extraAmt;
    }
}
