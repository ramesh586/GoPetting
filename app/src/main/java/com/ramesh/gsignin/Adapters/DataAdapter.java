package com.ramesh.gsignin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ramesh.gsignin.Model.Data;
import com.ramesh.gsignin.Model.SingleTon;
import com.ramesh.gsignin.R;

import java.util.List;

/**
 * Created by thrymr on 8/2/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<Data> dataList;
    private int rowLayout;
    private Activity context;
    private boolean isCart;

    public  class DataViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView endDate;
        ImageView imageIcon;
        Button addToCart;


        public DataViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.nameTv);
            endDate = (TextView) v.findViewById(R.id.endDateTv);
            imageIcon = (ImageView) v.findViewById(R.id.imgIcon);
            addToCart= (Button) v.findViewById(R.id.addCart);
            if(isCart)
                addToCart.setText("Remove Item");
        }
    }

    public DataAdapter(List<Data> dataList, int rowLayout, Activity context, boolean b) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
        isCart=b;
    }


    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        final Data data=dataList.get(position);
        if(data!=null) {
            holder.name.setText(data.getName());
            holder.endDate.setText(data.getEndDate());
            Glide.with(context).load(data.getIconURL())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageIcon);
            holder.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isCart){
                        if(SingleTon.getmInstance().getCartList().size()>0){
                            SingleTon.getmInstance().getCartList().remove(position);
//                            dataList.remove(position);
                           notifyDataSetChanged();
                        }else context.finish();
                    }else {
                        if(!SingleTon.getmInstance().getCartList().contains(data))
                        SingleTon.getmInstance().getCartList().add(data);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}