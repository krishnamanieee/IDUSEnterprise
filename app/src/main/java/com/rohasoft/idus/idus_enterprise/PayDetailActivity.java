package com.rohasoft.idus.idus_enterprise;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.Adapter.AdapterPayDetails;
import com.rohasoft.idus.idus_enterprise.Adapter.AddLoanCus;
import com.rohasoft.idus.idus_enterprise.Adapter.PayDetails;
import com.rohasoft.idus.idus_enterprise.other.AddLoanCusList;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayDetailActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://idusmarket.com/loan-app/app/laonpaydetails.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    String loanid;

    TextView textView_totAmt, textView_loanid, textView_paidAmt, textView_balamt, textView_loanAmt;

    private List<PayDetails> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_detail);

        /*recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pay);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));*/
        listView= (ListView) findViewById(R.id.new_pay_list);

        textView_totAmt = (TextView) findViewById(R.id.pd_txt_totAmt);
        textView_loanid = (TextView) findViewById(R.id.pd_txt_loanid);
        textView_paidAmt = (TextView) findViewById(R.id.pd_txt_paidAmt);
        textView_balamt = (TextView) findViewById(R.id.pd_txt_balAmt);
        textView_loanAmt = (TextView) findViewById(R.id.pd_txt_loanAmt);


        list = new ArrayList<>();
        loanid = getIntent().getExtras().getString("loan_id");
        getdetails(loanid);
    }

    public void getdetails(final String loanid) {
        final ProgressDialog progressDialog = new ProgressDialog(PayDetailActivity.this);
        progressDialog.setMessage("loading Data....");
        progressDialog.show();

        final ArrayList arrayList=new ArrayList();


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

                            JSONObject object1 = jsonArray.getJSONObject(0);
                            textView_loanid.setText(object1.getString("total_amount"));
                            textView_totAmt.setText(object1.getString("total_amount"));
                            textView_paidAmt.setText(object1.getString("paid_amount"));
                            textView_balamt.setText(object1.getString("balance_amount"));
                            textView_loanAmt.setText(object1.getString("total_amount"));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                PayDetails payDetails = new PayDetails(
                                        object.getString("total_amount"),
                                        object.getString("paid_amount"),
                                        object.getString("balance_amount"),
                                        object.getString("customer_name"),
                                        object.getString("loan_term"),
                                        object.getString("loan_term"),
                                        object.getString("loan_term"),
                                        object.getString("loan_term"),
                                        object.getString("loan_term")
                                );

                                Log.e("Pay Details loan pay", loanid);


                                list.add(payDetails);
                                arrayList.add( "name");

                            }

                            Log.e("list",list.toString());
                            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_2,arrayList);
                            listView.setAdapter(arrayAdapter);
               /*             adapter = new AdapterPayDetails(list, PayDetailActivity.this);
                            recyclerView.setAdapter(adapter);*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
                String s = userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                Log.e("Pay Details", loanid);

                // Adding All values to Params.
                params.put("loanid", loanid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
