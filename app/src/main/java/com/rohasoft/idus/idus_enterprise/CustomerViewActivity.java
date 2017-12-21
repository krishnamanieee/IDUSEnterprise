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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Ayothi selvam on 10-11-2017.
 */

public class CustomerViewActivity extends AppCompatActivity {

    GPSTracker gps;

    TextView textView_cusName, textView_cusId, textView_address, textView_city, textView_phone, textView_pincode;


    TextView textView_noloan, textView_refName, textView_refPhone, textView_shopName, textView_business;
    ImageView imageView_cus, imageView_shop, imageView_idProof, imageView_address;

    Button button_map, button_newLoan;

    String id, CusName, phone, address, city, pincode, latMap, lanMap, cusImg, shopImg, addressImg, idImg, refName, refPhone, shopName, industry;
    private static final String URL_DATA = "http://www.idusmarket.com/loan-app/app/getcusloandata.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Loan> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_cusview);


        textView_cusName = (TextView) findViewById(R.id.txt_cusview_name);
        textView_cusId = (TextView) findViewById(R.id.txt_cusview_cusid);
        textView_city = (TextView) findViewById(R.id.txt_cusview_city);
        textView_pincode = (TextView) findViewById(R.id.txt_cusview_pincode);
        textView_phone = (TextView) findViewById(R.id.txt_cusview_phone);
        textView_address = (TextView) findViewById(R.id.txt_cusview_addr);
        textView_refName = (TextView) findViewById(R.id.txt_cusview_refname);
        textView_refPhone = (TextView) findViewById(R.id.txt_cusview_refphone);
        textView_shopName = (TextView) findViewById(R.id.cusView_txt_shopName);
        textView_business = (TextView) findViewById(R.id.cusView_txt_business);

        imageView_cus = (ImageView) findViewById(R.id.img_view_execustum);
        imageView_shop = (ImageView) findViewById(R.id.img_view_exeshop);
        imageView_idProof = (ImageView) findViewById(R.id.img_view_exeidproof);
        imageView_address = (ImageView) findViewById(R.id.img_view_exeaddrproof);


        textView_noloan = (TextView) findViewById(R.id.txt_cusdes_schedule);
        textView_noloan.setVisibility(View.INVISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.listView_loan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button_map = (Button) findViewById(R.id.button_map);
        button_newLoan = (Button) findViewById(R.id.button_newloan);

        list = new ArrayList<>();
        loadRecyclerViewData();

        newLoan();


        if (getIntent().getExtras().getString("id").length() > 0) {
            id = getIntent().getExtras().getString("id");
            CusName = getIntent().getExtras().getString("cusName");
            phone = getIntent().getExtras().getString("phone");
            address = getIntent().getExtras().getString("address");
            city = getIntent().getExtras().getString("city");
            pincode = getIntent().getExtras().getString("pincode");
            latMap = getIntent().getExtras().getString("lat");
            lanMap = getIntent().getExtras().getString("lan");
            refName = getIntent().getExtras().getString("refName");
            refPhone = getIntent().getExtras().getString("refPhone");
            shopName = getIntent().getExtras().getString("shopName");
            industry = getIntent().getExtras().getString("industry");
            cusImg = getIntent().getExtras().getString("cusImg");
            shopImg = getIntent().getExtras().getString("shopImg");
            idImg = getIntent().getExtras().getString("idImg");
            addressImg = getIntent().getExtras().getString("addressImg");

            textView_cusName.setText(CusName);
            textView_cusId.setText("CUS" + id);
            textView_phone.setText(phone);
            textView_address.setText(address);
            textView_city.setText(city);
            textView_pincode.setText(pincode);
            textView_refName.setText(refName);
            textView_refPhone.setText(refPhone);
            textView_shopName.setText(shopName);
            textView_business.setText(industry);

            Picasso.with(this).load("http://www.idusmarket.com/loan-app/admin/uploads/" + cusImg).into(imageView_cus);
            Picasso.with(this).load("http://www.idusmarket.com/loan-app/admin/uploads/" + shopImg).into(imageView_shop);
            Picasso.with(this).load("http://www.idusmarket.com/loan-app/admin/uploads/" + idImg).into(imageView_idProof);
            Picasso.with(this).load("http://www.idusmarket.com/loan-app/admin/uploads/" + addressImg).into(imageView_address);

        }


        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fromLoaction = null;
                String toLoaction = null;

                gps = new GPSTracker(getApplicationContext());
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    fromLoaction = String.valueOf(latitude) + "," + String.valueOf(longitude);

                }
                toLoaction = latMap + "," + lanMap;


                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("geo:0,0?q="+ map));
//                        Uri.parse("http://maps.google.com/maps?saddr=  &daddr="+ map));
                        Uri.parse("http://maps.google.com/maps?saddr=" + fromLoaction + " &daddr=" + toLoaction));
                startActivity(intent);



                /*Uri mapUri = Uri.parse("geo:0,0?q=12.972879,80.220661");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);*/

            }
        });

        imageView_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewImg(cusImg);

            }
        });
        imageView_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewImg(addressImg);

            }
        });
        imageView_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewImg(shopImg);

            }
        });
        imageView_idProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewImg(idImg);

            }
        });


    }

    private void newLoan() {

        button_newLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddLoan_Activity.class);
                intent.putExtra("id", id);
                intent.putExtra("cusName", CusName);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                intent.putExtra("city", city);
                intent.putExtra("pincode", pincode);
                intent.putExtra("cusImg", cusImg);
                intent.putExtra("shopImg", shopImg);
                intent.putExtra("idImg", idImg);
                intent.putExtra("addressImg", addressImg);
                intent.putExtra("shopName", shopName);
                intent.putExtra("industry", industry);
                intent.putExtra("refName", refName);
                intent.putExtra("refPhone", refPhone);
                startActivity(intent);
            }
        });

    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
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
                            JSONArray jsonArray = jsonObject.getJSONArray("loan");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String s = object.getString("phone").toString().trim();

                                Long v1 = Long.parseLong(s);
                                String s3 = textView_phone.getText().toString();
                                Long v2 = Long.parseLong(s3);
                                if (v1.equals(v2)) {
                                    Loan items = new Loan(
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

                                } else {

                                    //Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_SHORT).show();
                                }

                            }
                            if (list.isEmpty()) {
                                textView_noloan.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "No Loan Available", Toast.LENGTH_SHORT).show();
                            } else {
                                adapter = new LaonAdapter(list, CustomerViewActivity.this);
                                recyclerView.setAdapter(adapter);
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
                }) {
            @Override
            protected Map<String, String> getParams() {

                UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
                String s = userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("phone", textView_phone.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void ViewImg(String path) {
        Intent intent = new Intent(CustomerViewActivity.this, ImageViewActivity.class);
        intent.putExtra("img", path);
        startActivity(intent);
    }

}
