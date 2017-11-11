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
import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.AddLoanCusList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ayothi selvam on 07-11-2017.
 */

public class AddLoanCus extends RecyclerView.Adapter<AddLoanCus.ViewHolder>{


    private List<AddLoanCusList> list;
    private Context context;

    public AddLoanCus(List<AddLoanCusList> list, Context context) {
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
        holder.textView_cusId.setText("CUS"+loanCusList.getCusId());
        holder.textView_city.setText(loanCusList.getCity());
        Picasso.with(context)
                .load("http://www.idusmarket.com/loan-app/admin/uploads/"+loanCusList.getCusImag()).into(holder.imageView_cusphoto);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, AddLoan_Activity.class);
                intent.putExtra("id",loanCusList.getCusId());
                intent.putExtra("cusName",loanCusList.getCusName());
                intent.putExtra("phone",loanCusList.getPhone());
                intent.putExtra("address",loanCusList.getAddress());
                intent.putExtra("city",loanCusList.getCity());
                intent.putExtra("pincode",loanCusList.getPincode());
                intent.putExtra("cusImg",loanCusList.getCusImag());
                intent.putExtra("shopImg",loanCusList.getShopImg());
                intent.putExtra("idImg",loanCusList.getIdImg());
                intent.putExtra("addressImg",loanCusList.getAddressImg());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_cusName,textView_phone,textView_cusId,textView_city;
        LinearLayout linearLayout;
        ImageView  imageView_cusphoto;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_cusName=(TextView) itemView.findViewById(R.id.txt_Addloanlistcus);
            textView_phone=(TextView) itemView.findViewById(R.id.txt_Addloanlistphone);
            textView_cusId=(TextView) itemView.findViewById(R.id.txt_Addloanlistcusid);
            imageView_cusphoto=(ImageView) itemView.findViewById(R.id.img_AddLoanCusImg);
            textView_city=(TextView) itemView.findViewById(R.id.txt_Addloanlistcity);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout_addLoanCus);
        }
    }
}
