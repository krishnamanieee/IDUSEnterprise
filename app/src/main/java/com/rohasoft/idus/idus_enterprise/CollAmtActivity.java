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
import com.rohasoft.idus.idus_enterprise.Adapter.HomeAdapter;
import com.rohasoft.idus.idus_enterprise.other.CollectLoanList;
import com.rohasoft.idus.idus_enterprise.other.HomeList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CollAmtActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter  adapter;
    private List<HomeList> list;
    private static final String URL_DATA="http://www.idusmarket.com/loan-app/app/fetchhomepagedata.php";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_coll_amt);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview_CollAmt);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        loadRecyclerViewData();



    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog=new ProgressDialog(CollAmtActivity.this);
        progressDialog.setMessage("loading Data....");
        progressDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();


                        try {
                            JSONObject jsonObject=new JSONObject(response);
//                            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
                            JSONArray jsonArray=jsonObject.getJSONArray("collect_loan");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject  object=jsonArray.getJSONObject(i);

                                HomeList items=new HomeList(
                                        object.getString("customer_name"),
                                        object.getString("city"),
                                        object.getString("phone"),
                                        object.getString("due_paid_date"),
                                        object.getString("due_paid_amount"),
                                        object.getString("customer_image")
                                );

                                list.add(items);

                            }

                            adapter=new HomeAdapter(list,CollAmtActivity.this);
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
                });

        RequestQueue requestQueue= Volley.newRequestQueue(CollAmtActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;

    }
}