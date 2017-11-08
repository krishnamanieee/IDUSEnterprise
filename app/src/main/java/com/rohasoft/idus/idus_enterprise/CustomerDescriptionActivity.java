package com.rohasoft.idus.idus_enterprise;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class CustomerDescriptionActivity extends AppCompatActivity{

    TextView textView_cusName,textView_cusId,textView_address,textView_city,textView_phone,textView_pincode;

    TextView textView_view_schedule;

    String id,CusName,phone,address,city,pincode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_customer_view);

        textView_cusName=(TextView) findViewById(R.id.txt_cusview_name);
        textView_cusId=(TextView) findViewById(R.id.txt_cusview_cusid);
        textView_city=(TextView) findViewById(R.id.txt_cusview_city);
        textView_pincode=(TextView) findViewById(R.id.txt_cusview_pincode);
        textView_phone=(TextView) findViewById(R.id.txt_cusview_phone);
        textView_address=(TextView) findViewById(R.id.txt_cusview_addr);
        textView_view_schedule=(TextView) findViewById(R.id.txt_cusview_viewschedule);

        if(getIntent().getExtras().getString("id").length()>0){
            id=getIntent().getExtras().getString("id");
            CusName=getIntent().getExtras().getString("cusName");
            phone=getIntent().getExtras().getString("phone");
            address=getIntent().getExtras().getString("address");
            city=getIntent().getExtras().getString("city");
            pincode=getIntent().getExtras().getString("pincode");

            textView_cusName.setText(CusName);
            textView_cusId.setText("CUS"+id);
            textView_phone.setText(phone);
            textView_address.setText(address);
            textView_city.setText(city);
            textView_pincode.setText(pincode);




        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
