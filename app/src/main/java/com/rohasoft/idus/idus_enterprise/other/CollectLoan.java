package com.rohasoft.idus.idus_enterprise.other;

import android.widget.Button;

/**
 * Created by Ayothi selvam on 04-11-2017.
 */

public class CollectLoan {
    String customerName,phone,city,loanId,totalAmount,paidAmount,balanceAmount,dueDate,dueAmount,duePaidDate,duePaidAmount,status,cusImg;

    public CollectLoan(String customerName, String phone, String city, String loanId, String totalAmount, String paidAmount, String balanceAmount, String dueDate, String dueAmount, String duePaidDate, String duePaidAmount, String status, String cusImg) {
        this.customerName = customerName;
        this.phone = phone;
        this.city = city;
        this.loanId = loanId;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.balanceAmount = balanceAmount;
        this.dueDate = dueDate;
        this.dueAmount = dueAmount;
        this.duePaidDate = duePaidDate;
        this.duePaidAmount = duePaidAmount;
        this.status = status;
        this.cusImg = cusImg;
    }
}
