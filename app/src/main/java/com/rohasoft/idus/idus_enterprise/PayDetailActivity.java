package com.rohasoft.idus.idus_enterprise;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.Adapter.AdapterPay;
import com.rohasoft.idus.idus_enterprise.Adapter.AdapterPayDetails;
import com.rohasoft.idus.idus_enterprise.Adapter.PayDetails;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayDetailActivity extends AppCompatActivity {

    private static final String URL_DATA="http://finance.idusmarket.com/api/laonpaydetails.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter  adapter;
    String loanid;
    ListView listView;

    TextView textView_totAmt,textView_loanid,textView_paidAmt,textView_balamt,textView_loanAmt;

    private List<PayDetails> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_new_pay);

      /*  recyclerView=(RecyclerView) findViewById(R.id.recyclerview_paydetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
*/
       /* textView_totAmt= (TextView) findViewById(R.id.pd_txt_totAmt);
        textView_loanid= (TextView) findViewById(R.id.pd_txt_loanid);
        textView_paidAmt= (TextView) findViewById(R.id.pd_txt_paidAmt);
        textView_balamt= (TextView) findViewById(R.id.pd_txt_balAmt);
        textView_loanAmt= (TextView) findViewById(R.id.pd_txt_loanAmt);*/

       listView=(ListView)findViewById(R.id.new_pay_list_view);
        list =new ArrayList<>();
       ArrayAdapter<String> adapter;



        loanid = getIntent().getExtras().getString("loan_id");
        getdetails(loanid);
    }

    public void getdetails(final String loanid){
        final ProgressDialog progressDialog=new ProgressDialog(getApplicationContext());
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

                                textView_loanid.setText(object.getString("total_amount"));
                                textView_totAmt.setText(object.getString("total_amount"));
                                textView_paidAmt.setText(object.getString("paid_amount"));
                                textView_balamt.setText(object.getString("balance_amount"));
                                textView_loanAmt.setText(object.getString("total_amount"));

                                PayDetails payDetails=new PayDetails(
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



                                list.add(payDetails);

                            }
                            AdapterPay adapterPay=new AdapterPay(getApplicationContext(),list);
                            listView.setAdapter(adapterPay);
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

                UserLocalStore userLocalStore=new UserLocalStore(getApplicationContext());
                String s=userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("loanid", loanid);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
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
