package com.rohasoft.idus.idus_enterprise.other;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ayothi selvam on 30-10-2017.
 */

public class UserLocalStore {
    public  static final String SP_NAME="userDetails";
    SharedPreferences userlocalDatabase;

    public UserLocalStore(Context context){
        userlocalDatabase=context.getSharedPreferences(SP_NAME,0);
    }
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor=userlocalDatabase.edit();
        spEditor.putString("name",user.name);
        spEditor.putString("email",user.email);
        spEditor.putString("phone_no",user.phone_no);
        spEditor.putString("pass",user.pass);
        spEditor.putString("otp",user.otp);

        spEditor.commit();
    }
    public User getLoggedInUser(){
        String name=userlocalDatabase.getString("name","");
        String email=userlocalDatabase.getString("email","");
        String phone_no=userlocalDatabase.getString("phone_no","");
        String pass=userlocalDatabase.getString("pass","");
        String otp=userlocalDatabase.getString("otp","");


        User storedUser=new User(name,email,phone_no,pass,otp);
        return  storedUser;
    }
    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor=userlocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userlocalDatabase.getBoolean("loggedIn",false) == true ){
            return  true;
        }
        else {
            return false;
        }
    }
    public  void clearUserData(){
        SharedPreferences.Editor spEditor=userlocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }


}
