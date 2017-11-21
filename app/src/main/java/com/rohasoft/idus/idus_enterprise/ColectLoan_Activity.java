package com.rohasoft.idus.idus_enterprise;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.Adapter.ExistingCustomer;
import com.rohasoft.idus.idus_enterprise.other.AddLoanCusList;
import com.rohasoft.idus.idus_enterprise.other.CollectLoan;
import com.rohasoft.idus.idus_enterprise.other.GetCollectLoanCallBack;
import com.rohasoft.idus.idus_enterprise.other.ServerRequest;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class ColectLoan_Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL_DATA="http://idusmarket.com/loan-app/app/rating.php";

    Button pay, reset;
    EditText duePaidDate, editText_paidAmount;
    EditText  editText_cusName,editText_phone,editText_city;
    private SimpleDateFormat dateFormatter;
    String id,CusName,phone,city,loanid,totAmt,padiAmt,balAmt,nextDueDate,nextDueAmount,loanOption, status="Active",
            cusImg, loanRating;
    int rating, result;

    long diffentbwDate=0;

    TextView textView_loanId,textView_totalAmount,textView_paidAmount,textView_balanceAmount,textView_dueDate, textView_dueAmount,textView_balAmount;
    DatePickerDialog datePickerDialog;

    AlertDialog alertDialog;

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
        textView_totalAmount= (TextView) findViewById(R.id.txtview_collaon_totamount);
        textView_paidAmount= (TextView) findViewById(R.id.txtview_collaon_paid_amount);
        textView_balanceAmount= (TextView) findViewById(R.id.txtview_collaon_balance_amount);
        textView_dueDate= (TextView) findViewById(R.id.txtview_collaon_due_date);
        textView_dueAmount= (TextView) findViewById(R.id.txtview_collaon_due_amount);


        editText_phone.setEnabled(false);
        editText_phone.setInputType(InputType.TYPE_NULL);
        editText_city.setEnabled(false);
        editText_city.setInputType(InputType.TYPE_NULL);





        duePaidDate = (EditText)findViewById(R.id.edit_colloan_due_paid_date);
        duePaidDate.requestFocus();
        duePaidDate.setInputType(InputType.TYPE_NULL);

        editText_paidAmount  = (EditText) findViewById(R.id.edit_colloan_paid_amount);

        if(getIntent().getExtras().getString("id").length()>0){
            loanid=getIntent().getExtras().getString("id");
            CusName=getIntent().getExtras().getString("cusName");
            phone=getIntent().getExtras().getString("phone");
            city=getIntent().getExtras().getString("city");
            totAmt=getIntent().getExtras().getString("totalAmount");
            loanOption=getIntent().getExtras().getString("loanOption");
            id=getIntent().getExtras().getString("totalAmount");
            padiAmt=getIntent().getExtras().getString("paidAmount");
            balAmt =getIntent().getExtras().getString("balanceAmount");
            nextDueDate=getIntent().getExtras().getString("NextdueDate");
            nextDueAmount=getIntent().getExtras().getString("NextdueAmount");
            cusImg=getIntent().getExtras().getString("cusImg");


            editText_cusName.setText(CusName);
            editText_city.setText(city);
            editText_phone.setText(phone);
            textView_loanId.setText("LOAN"+loanid);
            textView_totalAmount.setText(totAmt);
            textView_paidAmount.setText(padiAmt);
            textView_balanceAmount.setText(balAmt);
            textView_dueDate.setText(nextDueDate);
            textView_dueAmount.setText(nextDueAmount);

        }

        reset();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        getDate();

        pay();

    }

    private void pay() {

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String cusName=editText_cusName.getText().toString();
                String phone=editText_phone.getText().toString();
                String city=editText_city.getText().toString();
                String loanId=loanid;
                String totalAmount=textView_totalAmount.getText().toString();

                String dueAmount=textView_dueAmount.getText().toString();
                String paidDueDate=duePaidDate.getText().toString();
                String paidDueAmount=editText_paidAmount.getText().toString();

                int tempPaidAmount=Integer.parseInt(textView_paidAmount.getText().toString());
                int tempBalanceAmount=Integer.parseInt(textView_balanceAmount.getText() .toString());
                int tempPaidDueAmount=Integer.parseInt(editText_paidAmount.getText().toString());
                String paidAmount= String.valueOf(tempPaidAmount+tempPaidDueAmount);
                result=tempBalanceAmount - tempPaidDueAmount;
                String balanceAmount=String.valueOf(result);

                if (result <= 50  ){
                    status = "Deactive";

                    try {
                        Date tempDueDate=dateFormatter.parse(paidDueDate);
                        Date tempCurrentDate=dateFormatter.parse(nextDueDate);
                        long diff=tempDueDate.getTime() - tempCurrentDate.getTime();
                        diffentbwDate=diff / (24 * 60 * 60 * 1000);
                        if (diffentbwDate <=0){
                            rating=5;
                        }
                        else if(diffentbwDate <= 5 && diffentbwDate >0 ){
                            rating=4;
                        }
                        else if (diffentbwDate <=10 && diffentbwDate >5){
                            rating=3;
                        }
                        else if (diffentbwDate <=20 && diffentbwDate >10){
                            rating=2;
                        }
                        else if (diffentbwDate <=30 && diffentbwDate >20){
                            rating=1;
                        }
                        else {
                            rating=0;
                        }

                        loanRating=String.valueOf(rating);

                       // Toast.makeText(getApplicationContext(),""+rating,Toast.LENGTH_SHORT).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    loanRating="";
                }

                Toast.makeText(getApplicationContext(),loanRating,Toast.LENGTH_SHORT).show();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(textView_dueDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (loanOption.equals("Daily")){

                    c.add(Calendar.DATE, 1 );
                }
                else if(loanOption.equals("Weekly")){
                    c.add(Calendar.DATE, 7);
                }
                else if(loanOption.equals("By Weekly")){
                    c.add(Calendar.DATE, 15);
                }
                else if(loanOption.equals("Monthly")){
                    c.add(Calendar.MONTH, 1);
                }
                else {
                    Toast.makeText(getApplicationContext(),"not ",Toast.LENGTH_LONG).show();
                }
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                String dueDate = sdf1.format(c.getTime());
                UserLocalStore userLocalStore=new UserLocalStore(getApplicationContext());
                String user=userLocalStore.getLoggedInUser();

                if (editText_paidAmount.length() >0){

                    if (duePaidDate.length() > 0){

                        if (tempBalanceAmount>= tempPaidDueAmount){

                            CollectLoan collectLoan=new CollectLoan(cusName,phone,city,loanId,loanOption,totalAmount,paidAmount,balanceAmount,dueDate,dueAmount,paidDueDate,paidDueAmount,status,cusImg,user,loanRating);
                            AddDataToSerever(collectLoan);
                            Toast.makeText(getApplicationContext(),"Collections are success",Toast.LENGTH_SHORT).show();
                           /* if (result <= 50  ) {
                                addRatingCus();
                            }*/



                        }else {
                            AlertDialog alertDialog = new AlertDialog.Builder(ColectLoan_Activity.this).create();
                            alertDialog.setTitle("Alert Dialog");
                            alertDialog.setMessage("Paid amount greater then balance Amount ");
                            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        }
                    }
                    else {
                        duePaidDate.setError("Pic the Date");
                    }
                }
                else {
                    editText_paidAmount.setError("Enter the Collected Amount");
                }
            }
        });
    }

    private void addRatingCus() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                UserLocalStore userLocalStore=new UserLocalStore(getApplicationContext());
                String s=userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                String phone=editText_phone.getText().toString();
                // Adding All values to Params.
                params.put("phone", phone);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void AddDataToSerever(CollectLoan collectLoan) {

        ServerRequest serverRequest=new ServerRequest(this);

        serverRequest.storeCollectDataInBackground(collectLoan, new GetCollectLoanCallBack() {
            @Override
            public void done(CollectLoan returnCollectLoan) {
                onBackPressed();
                if (returnCollectLoan != null){

                }
            }
        });
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
                editText_paidAmount.setText("");
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
