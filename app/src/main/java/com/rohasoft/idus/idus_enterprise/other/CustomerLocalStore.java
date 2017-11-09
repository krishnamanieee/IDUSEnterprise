package com.rohasoft.idus.idus_enterprise.other;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class CustomerLocalStore {
    public  static final String SP_NAME="loandetailsDetails";
    SharedPreferences userlocalDatabase;

    public CustomerLocalStore(Context context) {
        userlocalDatabase=context.getSharedPreferences(SP_NAME,0);
    }


}
