package com.rohasoft.idus.idus_enterprise;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class ColectLoan_Activity extends AppCompatActivity implements View.OnClickListener {

    Button pay, reset;
    EditText duePaidDate, paidAmount;
    EditText  editText_cusName,editText_cusId,editText_phone,editText_city;
    private SimpleDateFormat dateFormatter;
    String id,CusName,phone,city;

    TextView textView_loanId;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_colectloan);

        pay = (Button) findViewById(R.id.btn_colloan_pay);
        reset = (Button) findViewById(R.id.btn_colloan_reset);

        editText_cusName= (EditText) findViewById(R.id.edit_colLoan_custumname);
        editText_city= (EditText) findViewById(R.id.edit_colLoan_city);
        editText_phone= (EditText) findViewById(R.id.edit_colLoan__phnno);

        textView_loanId= (TextView) findViewById(R.id.txtview_collaon_loanid);


        editText_phone.setEnabled(false);
        editText_phone.setInputType(InputType.TYPE_NULL);
        editText_city.setEnabled(false);
        editText_city.setInputType(InputType.TYPE_NULL);



        duePaidDate = (EditText)findViewById(R.id.edit_colloan_due_paid_date);
        duePaidDate.requestFocus();
        duePaidDate.setInputType(InputType.TYPE_NULL);

        paidAmount  = (EditText) findViewById(R.id.edit_colloan_paid_amount);





        if(getIntent().getExtras().getString("id").length()>0){
            id=getIntent().getExtras().getString("id");
            CusName=getIntent().getExtras().getString("cusName");
            phone=getIntent().getExtras().getString("phone");
            city=getIntent().getExtras().getString("city");


            editText_cusName.setText(CusName);
            editText_city.setText(city);
            editText_phone.setText(phone);
            textView_loanId.setText("LOAN"+id);




        }

        reset();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        getDate();
    }
    private void getDate() {

        duePaidDate.setOnClickListener(this);
        Calendar calendar=Calendar.getInstance();

         datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                duePaidDate.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));



    }

    private void reset() {
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duePaidDate.setText("");
                paidAmount.setText("");
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == duePaidDate) {
            datePickerDialog.show();

        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
