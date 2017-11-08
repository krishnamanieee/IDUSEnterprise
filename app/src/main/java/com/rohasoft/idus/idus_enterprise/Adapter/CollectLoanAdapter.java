package com.rohasoft.idus.idus_enterprise.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rohasoft.idus.idus_enterprise.AddLoan_Activity;
import com.rohasoft.idus.idus_enterprise.ColectLoan_Activity;
import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.AddLoanCusList;
import com.rohasoft.idus.idus_enterprise.other.CollectLoan;
import com.rohasoft.idus.idus_enterprise.other.CollectLoanList;

import java.util.List;

/**
 * Created by Ayothi selvam on 08-11-2017.
 */

public class CollectLoanAdapter extends RecyclerView.Adapter<CollectLoanAdapter.ViewHolder> {

    private List<CollectLoanList> cusLists;
    private Context context;

    public CollectLoanAdapter(List<CollectLoanList> cusLists, Context context) {
        this.cusLists = cusLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loancustomerlistitems,parent,false);

        return new CollectLoanAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final CollectLoanList collectLoan=cusLists.get(position);

        holder.textView_cusName.setText(collectLoan.getCusName());
        holder.textView_phone.setText(collectLoan.getPhone());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ColectLoan_Activity.class);
                intent.putExtra("id",collectLoan.getLoanId().toString());
                intent.putExtra("cusName",collectLoan.getCusName());
                intent.putExtra("phone",collectLoan.getPhone());
                intent.putExtra("city",collectLoan.getCity());

                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return cusLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_cusName,textView_phone;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_cusName=(TextView) itemView.findViewById(R.id.txt_colloanlistcusnmae);
            textView_phone=(TextView) itemView.findViewById(R.id.txt_coloanlistphone);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout_ColLoanCusitems);
        }
        }

}
