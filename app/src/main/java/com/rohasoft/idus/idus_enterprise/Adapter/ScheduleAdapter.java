package com.rohasoft.idus.idus_enterprise.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rohasoft.idus.idus_enterprise.R;

import java.util.List;

/**
 * Created by Ayothi selvam on 10-11-2017.
 */

public class ScheduleAdapter extends ArrayAdapter<Schedule> {

    List<Schedule> list;
    Context context;
    int resource;


    TextView textView_dueDate,textView_payAmt,textView_balAmt;

    public  ScheduleAdapter(Context context,int resource,List<Schedule> schedules){
        super(context,resource,schedules);
        this.context=context;
        this.resource=resource;
        this.list=schedules;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(resource,null,false);

        textView_dueDate=(TextView) view.findViewById(R.id.txt_duedate);
        textView_payAmt=(TextView) view.findViewById(R.id.txt_payamt);
        textView_balAmt=(TextView) view.findViewById(R.id.txt_balAmt);

        Schedule schedule= list.get(position);


        textView_dueDate.setText(schedule.dueDate);
        textView_payAmt.setText(schedule.payAmount);
        textView_balAmt.setText(schedule.balanceAmount);

        return view;
    }
}
