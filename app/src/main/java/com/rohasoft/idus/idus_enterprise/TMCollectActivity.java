package com.rohasoft.idus.idus_enterprise;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.Adapter.CollectLoanAdapter;
import com.rohasoft.idus.idus_enterprise.other.CollectLoanList;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TMCollectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter  adapter;
    private List<CollectLoanList> list;
    private static final String URL_DATA="http://www.idusmarket.com/loan-app/app/tom_loan.php";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tmcollect);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview_tommorrwCol);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        loadRecyclerViewData();



    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog=new ProgressDialog(TMCollectActivity.this);
        progressDialog.setMessage("loading Data....");
        progressDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();


                        try {
                            JSONObject jsonObject=new JSONObject(response);
//                            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
                            JSONArray jsonArray=jsonObject.getJSONArray("loan");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject  object=jsonArray.getJSONObject(i);

                                CollectLoanList items=new CollectLoanList(
                                        object.getString("customer_name"),
                                        object.getString("customer_id"),
                                        object.getString("phone"),
                                        object.getString("city"),
                                        object.getString("id"),
                                        object.getString("loan_option"),
                                        object.getString("loan_amount"),
                                        object.getString("paid_amount"),
                                        object.getString("balance_amount"),
                                        object.getString("current_due_date"),
                                        object.getString("current_due_amount"),
                                        object.getString("customer_image"),
                                        object.getString("shop_image"),
                                        object.getString("idproof_image"),
                                        object.getString("addressproof_image")
                                );

                                list.add(items);

                            }

                            adapter=new CollectLoanAdapter(list,TMCollectActivity.this);
                            recyclerView.setAdapter(adapter );
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

                UserLocalStore userLocalStore=new UserLocalStore(TMCollectActivity.this);
                String s=userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("user", s);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(TMCollectActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;

    }
}
