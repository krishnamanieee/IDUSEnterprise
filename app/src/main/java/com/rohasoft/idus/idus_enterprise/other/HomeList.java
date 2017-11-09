package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class HomeList {

    String cusName,city,phone,duePayDate,amount;

    public String getCusName() {
        return cusName;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getDuePayDate() {
        return duePayDate;
    }

    public String getAmount() {
        return amount;
    }

    public HomeList(String cusName, String city, String phone, String duePayDate, String amount) {

        this.cusName = cusName;
        this.city = city;
        this.phone = phone;
        this.duePayDate = duePayDate;
        this.amount = amount;
    }
}
