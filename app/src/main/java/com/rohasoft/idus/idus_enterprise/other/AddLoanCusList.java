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
    private String cusImag;
    private String shopImg;
    private String idImg;
    private String addressImg;

    private String latMap;
    private String lanMap;

    public AddLoanCusList(String cusName, String phone, String cusId, String address, String city, String pincode, String cusImag, String shopImg, String idImg, String addressImg, String latMap, String lanMap) {
        this.cusName = cusName;
        this.phone = phone;
        this.cusId = cusId;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.cusImag = cusImag;
        this.shopImg = shopImg;
        this.idImg = idImg;
        this.addressImg = addressImg;
        this.latMap = latMap;
        this.lanMap = lanMap;
    }

    public AddLoanCusList(String cusName, String phone, String cusId, String city) {
        this.cusName = cusName;
        this.phone = phone;
        this.cusId = cusId;
        this.address = "";
        this.city=city;
        this.pincode="";
        this.cusImag = "";
        this.shopImg = "";
        this.idImg = "";
        this.addressImg = "";
        this.latMap = "";
        this.lanMap = "";
    }

    public String getLatMap() {
        return latMap;
    }

    public String getLanMap() {
        return lanMap;
    }

    public String getCusImag() {
        return cusImag;
    }

    public String getShopImg() {
        return shopImg;
    }

    public String getIdImg() {
        return idImg;
    }

    public String getAddressImg() {
        return addressImg;
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


