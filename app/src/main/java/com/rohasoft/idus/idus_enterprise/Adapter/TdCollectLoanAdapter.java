package com.rohasoft.idus.idus_enterprise.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.ColectLoan_Activity;
import com.rohasoft.idus.idus_enterprise.MainActivity;
import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.TdCollectActivity;
import com.rohasoft.idus.idus_enterprise.other.CollectLoan;
import com.rohasoft.idus.idus_enterprise.other.CollectLoanList;
import com.rohasoft.idus.idus_enterprise.other.GetCollectLoanCallBack;
import com.rohasoft.idus.idus_enterprise.other.ServerRequest;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class TdCollectLoanAdapter extends RecyclerView.Adapter<TdCollectLoanAdapter.ViewHolder> {

    private List<CollectLoanList> cusLists;
    private Context context;

    String Col_date, id;
    String phone,due_date;
    String tomorLoanDate,status="Active",loanRating,loanOption,cusImg;
    int rating, result, tempLoanTerm,pendingAmt,extraAmt,tempPaidAmount,tempBalanceAmount,tempPaidDueAmount,tempsubAmt;
    private SimpleDateFormat dateFormatter;
    private static final String URL_DATA="http://finance.idusmarket.com/api/unpay.php";

    long diffentbwDate=0;


    public TdCollectLoanAdapter(List<CollectLoanList> cusLists, Context context) {
        this.cusLists = cusLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loancustomerlistitems,parent,false);

        return new TdCollectLoanAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final CollectLoanList collectLoan=cusLists.get(position);

        holder.textView_cusName.setText(collectLoan.getCusName());
        holder.textView_phone.setText(collectLoan.getPhone());
        holder.textView_city.setText(collectLoan.getCity());
        holder.textView_loanId.setText("LOAN"+collectLoan.getLoanId());
        holder.textView_loanAmount.setText(collectLoan.getTotalAmount());
        Picasso.with(context)
                .load("http://finance.idusmarket.com/uploads/"+collectLoan.getCustomerImage()).into(holder.imageView_cus);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Loan");
                builder.setMessage("Select loan Option");
               builder.setPositiveButton("Pay", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Intent intent=new Intent(context, ColectLoan_Activity.class);
                       intent.putExtra("id",collectLoan.getLoanId().toString());
                       intent.putExtra("cusName",collectLoan.getCusName());
                       intent.putExtra("phone",collectLoan.getPhone());
                       intent.putExtra("city",collectLoan.getCity());
                       intent.putExtra("cusId",collectLoan.getCusId());
                       intent.putExtra("loanOption",collectLoan.getLoanOption());
                       intent.putExtra("loanTerm",collectLoan.getLoan_term());
                       intent.putExtra("totalAmount",collectLoan.getTotalAmount());
                       intent.putExtra("paidAmount",collectLoan.getPaidAmount());
                       intent.putExtra("balanceAmount",collectLoan.getBalanceAmount());
                       intent.putExtra("NextdueDate",collectLoan.getDueDate());
                       intent.putExtra("NextdueAmount",collectLoan.getDueAmount());
                       intent.putExtra("cusImg",collectLoan.getCustomerImage());
                       intent.putExtra("unPay",false);
                       context.startActivity(intent);

                   }
               });


              builder.setNegativeButton("UnPay", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      unPay(position);
//                      Intent intent=new Intent(context, ColectLoan_Activity.class);
//                      intent.putExtra("id",collectLoan.getLoanId().toString());
//                      intent.putExtra("cusName",collectLoan.getCusName());
//                      intent.putExtra("phone",collectLoan.getPhone());
//                      intent.putExtra("city",collectLoan.getCity());
//                      intent.putExtra("cusId",collectLoan.getCusId());
//                      intent.putExtra("loanOption",collectLoan.getLoanOption());
//                      intent.putExtra("loanTerm",collectLoan.getLoan_term());
//                      intent.putExtra("totalAmount",collectLoan.getTotalAmount());
//                      intent.putExtra("paidAmount",collectLoan.getPaidAmount());
//                      intent.putExtra("balanceAmount",collectLoan.getBalanceAmount());
//                      intent.putExtra("NextdueDate",collectLoan.getDueDate());
//                      intent.putExtra("NextdueAmount",collectLoan.getDueAmount());
//                      intent.putExtra("cusImg",collectLoan.getCustomerImage());
//                      intent.putExtra("unPay",true);
//
//                      context.startActivity(intent);
//                      Calendar c = Calendar.getInstance();
//                      c.add(Calendar.DATE, 1 );
//                      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//                      Col_date = sdf1.format(c.getTime());
//                      due_date=collectLoan.getDueDate();
//                      id=collectLoan.getLoanId();
//                      phone=collectLoan.getPhone();
//                      String loanOption= collectLoan.getLoanOption();
//
//                      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                      Calendar c1 = Calendar.getInstance();
//                      Calendar tomorLoan=Calendar.getInstance();
//                      try {
//                          c1.setTime(sdf.parse(due_date));
//                          tomorLoan.setTime(sdf.parse(due_date));
//                      } catch (ParseException e) {
//                          e.printStackTrace();
//                      }
//                      if (loanOption.equals("Daily")){
//                          c1.add(Calendar.DATE, 1 );
//                          tomorLoan.add(Calendar.DATE, 2);
//                      }
//                      else if(loanOption.equals("Weekly")){
//                          c1.add(Calendar.DATE, 7);
//                          tomorLoan.add(Calendar.DATE, 14);
//                      }
//                      else if(loanOption.equals("By Weekly")){
//                          c1.add(Calendar.DATE, 15);
//                          tomorLoan.add(Calendar.DATE, 30);
//                      }
//                      else if(loanOption.equals("Monthly")){
//                          c1.add(Calendar.MONTH, 1);
//                          tomorLoan.add(Calendar.MONTH, 2);
//                      }
//                      else {
//                          //  Toast.makeText(getApplicationContext(),"not ",Toast.LENGTH_LONG).show();
//                      }
//                      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//                      String dueDate = sdf2.format(c1.getTime());
//                      tomorLoanDate= sdf2.format(tomorLoan.getTime());
//                      notifyDataSetChanged();
//
//                      loadUnPay();

                  }
              });
              builder.show();



            }
        });



    }
    private void unPay(int position) {

        final CollectLoanList collectLoan = cusLists.get(position);
        tempLoanTerm=Integer.parseInt(collectLoan.getLoan_term());
        tempLoanTerm=tempLoanTerm+1;
        String cusName = collectLoan.getCusName();
        String phone = collectLoan.getPhone();
        String city = collectLoan.getCity();
        String loanId = collectLoan.getLoanId();
        String totalAmount = collectLoan.getTotalAmount();


        String dueAmount = collectLoan.getDueAmount();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String paidDueDate = dateFormat.format(date);
        String paidDueAmount = "0";
        cusImg = collectLoan.getCustomerImage();
        loanOption = collectLoan.getLoanOption();
        tempsubAmt = Integer.parseInt(dueAmount) - 0;

        tempPaidAmount = Integer.parseInt(collectLoan.getPaidAmount());
        tempBalanceAmount = Integer.parseInt(collectLoan.getBalanceAmount());
        tempPaidDueAmount = Integer.parseInt("0");
        String paidAmount = String.valueOf(tempPaidAmount + tempPaidDueAmount);
        result = tempBalanceAmount - tempPaidDueAmount;
        String balanceAmount = String.valueOf(result);

        if (result <= 50) {
            status = "Deactive";


        }
        try {
            dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = new Date();
            Log.e("tr", "unPay: "+dateFormat1.format(date1) );
            Date tempDueDate = dateFormatter.parse(dateFormat1.format(date1));
            Date tempCurrentDate = dateFormatter.parse(collectLoan.getDueDate());
            long diff = tempDueDate.getTime() - tempCurrentDate.getTime();
            diffentbwDate = diff / (24 * 60 * 60 * 1000);
            if (diffentbwDate <= 0) {
                rating = 5;
            } else if (diffentbwDate <= 5 && diffentbwDate > 0) {
                rating = 4;
            } else if (diffentbwDate <= 10 && diffentbwDate > 5) {
                rating = 3;
            } else if (diffentbwDate <= 20 && diffentbwDate > 10) {
                rating = 2;
            } else if (diffentbwDate <= 30 && diffentbwDate > 20) {
                rating = 1;
            } else {
                rating = 0;
            }
            loanRating = String.valueOf(rating);
            // Toast.makeText(getApplicationContext(),""+rating,Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //    Toast.makeText(getApplicationContext(),loanRating,Toast.LENGTH_SHORT).show();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar tomorLoan = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(collectLoan.getDueDate()));
            tomorLoan.setTime(sdf.parse(collectLoan.getDueDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (loanOption.equals("Daily")) {
            c1.add(Calendar.DATE, 1);
            tomorLoan.add(Calendar.DATE, 2);
        } else if (loanOption.equals("Weekly")) {
            c1.add(Calendar.DATE, 7);
            tomorLoan.add(Calendar.DATE, 14);
        } else if (loanOption.equals("By Weekly")) {
            c1.add(Calendar.DATE, 15);
            tomorLoan.add(Calendar.DATE, 30);
        } else if (loanOption.equals("Monthly")) {
            c1.add(Calendar.MONTH, 1);
            tomorLoan.add(Calendar.MONTH, 2);
        } else {
            //  Toast.makeText(getApplicationContext(),"not ",Toast.LENGTH_LONG).show();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String dueDate = sdf2.format(c1.getTime());
        String tomorLoanDate = sdf2.format(tomorLoan.getTime());
        UserLocalStore userLocalStore = new UserLocalStore(context);
        String user = userLocalStore.getLoggedInUser();
        if (tempBalanceAmount >= tempPaidDueAmount) {

            if (tempsubAmt < 0) {
                pendingAmt = 0;
                extraAmt = Math.abs(tempsubAmt);

            } else if (tempLoanTerm > 0) {
                pendingAmt = tempsubAmt;
                extraAmt = 0;

            } else {
                pendingAmt = 0;
                extraAmt = 0;

            }


            CollectLoan collectLoan1 = new CollectLoan(cusName, phone, city, loanId, loanOption, "" + tempLoanTerm,
                    totalAmount, paidAmount, balanceAmount, dueDate, dueAmount, paidDueDate, paidDueAmount, status,
                    cusImg, user, loanRating, "" + pendingAmt, "" + extraAmt, tomorLoanDate);
            AddDataToSerever(collectLoan1);
            Toast.makeText(context, "Collections are success", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, MainActivity.class));

        }
    }
    private void AddDataToSerever(CollectLoan collectLoan) {

        ServerRequest serverRequest=new ServerRequest(context);

        serverRequest.storeCollectDataInBackground(collectLoan, new GetCollectLoanCallBack() {
            @Override
            public void done(CollectLoan returnCollectLoan) {

                if (returnCollectLoan != null){

                }
            }
        });
    }

    private void loadUnPay() {

        final ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("loading Data....");
        progressDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        notifyDataSetChanged();
                        context.startActivity(new Intent(context, MainActivity.class));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() {



                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("id", id);
                params.put("Col_date", Col_date);
                params.put("due_date", due_date);
                params.put("phone", phone);
                params.put("tomor_due_date", tomorLoanDate);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    @Override
    public int getItemCount() {
        return cusLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_cusName,textView_phone, textView_loanId,textView_city,textView_loanAmount;
        LinearLayout linearLayout;
        ImageView imageView_cus;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_cusName=(TextView) itemView.findViewById(R.id.txt_colloanlistcusnmae);
            textView_loanId=(TextView) itemView.findViewById(R.id.txt_colloanlistloanid);
            textView_city=(TextView) itemView.findViewById(R.id.txt_coloanlistcity);
            textView_phone=(TextView) itemView.findViewById(R.id.txt_colloanlistphone);
            imageView_cus=(ImageView) itemView.findViewById(R.id.img_colLoanCusImg);
            textView_loanAmount=(TextView) itemView.findViewById(R.id.txt_colloanlistloanamt);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout_ColLoanCusitems);
        }
        }

}
