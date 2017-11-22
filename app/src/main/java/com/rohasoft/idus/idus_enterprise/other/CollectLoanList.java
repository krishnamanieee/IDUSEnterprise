package com.rohasoft.idus.idus_enterprise.other;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class CollectLoanList {


    String CusName,CusId,phone,city,loanId,loanOption,loan_term,totalAmount,paidAmount,balanceAmount,dueDate,dueAmount;
    String   customerImage,shopImage,idProofImage,addressProofImage;


    public CollectLoanList(String cusName, String cusId, String phone, String city, String loanId, String loanOption, String loan_term,String totalAmount, String paidAmount, String balanceAmount, String dueDate, String dueAmount, String customerImage, String shopImage, String idProofImage, String addressProofImage) {
        CusName = cusName;
        CusId = cusId;
        this.phone = phone;
        this.city = city;
        this.loanId = loanId;
        this.loanOption = loanOption;
        this.loan_term = loan_term;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.balanceAmount = balanceAmount;
        this.dueDate = dueDate;
        this.dueAmount = dueAmount;
        this.customerImage = customerImage;
        this.shopImage = shopImage;
        this.idProofImage = idProofImage;
        this.addressProofImage = addressProofImage;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public String getShopImage() {
        return shopImage;
    }

    public String getIdProofImage() {
        return idProofImage;
    }

    public String getAddressProofImage() {
        return addressProofImage;
    }

    public String getCusName() {
        return CusName;
    }

    public String getCusId() {
        return CusId;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public String getLoanOption() {
        return loanOption;
    }

    public String getLoan_term() {
        return loan_term;
    }
}
