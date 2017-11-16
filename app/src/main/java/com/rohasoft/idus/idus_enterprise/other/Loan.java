package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 02-11-2017.
 */

public class Loan {

    String customer_name,customer_id,phone,address,city,pincode,loan_amount,loan_option,loan_duration,
            start_date,end_date,remarks,current_due_date,current_due_amount,cusImg,shoImg,idImg,addressImg,user ;

    public Loan(String customer_name, String customer_id, String phone, String address, String city, String pincode, String loan_amount,
                String loan_option, String loan_duration, String start_date, String end_date, String remarks, String current_due_date,
                String current_due_amount, String cusImg, String shoImg, String idImg, String addressImg, String user) {
        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.loan_amount = loan_amount;
        this.loan_option = loan_option;
        this.loan_duration = loan_duration;
        this.start_date = start_date;
        this.end_date = end_date;
        this.remarks = remarks;
        this.current_due_date = current_due_date;
        this.current_due_amount = current_due_amount;
        this.cusImg = cusImg;
        this.shoImg = shoImg;
        this.idImg = idImg;
        this.addressImg = addressImg;
        this.user = user;

    }
}
