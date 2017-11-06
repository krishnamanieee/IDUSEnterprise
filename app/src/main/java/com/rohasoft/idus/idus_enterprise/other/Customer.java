package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 04-11-2017.
 */

public class Customer  {

    String customerName,phone,address,city,pincode,mapLan,mapLac,remaks;

    public Customer(String customerName,String phone, String address, String city, String pincode, String mapLan, String mapLac, String remaks){
        this.customerName=customerName;
        this.phone=phone;
        this.address=address;
        this.city=city;
        this.pincode=pincode;
        this.mapLan=mapLan;
        this.mapLac=mapLac;
        this.remaks=remaks;

    }
}
