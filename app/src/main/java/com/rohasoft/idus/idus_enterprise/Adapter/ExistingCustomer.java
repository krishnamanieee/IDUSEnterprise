package com.rohasoft.idus.idus_enterprise.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rohasoft.idus.idus_enterprise.AddLoan_Activity;
import com.rohasoft.idus.idus_enterprise.CustomerDescriptionActivity;
import com.rohasoft.idus.idus_enterprise.CustomerViewActivity;
import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.AddLoanCusList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ayothi selvam on 07-11-2017.
 */

public class ExistingCustomer extends RecyclerView.Adapter<ExistingCustomer.ViewHolder>{


    private List<AddLoanCusList> list;
    private Context context;

    public ExistingCustomer(List<AddLoanCusList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addloanitem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final AddLoanCusList loanCusList=list.get(position);

        holder.textView_cusName.setText(loanCusList.getCusName());
        holder.textView_phone.setText(loanCusList.getPhone());
        Picasso.with(context)
                .load("http://idusmarket.com/loan-app/app/images/"+loanCusList.getCusImag()).into(holder.imageView_cusphoto);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CustomerViewActivity.class);
                intent.putExtra("id",loanCusList.getCusId());
                intent.putExtra("cusName",loanCusList.getCusName());
                intent.putExtra("phone",loanCusList.getPhone());
                intent.putExtra("address",loanCusList.getAddress());
                intent.putExtra("city",loanCusList.getCity());
                intent.putExtra("pincode",loanCusList.getPincode());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_cusName,textView_phone;
        LinearLayout linearLayout;
        ImageView  imageView_cusphoto;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_cusName=(TextView) itemView.findViewById(R.id.txt_Addloanlistcus);
            textView_phone=(TextView) itemView.findViewById(R.id.txt_Addloanlistphone);
            imageView_cusphoto=(ImageView) itemView.findViewById(R.id.img_AddLoanCusImg);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout_addLoanCus);
        }
    }
}
