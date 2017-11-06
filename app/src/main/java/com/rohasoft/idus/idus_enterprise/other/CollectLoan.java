package com.rohasoft.idus.idus_enterprise.other;

import android.widget.Button;

/**
 * Created by Ayothi selvam on 04-11-2017.
 */

public class CollectLoan {
    String customerName,loanId,phone,city,total_amount,paid_amount,balance_amount,due_date,due_amount,due_paid_date,due_paid_amount;



    public CollectLoan(String customerName,String loanId,String phone,String city,String total_amount,String paid_amount,String balance_amount,String due_date,String due_amount,String due_paid_date,String due_paid_amount){

        this.customerName=customerName;
        this.loanId=loanId;
        this.phone=phone;
        this.city=city;
        this.total_amount=total_amount;
        this.paid_amount=paid_amount;
        this.balance_amount=balance_amount;
        this.due_date=due_date;
        this.due_amount=due_amount;
        this.due_paid_date=due_paid_date;
        this.due_paid_amount=due_paid_amount;


    }
}
