package com.rohasoft.idus.idus_enterprise.Adapter;

/**
 * Created by Ayothi selvam on 11/23/2017.
 */

public class PayDetails {

    String totAmt,tot_paidAmt,tot_balAmt,LoanAmt,loanTerm,paidDate,paidAmt,pending,extra;

    public PayDetails(String totAmt, String tot_paidAmt, String tot_balAmt, String loanAmt, String loanTerm, String paidDate, String paidAmt, String pending, String extra) {
        this.totAmt = totAmt;
        this.tot_paidAmt = tot_paidAmt;
        this.tot_balAmt = tot_balAmt;
        LoanAmt = loanAmt;
        this.loanTerm = loanTerm;
        this.paidDate = paidDate;
        this.paidAmt = paidAmt;
        this.pending = pending;
        this.extra = extra;
    }

    public String getTotAmt() {
        return totAmt;
    }

    public String getTot_paidAmt() {
        return tot_paidAmt;
    }

    public String getTot_balAmt() {
        return tot_balAmt;
    }

    public String getLoanAmt() {
        return LoanAmt;
    }

    public String getLoanTerm() {
        return loanTerm;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public String getPaidAmt() {
        return paidAmt;
    }

    public String getPending() {
        return pending;
    }

    public String getExtra() {
        return extra;
    }
}
