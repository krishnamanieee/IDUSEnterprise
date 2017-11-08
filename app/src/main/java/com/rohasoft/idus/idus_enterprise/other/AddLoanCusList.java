package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 07-11-2017.
 */

public class AddLoanCusList {

    private String cusName;
    private String phone;
    private String cusId;
    private String address;
    private String city;
    private String pincode;



    public AddLoanCusList(String cusName, String phone, String cusId, String address,String city,String pincode) {
        this.cusName = cusName;
        this.phone = phone;
        this.cusId = cusId;
        this.address = address;
        this.city=city;
        this.pincode=pincode;
    }
    public AddLoanCusList(String cusName, String phone, String cusId, String city) {
        this.cusName = cusName;
        this.phone = phone;
        this.cusId = cusId;
        this.address = "";
        this.city=city;
        this.pincode="";
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

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


