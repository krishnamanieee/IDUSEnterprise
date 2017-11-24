package com.rohasoft.idus.idus_enterprise.Adapter;

/**
 * Created by Ayothi selvam on 11/22/2017.
 */

public class Test {

    String t1,t2,t3;
    int t4;

    public Test(String t1, String t2, String t3) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }
    public Test(int t1, String t2, String t3) {
        this.t4 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    public String getT3() {
        return t3;
    }
}
