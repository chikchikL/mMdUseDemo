package com.cskaoyan.liu.mmdusedemo.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cskaoyan.liu.mmdusedemo.R;
import com.cskaoyan.liu.mmdusedemo.entity.RecyclerBean;

import java.util.ArrayList;

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

        //这里可以为每个item绑定加载动画
        ScaleInAnimation scaleInAnimation = new ScaleInAnimation(0.1f);

        for (Animator anim : scaleInAnimation.getAnimators(holder.cardView)) {
            anim.setDuration(300).start();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //内部类ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivMain;
        public CardView cardView;

        public int position;

        public ViewHolder(View v) {
            super(v);
            ivMain = (ImageView) v.findViewById(R.id.ivMain);
            cardView=(CardView)v.findViewById(R.id.card_item);
        }
    }


    //动画效果
    public class ScaleInAnimation extends BaseAnimation {

        private static final float DEFAULT_SCALE_FROM = .5f;
        private final float mFrom;

        public ScaleInAnimation() {
            this(DEFAULT_SCALE_FROM);
        }

        public ScaleInAnimation(float from) {
            mFrom = from;
        }

        @Override
        public Animator[] getAnimators(View view) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", mFrom, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", mFrom, 1f);
            return new ObjectAnimator[]{scaleX, scaleY};
        }
    }
    public abstract class BaseAnimation {
        public abstract Animator[] getAnimators(View view);
    }
}
