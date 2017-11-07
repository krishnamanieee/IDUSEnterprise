package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 07-11-2017.
 */

public class AddLoanCusList {

    private String cusName;
    private String phone;
    private String cusId;
    private String address;



    public AddLoanCusList(String cusName, String phone, String cusId, String address) {
        this.cusName = cusName;
        this.phone = phone;
        this.cusId = cusId;
        this.address = address;    }

    public String getCusName() {
        return cusName;
    }

    public String getPhone() {
        return phone;
    }



    public String getCusId() {
        return cusId;
    }

    public String getAddress() {
        return address;
    }
}


