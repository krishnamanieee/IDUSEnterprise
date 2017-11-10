package com.rohasoft.idus.idus_enterprise.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rohasoft.idus.idus_enterprise.CustomerViewActivity;
import com.rohasoft.idus.idus_enterprise.NewScheduleActivity;
import com.rohasoft.idus.idus_enterprise.R;

import java.util.List;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class LaonAdapter extends RecyclerView.Adapter<LaonAdapter.ViewHolder> {

    private List<Loan>  loanList;
    private Context context;

    public LaonAdapter(List<Loan> loanList, Context context) {
        this.loanList = loanList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cusloanitem,parent,false);

        return new LaonAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Loan list=loanList.get(position);

        holder.textView_loanId.setText("LOAN"+list.loanId);
        holder.textView_totAmt.setText(list.getLoanTot());
        holder.textView_paidAmt.setText(list.getPaidAmount());
        holder.textView_balAmt.setText(list.getBalAmt());
        holder.textView_loanOption.setText(list.getLoanOption());
        holder.textView_loanDuration.setText(list.getLoanDurations());
        holder.textView_statDate.setText(list.getStartDate());
        holder.textView_endDate.setText(list.getEndDate());
        holder.textView_dueDate.setText(list.getDueDate());
        holder.textView_dueAmt.setText(list.getDueAmt());
        holder.textView_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, NewScheduleActivity.class);
                intent.putExtra("totalAmount",list.getLoanTot());
                intent.putExtra("loanOptions",list.getLoanOption());
                intent.putExtra("loanDurations",list.getLoanDurations());
                intent.putExtra("startDate",list.getStartDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_totAmt,textView_loanId,textView_paidAmt,textView_balAmt,textView_loanOption,textView_loanDuration,textView_statDate,textView_endDate;
        TextView textView_dueDate,textView_dueAmt;
        TextView textView_schedule;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_loanId=(TextView) itemView.findViewById(R.id.cus_loanId);
            textView_totAmt=(TextView) itemView.findViewById(R.id.txt_cusview_totloanamt);
            textView_paidAmt=(TextView) itemView.findViewById(R.id.txt_cusview_loanpaid);
            textView_balAmt=(TextView) itemView.findViewById(R.id.txt_cusview_loanbal);
            textView_loanOption=(TextView) itemView.findViewById(R.id.txt_cusview_loanoption);
            textView_loanDuration=(TextView) itemView.findViewById(R.id.txt_cusview_loanduration);
            textView_statDate=(TextView) itemView.findViewById(R.id.txt_cusview_startdate);
            textView_endDate=(TextView) itemView.findViewById(R.id.txt_cusview_enddate);
            textView_dueDate=(TextView) itemView.findViewById(R.id.txt_cusview_loancurrentdate);
            textView_dueAmt=(TextView) itemView.findViewById(R.id.txt_cusview_loanamt);
            textView_schedule=(TextView) itemView.findViewById(R.id.txt_cusview_viewschedule);

        }
    }
}
