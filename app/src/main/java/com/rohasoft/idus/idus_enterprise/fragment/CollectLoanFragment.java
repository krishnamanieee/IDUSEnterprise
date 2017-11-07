package com.rohasoft.idus.idus_enterprise.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


import com.rohasoft.idus.idus_enterprise.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.rohasoft.idus.idus_enterprise.R.id.edit_addloan_pincode;
import static com.rohasoft.idus.idus_enterprise.R.id.edit_colLoan__phnno;
import static com.rohasoft.idus.idus_enterprise.R.id.edit_colloan_due_paid_date;
import static com.rohasoft.idus.idus_enterprise.R.id.edit_colloan_paid_amount;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CollectLoanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CollectLoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectLoanFragment extends Fragment {

    Button pay, reset;
    EditText duePaidDate, paidAmount,phoneno,pincode;
    private SimpleDateFormat dateFormatter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CollectLoanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectLoanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectLoanFragment newInstance(String param1, String param2) {
        CollectLoanFragment fragment = new CollectLoanFragment();
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



        View v = inflater.inflate(R.layout.fragment_collect_loan, container, false);
        pay = (Button) v.findViewById(R.id.btn_colloan_pay);
        reset = (Button) v.findViewById(R.id.btn_colloan_reset);

        phoneno = (EditText) v.findViewById(R.id.edit_colLoan__phnno);
        phoneno.setEnabled(false);
        phoneno.setInputType(InputType.TYPE_NULL);

        pincode  = (EditText) v.findViewById(R.id.edit_addloan_pincode);
        pincode.setEnabled(false);
        pincode.setInputType(InputType.TYPE_NULL);

        duePaidDate = (EditText)v.findViewById(R.id.edit_colloan_due_paid_date);

        paidAmount  = (EditText) v.findViewById(R.id.edit_colloan_paid_amount);


        duePaidDate.setInputType(InputType.TYPE_NULL);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        reset();

        getDate();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        // Inflate the layout for this fragment
         return  v;
    }

    private void getDate() {

        duePaidDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar=Calendar.getInstance();

                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        duePaidDate.setText(dateFormatter.format(newDate.getTime()));

                    }
                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();

            }
        });

    }

    private void reset() {
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duePaidDate.setText("");
                paidAmount.setText("");
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
