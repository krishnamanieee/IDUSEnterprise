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
import android.widget.Toast;


import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.Customer;
import com.rohasoft.idus.idus_enterprise.other.GetCustomerCallBack;
import com.rohasoft.idus.idus_enterprise.other.Loan;
import com.rohasoft.idus.idus_enterprise.other.ServerRequest;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddCustomerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCustomerFragment extends Fragment{

    EditText editText_cusName,editText_phone,editText_addr1,editText_addr2,editText_city,editText_pincode,editText_lacMap,editText_lanMap,
    editText_remarks;
    Button button_addCustomer,button_reset;
    ImageView imageView_addcus_map;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddCustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCustomerFragment newInstance(String param1, String param2) {
        AddCustomerFragment fragment = new AddCustomerFragment();
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
        View v=  inflater.inflate(R.layout.fragment_add_customer, container, false);


        editText_cusName= (EditText) v.findViewById(R.id.edt_addcus_custmname);
        editText_phone= (EditText) v.findViewById(R.id.edt_addcus_phnno);
        editText_addr1= (EditText) v.findViewById(R.id.edt_addcus_addr1);
        editText_addr2= (EditText) v.findViewById(R.id.edt_addcus_addr2);
        editText_city= (EditText) v.findViewById(R.id.edt_addcus_city);
        editText_pincode= (EditText) v.findViewById(R.id.edt_addcus_pincode);
        editText_lacMap= (EditText) v.findViewById(R.id.edt_addcus_maplac);
        editText_lanMap= (EditText) v.findViewById(R.id.edt_addcus_maplan);
        editText_remarks= (EditText) v.findViewById(R.id.edt_addcus__remark);

        imageView_addcus_map= (ImageView) v.findViewById(R.id.img_addcus_map);






        button_addCustomer= (Button) v.findViewById(R.id.btn_addcus_submit);
        button_reset= (Button) v.findViewById(R.id.btn_addcus_reset);

        reset();



        button_addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cusName=editText_cusName.getText().toString().trim();
                String phone=editText_phone.getText().toString().trim();
                String addr1=editText_addr1.getText().toString().trim();
                String addr2=editText_addr2.getText().toString().trim();
                String city=editText_city.getText().toString().trim();
                String pincode=editText_pincode.getText().toString().trim();
                String lanMap=editText_lanMap.getText().toString().trim();
                String lacMap=editText_lacMap.getText().toString().trim();
                String remark=editText_remarks.getText().toString().trim();


                if (cusName.length() > 0){
                    if (phone.length() == 10){
                        if(pincode.length()==6){
                            if(city.length() > 0){
                                Customer customer=new Customer(cusName,phone,addr1+","+addr2,city,pincode,lanMap,lacMap,remark);
                                addCustomer(customer);
                            }
                            else{
                                editText_city.setError("Please enter the City");
                            }


                        }
                        else {
                            editText_pincode.setError("please enter valid pincode");
                        }

                    }
                    else {
                        editText_phone.setError("please enter valid phone no");
                    }
                }

                else {
                    editText_cusName.setError("please fill customer name");
                }


            }
        });

        return v;
    }

    private void reset() {

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editText_cusName.setText("");
                editText_phone.setText("");
                editText_addr1.setText("");
                editText_addr2.setText("");
                editText_city.setText("");
                editText_pincode.setText("");
                editText_lacMap.setText("");
                editText_remarks.setText("");
            }
        });
    }



    private void addCustomer(Customer customer) {

        ServerRequest serverRequest=new ServerRequest(getContext());
        serverRequest.storeCustomerDataInBackground(customer, new GetCustomerCallBack() {
            @Override
            public void done(Customer returedCustomer) {
                Toast.makeText(getContext(),"new Customer add sucessfully",Toast.LENGTH_SHORT).show();
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
