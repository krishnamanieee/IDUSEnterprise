package com.rohasoft.idus.idus_enterprise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rohasoft.idus.idus_enterprise.R;

import java.util.List;

/**
 * Created by krish on 12/21/2017.
 */

public class AdapterPay extends BaseAdapter {
    Context mContext;
    List<PayDetails> list;

    public AdapterPay(Context mContext, List<PayDetails> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView_loanterm,textView_date,textView_amt,textView_pending,textView_extra;
        if (view == null) {
            view = LayoutInflater.from(mContext).
                    inflate(R.layout.item_paydetail, viewGroup, false);
        }

        PayDetails payDetails=list.get(i);

        textView_loanterm=(TextView) view.findViewById(R.id.ipd_loanTerm);
        textView_date=(TextView) view.findViewById(R.id.ipd_date);
        textView_amt=(TextView) view.findViewById(R.id.ipd_amt);
        textView_pending=(TextView) view.findViewById(R.id.ipd_pending);
        textView_extra=(TextView) view.findViewById(R.id.ipd_extra);

        textView_loanterm.setText(payDetails.getLoanTerm());
        textView_date.setText(payDetails.getPaidDate());
        textView_amt.setText(payDetails.getPaidAmt());
        textView_pending.setText(payDetails.getPending());
        textView_extra.setText(payDetails.getExtra());


        return view;
    }
}
