package com.rohasoft.idus.idus_enterprise;

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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddLoanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddLoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddLoanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView txtcustumname, txtcustumid, txtphnno, txtaddr, txtcity, txtpincode, txtloanamount,txtloanduration, txtloanoption,
            txtstartdate, txtenddate, txtremarks, txtcustum, txtshop, txtidproof, txtaddrproof;

    EditText edtcustumname, edtcustumid, edtphnno, edtaddr1, edtaddr2, edtcity, edtpincode, edtloanamount, edtloanduration,
            edtstartdate, edtenddate, edtremarks;

    ImageView imgcustum, imgshop, imgidproof, imgaddrproof;

    Spinner spinloanoption;

    Button btnsubmit,btnreset;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddLoanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddLoanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddLoanFragment newInstance(String param1, String param2) {
        AddLoanFragment fragment = new AddLoanFragment();
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
        View v = inflater.inflate(R.layout.fragment_add_loan, container, false);

        edtcustumname = (EditText) v.findViewById(R.id.edit_txt_custumname);
        edtcustumid = (EditText) v.findViewById(R.id.edit_txt_custumid);
        edtphnno = (EditText) v.findViewById(R.id.edit_txt__phnno);
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



        return v;



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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
