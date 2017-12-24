package com.rohasoft.idus.idus_enterprise.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.ColectLoan_Activity;
import com.rohasoft.idus.idus_enterprise.MainActivity;
import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.CollectLoanList;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class PendingLoanAdapter extends RecyclerView.Adapter<PendingLoanAdapter.ViewHolder> {

    private List<CollectLoanList> cusLists;
    private Context context;

    String Col_date, id;
    String phone,due_date;

    private static final String URL_DATA="http://idusmarket.com/loan-app/app/unpay.php";



    public PendingLoanAdapter(List<CollectLoanList> cusLists, Context context) {
        this.cusLists = cusLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loancustomerlistitems,parent,false);

        return new PendingLoanAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final CollectLoanList collectLoan=cusLists.get(position);

        holder.textView_cusName.setText(collectLoan.getCusName());
        holder.textView_phone.setText(collectLoan.getPhone());
        holder.textView_city.setText(collectLoan.getCity());
        holder.textView_loanId.setText("LOAN"+collectLoan.getLoanId());
        holder.textView_loanAmount.setText(collectLoan.getTotalAmount());
        Picasso.with(context)
                .load("http://www.idusmarket.com/loan-app/admin/uploads/"+collectLoan.getCustomerImage()).into(holder.imageView_cus);

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

                       context.startActivity(intent);

                   }
               });


             /* builder.setNegativeButton("UnPay", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                      Calendar c = Calendar.getInstance();
                      c.add(Calendar.DATE, 1 );
                      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                      Col_date = sdf1.format(c.getTime());
                      due_date=collectLoan.getDueDate();
                      id=collectLoan.getLoanId();
                      phone=collectLoan.getPhone();
                      notifyDataSetChanged();

                      loadUnPay();

                  }
              });*/
              builder.show();



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
