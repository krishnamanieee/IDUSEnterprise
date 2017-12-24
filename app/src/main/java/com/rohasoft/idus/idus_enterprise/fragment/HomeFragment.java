package com.rohasoft.idus.idus_enterprise.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.solver.SolverVariable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.idus.idus_enterprise.Adapter.CollectLoanAdapter;
import com.rohasoft.idus.idus_enterprise.Adapter.HomeAdapter;
import com.rohasoft.idus.idus_enterprise.CollAmtActivity;
import com.rohasoft.idus.idus_enterprise.PendingActivity;
import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.TMCollectActivity;
import com.rohasoft.idus.idus_enterprise.TdCollectActivity;
import com.rohasoft.idus.idus_enterprise.other.CollectLoanList;
import com.rohasoft.idus.idus_enterprise.other.HomeList;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String URL_DATA="http://www.idusmarket.com/loan-app/app/fetchhomepagedata.php";
    private static final String URL_DATA_TD="http://www.idusmarket.com/loan-app/app/todayloan.php";
    private static final String URL_DATA_TM="http://www.idusmarket.com/loan-app/app/tom_loan.php";
    private static final String URL_DATA_PN="http://www.idusmarket.com/loan-app/app/pending_loan.php";


    LinearLayout button_collotionList,button_td,button_tm,mLinearLayout_pending;
    TextView textView_colAmt,textView_todayAmt,textView_tommorrowAmt,textView_pending;

    int collectedAmt= 0;
    int TodayLoanAmt= 0;
    int TommorrowLoanAmt= 0;
    int pendingAmt= 0;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        button_collotionList=(LinearLayout) v.findViewById(R.id.button_coll);
        button_td=(LinearLayout) v.findViewById(R.id.button_td);
        button_tm=(LinearLayout) v.findViewById(R.id.button_tm);
        mLinearLayout_pending=(LinearLayout) v.findViewById(R.id.linearLayout_pedingAmt);
        textView_colAmt=(TextView) v.findViewById(R.id.textView_colAmt);
        textView_todayAmt=(TextView) v.findViewById(R.id.textView_tdAmt);
        textView_tommorrowAmt=(TextView) v.findViewById(R.id.textView_tmAmt);
        textView_pending=(TextView) v.findViewById(R.id.textView_pedingAmt);

        CollectionAmount();
        todayLoanAmount();
        tommorrowLoanAmount();
        pendingLoanAmount();


        button_td.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TdCollectActivity.class));

            }
        });

        button_tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TMCollectActivity.class));

            }
        });

        button_collotionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CollAmtActivity.class));
            }
        });

        mLinearLayout_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PendingActivity.class));
            }
        });




        return v;
    }

    private void pendingLoanAmount() {
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("loading Data....");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                URL_DATA_PN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("loan");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject  object=jsonArray.getJSONObject(i);

                                pendingAmt=pendingAmt+Integer.parseInt(object.getString("current_due_amount"));
                            }

                            DecimalFormat format=new DecimalFormat();
                            format.setMinimumFractionDigits(2);
                            String s=format.format(pendingAmt);
                            textView_pending.setText(s);


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

                UserLocalStore userLocalStore=new UserLocalStore(getContext());
                String s=userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("user", s);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void todayLoanAmount() {
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("loading Data....");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                URL_DATA_TD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("loan");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject  object=jsonArray.getJSONObject(i);

                                TodayLoanAmt=TodayLoanAmt+Integer.parseInt(object.getString("current_due_amount"));
                            }

                            DecimalFormat format=new DecimalFormat();
                            format.setMinimumFractionDigits(2);
                            String s=format.format(TodayLoanAmt);
                            textView_todayAmt.setText(s);


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

                UserLocalStore userLocalStore=new UserLocalStore(getContext());
                String s=userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("user", s);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void tommorrowLoanAmount() {
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("loading Data....");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                URL_DATA_TM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("loan");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject  object=jsonArray.getJSONObject(i);

                                TommorrowLoanAmt=TommorrowLoanAmt+Integer.parseInt(object.getString("current_due_amount"));
                            }

                            DecimalFormat format=new DecimalFormat();
                            format.setMinimumFractionDigits(2);
                            String s1=format.format(TommorrowLoanAmt);
                            textView_tommorrowAmt.setText(s1);


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

                UserLocalStore userLocalStore=new UserLocalStore(getContext());
                String s=userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("user", s);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially com.rohasoft.idus.idus_enterprise.other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void CollectionAmount() {
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
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
                            JSONArray jsonArray=jsonObject.getJSONArray("collect_loan");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject  object=jsonArray.getJSONObject(i);
                                collectedAmt=collectedAmt+Integer.parseInt(object.getString("due_paid_amount"));
                            }
                            DecimalFormat format=new DecimalFormat();
                            format.setMinimumFractionDigits(2);
                            String s=format.format(collectedAmt);
                            textView_colAmt.setText(s);
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

                UserLocalStore userLocalStore=new UserLocalStore(getContext());
                String s=userLocalStore.getLoggedInUser();

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("user", s);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
/*
    @Override
    public void onStart() {
        collectedAmt=0;
        TommorrowLoanAmt=0;
        TodayLoanAmt=0;
        CollectionAmount();
        todayLoanAmount();
        tommorrowLoanAmount();
        super.onStart();
    }*/
}
