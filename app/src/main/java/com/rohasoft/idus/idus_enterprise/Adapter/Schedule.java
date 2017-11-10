package com.rohasoft.idus.idus_enterprise.Adapter;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class Schedule {

    String dueDate,payAmount,balanceAmount;

    public Schedule(String dueDate, String payAmount, String balanceAmount) {
        this.dueDate = dueDate;
        this.payAmount = payAmount;
        this.balanceAmount = balanceAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }
}
