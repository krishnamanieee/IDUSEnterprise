package com.rohasoft.idus.idus_enterprise.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.GetLoanCallBack;
import com.rohasoft.idus.idus_enterprise.other.Loan;
import com.rohasoft.idus.idus_enterprise.other.ServerRequest;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText edtcustumname, edtcustumid, edtphnno, edtaddr1, edtaddr2, edtcity, edtpincode, edtloanamount, edtloanduration,
            edtstartdate, edtenddate, edtremarks;

    ImageView imgcustum, imgshop, imgidproof, imgaddrproof;

    Spinner spinloanoption;

    Button btnsubmit,btnreset;

    private OnFragmentInteractionListener mListener;

    public MoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(String param1, String param2) {
        MoviesFragment fragment = new MoviesFragment();
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

        View v=inflater.inflate(R.layout.fragment_add_loan, container, false);


        edtcustumname = (EditText) v.findViewById(R.id.edit_txt_custumname);
        edtcustumid = (EditText) v.findViewById(R.id.edit_txt_custumid);
        edtphnno = (EditText) v.findViewById(R.id.edit_addloan_phnno);
        edtaddr1 = (EditText) v.findViewById(R.id.edit_txt_addr1);
        edtaddr2 = (EditText) v.findViewById(R.id.edit_txt_addr2);
        edtcity = (EditText) v.findViewById(R.id.edit_txt_city);
        edtpincode = (EditText) v.findViewById(R.id.edit_txt_pincode);
        edtloanamount = (EditText) v.findViewById(R.id.edit_txt_loan_amount);

        edtloanduration = (EditText) v.findViewById(R.id.edit_txt_loan_duration);
        edtstartdate = (EditText) v.findViewById(R.id.edit_txt_start_date);
        edtenddate = (EditText) v.findViewById(R.id.edit_txt_end_date);
        edtremarks = (EditText) v.findViewById(R.id.edit_txt_remarks);

        imgcustum = (ImageView) v.findViewById(R.id.img_view_custum);
        imgshop = (ImageView) v.findViewById(R.id.img_view_shop);
        imgidproof = (ImageView) v.findViewById(R.id.img_view_idproof);
        imgaddrproof = (ImageView) v.findViewById(R.id.img_view_addrproof);

        btnsubmit = (Button) v.findViewById(R.id.btn_submit);
        btnreset = (Button) v.findViewById(R.id.btn_reset);

        spinloanoption = (Spinner)v.findViewById(R.id.spin_loan_option);



        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cus_name=edtcustumname.getText().toString().trim();
                String cus_id=edtcustumid.getText().toString().trim();
                String phone=edtphnno.getText().toString().trim();
                String address=edtaddr1.getText().toString().trim() +", "+edtaddr2.getText().toString().trim();
                String city=edtcity.getText().toString().trim();
                /*String pincode=edtpincode.getText().toString().trim();
                String loan_amt=edtloanamount.getText().toString().trim();
               String loan_opttion=spinloanoption.getSelectedItem().toString().trim();
                String loan_duration=edtloanduration.getText().toString().trim();
                String Start_date=edtstartdate.getText().toString().trim();
                String end_date=edtenddate.getText().toString().trim();
                String remarks=edtremarks.getText().toString().trim();*/
                /*String cus_name=edtcustumname.getText().toString().trim();
                String cus_id=edtcustumid.getText().toString().trim();
                String phone="";
                String address="";
                String city="";*/
                String pincode="";
                String loan_amt="";
                String loan_opttion="Select loan otons";
                String loan_duration="";
                String Start_date="";
                String end_date="";
                String remarks="";
                Loan loan=new Loan(cus_name,cus_id,phone,address,city,pincode,loan_amt,loan_opttion,loan_duration,Start_date,end_date,remarks);

                AddLoan(loan);


            }
        });



        // Inflate the layout for this fragment
        return v;
    }

    private void AddLoan(Loan loan) {

        ServerRequest serverRequest=new ServerRequest(getContext());
        serverRequest.storeLoanDataInBackground(loan, new GetLoanCallBack() {
            @Override
            public void done(Loan returedGuser) {
                Toast.makeText(getContext(),"Insert",Toast.LENGTH_SHORT).show();
            }
        });


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
}
