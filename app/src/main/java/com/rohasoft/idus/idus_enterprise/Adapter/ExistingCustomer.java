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
import android.widget.Toast;

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

        String s=loanCusList.getRating().toString();
        int i=Integer.parseInt(s);
        if (i == 5){

            holder.img_star1.setVisibility(View.VISIBLE);
            holder.img_star2.setVisibility(View.VISIBLE);
            holder.img_star3.setVisibility(View.VISIBLE);
            holder.img_star4.setVisibility(View.VISIBLE);
            holder.img_star5.setVisibility(View.VISIBLE);

        }
        else if (i ==4){
            holder.img_star1.setVisibility(View.VISIBLE);
            holder.img_star2.setVisibility(View.VISIBLE);
            holder.img_star3.setVisibility(View.VISIBLE);
            holder.img_star4.setVisibility(View.VISIBLE);

        } else if (i ==3){
            holder.img_star1.setVisibility(View.VISIBLE);
            holder.img_star2.setVisibility(View.VISIBLE);
            holder.img_star3.setVisibility(View.VISIBLE);


        } else if (i ==2){
            holder.img_star1.setVisibility(View.VISIBLE);
            holder.img_star2.setVisibility(View.VISIBLE);

        } else if (i ==1){
            holder.img_star1.setVisibility(View.VISIBLE);

        }
        else {
            holder.img_star1.setVisibility(View.INVISIBLE);
            holder.img_star2.setVisibility(View.INVISIBLE);
            holder.img_star3.setVisibility(View.INVISIBLE);
            holder.img_star4.setVisibility(View.INVISIBLE);
            holder.img_star5.setVisibility(View.INVISIBLE);
        }
        holder.textView_cusName.setText(loanCusList.getCusName());
        holder.textView_phone.setText(loanCusList.getPhone());
        holder.textView_city.setText(loanCusList.getCity());
        holder.textView_cusid.setText("CUS"+loanCusList.getCusId());


        Picasso.with(context)
                .load("http://www.idusmarket.com/loan-app/admin/uploads/"+loanCusList.getCusImag())
                .into(holder.imageView_cusphoto);
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
                intent.putExtra("lat",loanCusList.getLatMap());
                intent.putExtra("lan",loanCusList.getLanMap());
                intent.putExtra("cusImg",loanCusList.getCusImag());
                intent.putExtra("shopImg",loanCusList.getShopImg());
                intent.putExtra("idImg",loanCusList.getIdImg());
                intent.putExtra("addressImg",loanCusList.getAddressImg());
                intent.putExtra("refName",loanCusList.getRefName());
                intent.putExtra("refPhone",loanCusList.getRefPhone());
                intent.putExtra("shopName",loanCusList.getShopName());
                intent.putExtra("industry",loanCusList.getIndustry());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_cusName,textView_phone,textView_cusid,textView_city;
        LinearLayout linearLayout;
        ImageView  imageView_cusphoto;
        ImageView  img_star1,img_star2,img_star3,img_star4,img_star5;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_cusName=(TextView) itemView.findViewById(R.id.txt_Addloanlistcus);
            textView_phone=(TextView) itemView.findViewById(R.id.txt_Addloanlistphone);
            textView_cusid=(TextView) itemView.findViewById(R.id.txt_Addloanlistcusid);
            textView_city=(TextView) itemView.findViewById(R.id.txt_Addloanlistcity);
            imageView_cusphoto=(ImageView) itemView.findViewById(R.id.img_AddLoanCusImg);
            img_star1=(ImageView) itemView.findViewById(R.id.img_star1);
            img_star2=(ImageView) itemView.findViewById(R.id.img_star2);
            img_star3=(ImageView) itemView.findViewById(R.id.img_star3);
            img_star4=(ImageView) itemView.findViewById(R.id.img_star4);
            img_star5=(ImageView) itemView.findViewById(R.id.img_star5);
            img_star1.setVisibility(View.INVISIBLE);
            img_star2.setVisibility(View.INVISIBLE);
            img_star3.setVisibility(View.INVISIBLE);
            img_star4.setVisibility(View.INVISIBLE);
            img_star5.setVisibility(View.INVISIBLE);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout_addLoanCus);
        }
    }
}
