package com.rohasoft.idus.idus_enterprise.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rohasoft.idus.idus_enterprise.R;
import com.rohasoft.idus.idus_enterprise.other.CollectLoanList;
import com.rohasoft.idus.idus_enterprise.other.HomeList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ayothi selvam on 09-11-2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<HomeList> homeLists;
    private Context context;

    public HomeAdapter(List<HomeList> homeLists, Context context) {
        this.homeLists = homeLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_collectloanitem,parent,false);

        return new HomeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HomeList list=homeLists.get(position);

        holder.textView_cusName.setText(list.getCusName());
        holder.textView_city.setText(list.getCity());
        holder.textView_phone.setText(list.getPhone());
        holder.textView_amount.setText(list.getAmount());
        holder.textView_date.setText(list.getDuePayDate());
        Picasso.with(context)
                .load("http://idusmarket.com/loan-app/app/images/"+list.getCusImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return homeLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_cusName,textView_city,textView_phone,textView_amount,textView_date;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_cusName=(TextView) itemView.findViewById(R.id.txt_homeloanlistcus);
            textView_city=(TextView) itemView.findViewById(R.id.txt_homeloanlistcity);
            textView_phone=(TextView) itemView.findViewById(R.id.txt_homeloanlistphone);
            textView_amount=(TextView) itemView.findViewById(R.id.txt_homeloanlistamt);
            textView_date=(TextView) itemView.findViewById(R.id.txt_homeloanlistdate);
            imageView=(ImageView) itemView.findViewById(R.id.img_homeitem);
        }
    }
}
