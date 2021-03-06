package com.rohasoft.idus.idus_enterprise;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.rohasoft.idus.idus_enterprise.other.CollectLoan;
import com.rohasoft.idus.idus_enterprise.other.GetCollectLoanCallBack;
import com.rohasoft.idus.idus_enterprise.other.ServerRequest;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class ColectLoan_Activity extends AppCompatActivity implements View.OnClickListener {

    //private static final String URL_DATA="http://idusmarket.loan-app/app/rating.php";

    Button pay, reset;
    EditText duePaidDate, editText_paidAmount;
    EditText  editText_cusName,editText_phone,editText_city;
    private SimpleDateFormat dateFormatter;
    String id,CusName,phone,city,loanid,totAmt,padiAmt,balAmt,nextDueDate,nextDueAmount,loanOption, status="Active",
            cusImg, loanRating,loanTerm;
    int rating, result, tempLoanTerm,pendingAmt,extraAmt,tempPaidAmount,tempBalanceAmount,tempPaidDueAmount,tempsubAmt;

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
            loanTerm=getIntent().getExtras().getString("loanTerm");
            id=getIntent().getExtras().getString("totalAmount");
            padiAmt=getIntent().getExtras().getString("paidAmount");
            balAmt =getIntent().getExtras().getString("balanceAmount");
            nextDueDate=getIntent().getExtras().getString("NextdueDate");
            nextDueAmount=getIntent().getExtras().getString("NextdueAmount");
            cusImg=getIntent().getExtras().getString("cusImg");

            tempLoanTerm=Integer.parseInt(loanTerm);
            tempLoanTerm=tempLoanTerm+1;



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
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
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
                try{
                    if(getIntent().getExtras().getBoolean("unPay")){
                        paidDueAmount= String.valueOf('0');
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                tempsubAmt=Integer.parseInt(dueAmount)- Integer.parseInt(paidDueAmount);

                  tempPaidAmount=Integer.parseInt(textView_paidAmount.getText().toString());
                  tempBalanceAmount=Integer.parseInt(textView_balanceAmount.getText() .toString());
                  tempPaidDueAmount=Integer.parseInt(editText_paidAmount.getText().toString());
                String paidAmount= String.valueOf(tempPaidAmount+tempPaidDueAmount);
                result=tempBalanceAmount - tempPaidDueAmount;
                String balanceAmount=String.valueOf(result);

                if (result <= 50  ){
                    status = "Deactive";


                }
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

            //    Toast.makeText(getApplicationContext(),loanRating,Toast.LENGTH_SHORT).show();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Calendar tomorLoan=Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(textView_dueDate.getText().toString()));
                    tomorLoan.setTime(sdf.parse(textView_dueDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (loanOption.equals("Daily")){
                    c.add(Calendar.DATE, 1 );
                    tomorLoan.add(Calendar.DATE, 2);
                }
                else if(loanOption.equals("Weekly")){
                    c.add(Calendar.DATE, 7);
                    tomorLoan.add(Calendar.DATE, 14);
                }
                else if(loanOption.equals("By Weekly")){
                    c.add(Calendar.DATE, 15);
                    tomorLoan.add(Calendar.DATE, 30);
                }
                else if(loanOption.equals("Monthly")){
                    c.add(Calendar.MONTH, 1);
                    tomorLoan.add(Calendar.MONTH, 2);
                }
                else {
                  //  Toast.makeText(getApplicationContext(),"not ",Toast.LENGTH_LONG).show();
                }
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                String dueDate = sdf1.format(c.getTime());
                String tomorLoanDate= sdf1.format(tomorLoan.getTime());
                UserLocalStore userLocalStore=new UserLocalStore(getApplicationContext());
                String user=userLocalStore.getLoggedInUser();

                if (editText_paidAmount.length() >0){

                    if (duePaidDate.length() > 0){

                        if (tempBalanceAmount>= tempPaidDueAmount){

                            if (tempsubAmt <0){
                                pendingAmt=0;
                                extraAmt=Math.abs(tempsubAmt);

                            }
                            else if (tempLoanTerm >0){
                                pendingAmt=tempsubAmt;
                                extraAmt=0;

                            }
                            else {
                                pendingAmt=0;
                                extraAmt=0;

                            }




                            CollectLoan collectLoan=new CollectLoan(cusName,phone,city,loanId,loanOption,""+tempLoanTerm,
                                    totalAmount,paidAmount,balanceAmount,dueDate,dueAmount,paidDueDate,paidDueAmount,status,
                                    cusImg,user,loanRating,""+pendingAmt,""+extraAmt,tomorLoanDate);
                            AddDataToSerever(collectLoan);
                            Toast.makeText(getApplicationContext(),"Collections are success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ColectLoan_Activity.this,MainActivity.class));



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


    private void AddDataToSerever(CollectLoan collectLoan) {

        ServerRequest serverRequest=new ServerRequest(this);

        serverRequest.storeCollectDataInBackground(collectLoan, new GetCollectLoanCallBack() {
            @Override
            public void done(CollectLoan returnCollectLoan) {

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

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
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
        finish();
        return true;
    }

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    public void onBackPressed() {


        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
            System.exit(0);

        } else {

            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }


}
