package com.rohasoft.idus.idus_enterprise;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.Adapter.AdapterPay;
import com.rohasoft.idus.idus_enterprise.Adapter.PayDetails;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by krish on 12/21/2017.
 */

public class NewPayDetails extends AppCompatActivity {

    List<PayDetails> list;
    ListView mListView;
    String loanid;
    TextView textView_totAmt, textView_loanid, textView_paidAmt, textView_balamt, textView_loanAmt;

    private static final String URL_DATA = "http://idusmarket.com/loan-app/app/laonpaydetails.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pay);

        list = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.new_pay_list_view);

        textView_totAmt = (TextView) findViewById(R.id.pd_txt_totAmt);
        textView_loanid = (TextView) findViewById(R.id.pd_txt_loanid);
        textView_paidAmt = (TextView) findViewById(R.id.pd_txt_paidAmt);
        textView_balamt = (TextView) findViewById(R.id.pd_txt_balAmt);
        //   textView_loanAmt= (TextView) findViewById(R.id.pd_txt_loanAmt);


        loanid = getIntent().getExtras().getString("loan_id");
        textView_loanid.setText(loanid);
        getdetails(loanid);

    }

    public void getdetails(final String loanidm) {
        final ProgressDialog progressDialog = new ProgressDialog(NewPayDetails.this);
        progressDialog.setMessage("loading Data....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();


                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
                            JSONArray jsonArray = jsonObject.getJSONArray("loan");
                            Log.e("TAG", response);


                            list.add(new PayDetails("hy", "jkh", "uh",
                                    "lij", "Term", "Date", "Paid", "Pending", "Extra"));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);


                                textView_totAmt.setText(object.getString("total_amount"));
                                textView_paidAmt.setText(object.getString("paid_amount"));
                                textView_balamt.setText(object.getString("balance_amount"));

                                PayDetails payDetails = new PayDetails(
                                        object.getString("total_amount"),
                                        object.getString("paid_amount"),
                                        object.getString("balance_amount"),
                                        object.getString("customer_name"),
                                        object.getString("loan_term"),
                                        object.getString("due_paid_date"),
                                        object.getString("due_paid_amount"),
                                        object.getString("pendind_amt"),
                                        object.getString("extra_amt")
                                );


                                list.add(payDetails);
                            }
                            AdapterPay adapterPay = new AdapterPay(NewPayDetails.this, list);
                            mListView.setAdapter(adapterPay);
                          /*  adapter=new AdapterPayDetails(list,PayDetailActivity.this);
                            recyclerView.setAdapter(adapter );*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                String lo=textView_loanid.getText().toString().trim();


                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("loan_id", lo);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
