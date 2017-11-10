package com.rohasoft.idus.idus_enterprise;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

import com.rohasoft.idus.idus_enterprise.fragment.AddLoanFragment;
import com.rohasoft.idus.idus_enterprise.imageUpload.ConnectionDetector;
import com.rohasoft.idus.idus_enterprise.imageUpload.HttpFileUpload;
import com.rohasoft.idus.idus_enterprise.other.Customer;
import com.rohasoft.idus.idus_enterprise.other.GetCustomerCallBack;
import com.rohasoft.idus.idus_enterprise.other.GetLoanCallBack;
import com.rohasoft.idus.idus_enterprise.other.Loan;
import com.rohasoft.idus.idus_enterprise.other.ServerRequest;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.R.attr.bitmap;
import static java.security.AccessController.getContext;

/**
 * Created by Ayothi selvam on 04-11-2017.
 */

public class AddLoan_Activity extends AppCompatActivity implements OnClickListener{
    EditText edtcustumname, edtcustumid, edtphnno, edtaddr, edtcity, edtpincode, edtloanamount, edtloanduration,
            edtstartdate, edtenddate, edtremarks;

    ImageView imgcustum, imgshop, imgidproof, imgaddrproof;

    Spinner spinloanoption;

    Button btnsubmit,btnreset;


    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;


    private SimpleDateFormat dateFormatter;
    String id,CusName,phone,address,city,pincode,cusImg,shopImg,addressImg,idImg;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_laon);






        edtcustumname = (EditText) findViewById(R.id.edit_txt_custumname);
        edtcustumname.setEnabled(false);
        edtcustumname.setInputType(InputType.TYPE_NULL);

        edtcustumid = (EditText) findViewById(R.id.edit_txt_custumid);
        edtcustumid.setEnabled(false);
        edtcustumid.setInputType(InputType.TYPE_NULL);

        edtphnno = (EditText) findViewById(R.id.edit_addloan_phnno);
        edtphnno.setEnabled(false);
        edtphnno.setInputType(InputType.TYPE_NULL);

        edtaddr = (EditText) findViewById(R.id.edit_addloan_adr1);


        edtcity = (EditText) findViewById(R.id.edit_addloan_city);
        edtcity.setEnabled(false);
        edtcity.setInputType(InputType.TYPE_NULL);

        edtpincode = (EditText) findViewById(R.id.edit_addloan_pincode);
        edtpincode.setEnabled(false);
        edtpincode.setInputType(InputType.TYPE_NULL);

        edtloanamount = (EditText) findViewById(R.id.edit_addloan_loan_amount);

        edtloanduration = (EditText) findViewById(R.id.edit_addloan_loan_duration);
        edtstartdate = (EditText) findViewById(R.id.edit_addloan_start_date);
        edtenddate = (EditText) findViewById(R.id.edit_addloan_end_date);
        edtremarks = (EditText) findViewById(R.id.edit_addloan_remarks);

        edtstartdate.setInputType(InputType.TYPE_NULL);
        edtstartdate.requestFocus();
        edtenddate.setInputType(InputType.TYPE_NULL);



        imgcustum = (ImageView) findViewById(R.id.img_view_custum);
        imgshop = (ImageView) findViewById(R.id.img_view_shop);
         imgidproof = (ImageView) findViewById(R.id.img_view_idproof);
        imgaddrproof = (ImageView) findViewById(R.id.img_view_addrproof);

        btnsubmit = (Button) findViewById(R.id.btn_submit);
        btnreset = (Button) findViewById(R.id.btn_reset);

        edtcustumid.setEnabled(false);
        edtcustumid.setInputType(InputType.TYPE_NULL);

        edtaddr.setEnabled(false);
        edtaddr.setInputType(InputType.TYPE_NULL);

        edtcustumid.setEnabled(false);
        edtcustumid.setInputType(InputType.TYPE_NULL);

        btnreset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();

            }
        });


        spinloanoption = (Spinner) findViewById(R.id.spin_addloan_loan_option);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();


        addLoanOption();

        SaveDataToServer();
        if(getIntent().getExtras().getString("id").length()>0){
            id=getIntent().getExtras().getString("id");
            CusName=getIntent().getExtras().getString("cusName");
            phone=getIntent().getExtras().getString("phone");
            address=getIntent().getExtras().getString("address");
            city=getIntent().getExtras().getString("city");
            pincode=getIntent().getExtras().getString("pincode");
            cusImg=getIntent().getExtras().getString("cusImg");
            shopImg=getIntent().getExtras().getString("shopImg");
            idImg=getIntent().getExtras().getString("idImg");
            addressImg=getIntent().getExtras().getString("addressImg");
            edtcustumname.setText(CusName);
            edtcustumid.setText("CUS"+id);
            edtphnno.setText(phone);
            edtaddr.setText(address);
            edtcity.setText(city);
            edtpincode.setText(pincode);

            Picasso.with(this).load("http://idusmarket.com/loan-app/app/images/"+cusImg).into(imgcustum);
            Picasso.with(this).load("http://idusmarket.com/loan-app/app/images/"+shopImg).into(imgshop);
            Picasso.with(this).load("http://idusmarket.com/loan-app/app/images/"+idImg).into(imgidproof);
            Picasso.with(this).load("http://idusmarket.com/loan-app/app/images/"+addressImg).into(imgaddrproof);
        }



    }




    private void reset() {
        edtcustumname.setText("");
        edtcustumid.setText("");
        edtphnno.setText("");
        edtaddr.setText("");
        edtcity.setText("");
        edtpincode.setText("");
        edtloanamount.setText("");
        edtloanduration.setText("");
        edtstartdate.setText("");
        edtenddate.setText("");
        edtremarks.setText("");

    }



    private void SaveDataToServer() {
        btnsubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String cus_name=edtcustumname.getText().toString().trim();
                String cus_id=edtcustumid.getText().toString().trim();
                String phone=edtphnno.getText().toString().trim();
                String address=edtaddr.getText().toString().trim();
                String city=edtcity.getText().toString().trim();
                String pincode=edtpincode.getText().toString().trim();
                String loan_amt=edtloanamount.getText().toString().trim();
                String loan_opttion=spinloanoption.getSelectedItem().toString();
                String loan_duration=edtloanduration.getText().toString().trim();
                String start_date=edtstartdate.getText().toString().trim();
                String end_date=edtenddate.getText().toString().trim();
                String remarks=edtremarks.getText().toString().trim();


                if (cus_name.length()>0){
                    if (phone.length() == 10){
                        if(pincode.length()==6){
                            if(city.length()>0){

                               SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                Calendar c = Calendar.getInstance();
                                try {
                                    c.setTime(sdf.parse(start_date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (loan_opttion == "Daily"){

                                    c.add(Calendar.DATE, 1 );

                                }
                                else if(loan_opttion == "Weekly"){


                                    c.add(Calendar.DATE, 7);

                                }
                                else if(loan_opttion == "By Weekly"){


                                    c.add(Calendar.DATE, 15);

                                }
                                else if(loan_opttion == "Monthly"){


                                    c.add(Calendar.MONTH, 1);

                                }


                                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                                int totAmt=Integer.parseInt(loan_amt);
                                int totDur=Integer.parseInt(loan_duration);



                                String currentDueDate = sdf1.format(c.getTime());

                                String DueAmount=String.valueOf(totAmt/totDur);






                                Loan loan=new Loan(cus_name,cus_id,phone,address,city,pincode,loan_amt,loan_opttion,loan_duration,start_date,end_date,currentDueDate,DueAmount,remarks,cusImg,shopImg,idImg,addressImg);

                                AddLoan(loan);
                                reset();
                                onBackPressed();

                            }
                            else{
                                edtcity.setError("Please enter the City");
                            }


                        }
                        else {
                            edtpincode.setError("please enter valid pincode");
                        }

                    }
                    else {
                        edtphnno.setError("please enter valid phone no");
                    }
                }

                else {
                    edtcustumname.setError("please select customer name");
                }



            }


        });




    }

    private void AddLoan(Loan loan) {
        ServerRequest serverRequest=new ServerRequest(this);
        serverRequest.storeLoanDataInBackground(loan, new GetLoanCallBack() {
            @Override
            public void done(Loan returedGuser) {
                Toast.makeText(getApplicationContext(),"Insert",Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void setDateTimeField() {
        edtstartdate.setOnClickListener(this);
        edtenddate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                int duration=0;
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edtstartdate.setText(dateFormatter.format(newDate.getTime()));
                String s=edtstartdate.getText().toString();
                //String dt = "2012-01-04";  // Start date
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int laonduration=Integer.parseInt(edtloanduration.getText().toString().trim());
                String loanoption=spinloanoption.getSelectedItem().toString().trim();
                duration= laonduration;
                if (loanoption == "Daily"){

                    c.add(Calendar.DATE, duration );

                }
                else if(loanoption == "Weekly"){

                    duration=laonduration*7;
                    c.add(Calendar.DATE, duration);

                }
                else if(loanoption == "By Weekly"){

                    duration=laonduration*15;
                    c.add(Calendar.DATE, duration);

                }
                else if(loanoption == "Monthly"){

                    duration=laonduration;
                    c.add(Calendar.MONTH, duration);

                }
                else {
                    Toast.makeText(getApplicationContext(),"Select loan option",Toast.LENGTH_SHORT).show();
                }

                // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                String output = sdf1.format(c.getTime());
                edtenddate.setText(output);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edtenddate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void addLoanOption() {
        List<String> list =new ArrayList<String>();
        list.add("Select Option");
        list.add("Daily");
        list.add("Weekly");
        list.add("By Weekly");
        list.add("Monthly");


        ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinloanoption.setAdapter(data);
    }



    @Override
    public void onClick(View view) {
        if(view == edtstartdate) {
            fromDatePickerDialog.show();

        } else if(view == edtenddate) {
           // toDatePickerDialog.show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}
