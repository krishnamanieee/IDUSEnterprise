package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 04-11-2017.
 */

public class Customer  {

    String customerName,phone,address,city,pincode,mapLan,mapLac,remaks,id, user;

    String loanId,cusId,totalLoanAmount,loanOption,loanDuration,startDate,endDate,nextdueDate,nextDueAmount,paidLoan,balanceLoan;
    String   customerImage,shopImage,idProofImage,addressProofImage,refName,refPhone;

    public Customer(String loanId,String cusId){
        this.loanId=loanId;
        this.cusId=cusId;
        this.totalLoanAmount="";
        this.loanOption="";
        this.loanDuration="";
        this.startDate="";
        this.endDate="";
        this.nextdueDate="";
        this.nextDueAmount="";
        this.paidLoan="";
        this.balanceLoan="";



    }

    public Customer(String customerName,String phone, String address, String city, String pincode, String mapLan,
                    String mapLac, String customerImage,String shopImage,String idProofImage,String addressProofImage,
                    String remaks,String refName,String refPhone, String user){
        this.customerName=customerName;
        this.phone=phone;
        this.address=address;
        this.city=city;
        this.pincode=pincode;
        this.mapLan=mapLan;
        this.mapLac=mapLac;
        this.customerImage=customerImage;
        this.shopImage=shopImage;
        this.idProofImage=idProofImage;
        this.addressProofImage=addressProofImage;
        this.remaks=remaks;
        this.refName=refName;
        this.refPhone=refPhone;
        this.user=user;


    }

    public Customer(String id){
        this.id=id;
        this.customerName="";
        this.phone="";
        this.address="";
        this.city="";
        this.pincode="";
        this.mapLan="";
        this.mapLac="";
        this.remaks="";
    }
}
