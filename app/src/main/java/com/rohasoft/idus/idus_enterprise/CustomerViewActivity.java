package com.rohasoft.idus.idus_enterprise;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.Adapter.LaonAdapter;
import com.rohasoft.idus.idus_enterprise.Adapter.Loan;
import com.rohasoft.idus.idus_enterprise.other.GPSTracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ayothi selvam on 10-11-2017.
 */

public class CustomerViewActivity extends AppCompatActivity {

    GPSTracker gps;

    TextView textView_cusName, textView_cusId, textView_address, textView_city, textView_phone, textView_pincode;

    TextView textView_edit;
    TextView textView_noloan;

    Button button_map;

    String id, CusName, phone, address, city, pincode, latMap,lanMap;
    private static final String URL_DATA = "http://www.idusmarket.com/loan-app/app/getcusloandata.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Loan> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_customer_view);


        textView_cusName = (TextView) findViewById(R.id.txt_cusview_name);
        textView_cusId = (TextView) findViewById(R.id.txt_cusview_cusid);
        textView_city = (TextView) findViewById(R.id.txt_cusview_city);
        textView_pincode = (TextView) findViewById(R.id.txt_cusview_pincode);
        textView_phone = (TextView) findViewById(R.id.txt_cusview_phone);
        textView_address = (TextView) findViewById(R.id.txt_cusview_addr);
        textView_edit = (TextView) findViewById(R.id.txt_cusview_profedit);
        textView_edit.setVisibility(View.INVISIBLE);

        textView_noloan = (TextView) findViewById(R.id.txt_cusdes_schedule);
        textView_noloan.setVisibility(View.INVISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.listView_loan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button_map = (Button) findViewById(R.id.button_map);

        list = new ArrayList<>();
        loadRecyclerViewData();


        if (getIntent().getExtras().getString("id").length() > 0) {
            id = getIntent().getExtras().getString("id");
            CusName = getIntent().getExtras().getString("cusName");
            phone = getIntent().getExtras().getString("phone");
            address = getIntent().getExtras().getString("address");
            city = getIntent().getExtras().getString("city");
            pincode = getIntent().getExtras().getString("pincode");
            latMap = getIntent().getExtras().getString("lat");
            lanMap = getIntent().getExtras().getString("lan");

            textView_cusName.setText(CusName);
            textView_cusId.setText("CUS" + id);
            textView_phone.setText(phone);
            textView_address.setText(address);
            textView_city.setText(city);
            textView_pincode.setText(pincode);

        }


        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fromLoaction = null;
                String toLoaction = null;

                gps=new GPSTracker(getApplicationContext());
                if (gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                     fromLoaction= String.valueOf(latitude)+","+String.valueOf(longitude);

                }
                toLoaction=latMap+","+lanMap;


                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("geo:0,0?q="+ map));
//                        Uri.parse("http://maps.google.com/maps?saddr=  &daddr="+ map));
                        Uri.parse("http://maps.google.com/maps?saddr="+fromLoaction+" &daddr="+toLoaction));
                startActivity(intent);



                /*Uri mapUri = Uri.parse("geo:0,0?q=12.972879,80.220661");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);*/

            }
        });




    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
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
                            JSONArray jsonArray=jsonObject.getJSONArray("loan");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject  object=jsonArray.getJSONObject(i);
                                    String s=object.getString("phone").toString().trim();
                                Long v1=  Long.parseLong(s);
                                String s3=textView_phone.getText().toString();
                                Long v2=  Long.parseLong(s3);
                                if (v1.equals(v2)) {

                                    Loan items=new Loan(
                                            object.getString("customer_name"),
                                            object.getString("phone"),
                                            object.getString("id"),
                                            object.getString("loan_amount"),
                                            object.getString("paid_amount"),
                                            object.getString("balance_amount"),
                                            object.getString("loan_option"),
                                            object.getString("loan_duration"),
                                            object.getString("start_date"),
                                            object.getString("end_date"),
                                            object.getString("current_due_date"),
                                            object.getString("current_due_amount")
                                    );
                                    list.add(items);

                                }
                                else {

                                    //Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_SHORT).show();
                                }

                            }
                            if (list.isEmpty()){
                                textView_noloan.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(),"No Loan Available",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                adapter=new LaonAdapter(list,CustomerViewActivity.this);
                                recyclerView.setAdapter(adapter );
                            }

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

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
