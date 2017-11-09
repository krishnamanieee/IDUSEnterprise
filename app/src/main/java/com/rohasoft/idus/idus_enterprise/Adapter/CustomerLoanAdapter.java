package com.rohasoft.idus.idus_enterprise.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.AddLoanCusList;
import com.rohasoft.idus.idus_enterprise.other.GetLoanData;

import java.util.List;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class CustomerLoanAdapter extends RecyclerView.Adapter<CustomerLoanAdapter.ViewHolder> {

    private List<GetLoanData> getLoanDatas;
    private Context context;

    public CustomerLoanAdapter(List<GetLoanData> getLoanDatas, Context context) {
        this.getLoanDatas = getLoanDatas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cusloanitem,parent,false);

        return new CustomerLoanAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final GetLoanData getLoanData=getLoanDatas.get(position);
        holder.textView_loanid.setText(getLoanData.getLoanId());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_loanid;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_loanid=(TextView) itemView.findViewById(R.id.txt_cusloanid);
        }
    }
}
