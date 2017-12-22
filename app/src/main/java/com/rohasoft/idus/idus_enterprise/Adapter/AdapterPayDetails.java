package com.rohasoft.idus.idus_enterprise.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.CollectLoanList;

import java.util.List;

/**
 * Created by Ayothi selvam on 11/23/2017.
 */

public class AdapterPayDetails extends RecyclerView.Adapter<AdapterPayDetails.ViewHolder> {

    List<PayDetails> list;
    Context context;

    public AdapterPayDetails(List<PayDetails> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paydetail,parent,false);

        return new AdapterPayDetails.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final PayDetails payDetails=list.get(position);

        holder.textView_loanterm.setError(payDetails.getLoanTerm());
        holder.textView_date.setError(payDetails.getLoanTerm());
        holder.textView_amt.setError(payDetails.getLoanTerm());
        holder.textView_pending.setError(payDetails.getLoanTerm());
        holder.textView_extra.setError(payDetails.getLoanTerm());
        Log.e("TAG",payDetails.getLoanAmt());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_loanterm,textView_date,textView_amt,textView_pending,textView_extra;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_loanterm=(TextView) itemView.findViewById(R.id.ipd_loanTerm);
            textView_date=(TextView) itemView.findViewById(R.id.ipd_date);
            textView_amt=(TextView) itemView.findViewById(R.id.ipd_amt);
            textView_pending=(TextView) itemView.findViewById(R.id.ipd_pending);
            textView_extra=(TextView) itemView.findViewById(R.id.ipd_extra);
        }
    }
}
