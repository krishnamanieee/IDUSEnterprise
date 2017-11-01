package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 30-10-2017.
 */

public class User {
    String name, email,phone_no, pass,otp;




    public  User(String name,String email,String phone_no,String pass, String otp){
        this.name=name;
        this.email=email;
        this.phone_no=phone_no;
        this.pass=pass;
        this.otp=otp;

    }

    public  User(String email,String pass){
        this.name="";
        this.email=email;
        this.phone_no="";
        this.pass=pass;
        this.otp="";


    }

}
