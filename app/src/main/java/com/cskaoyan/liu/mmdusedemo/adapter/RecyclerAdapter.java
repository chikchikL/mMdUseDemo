package com.cskaoyan.liu.mmdusedemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cskaoyan.liu.mmdusedemo.R;
import com.cskaoyan.liu.mmdusedemo.entity.RecyclerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu on 2016/11/6.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RecyclerBean> mList;

    public RecyclerAdapter(Context context, ArrayList<RecyclerBean> mList){
        this.context=context;
        this.mList=mList;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        RecyclerBean recyclerBean = mList.get(position);
        holder.ivMain.setBackgroundResource(recyclerBean.getImg());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //内部类ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivMain;

        public int position;

        public ViewHolder(View v) {
            super(v);
            ivMain = (ImageView) v.findViewById(R.id.ivMain);
        }
    }
}
