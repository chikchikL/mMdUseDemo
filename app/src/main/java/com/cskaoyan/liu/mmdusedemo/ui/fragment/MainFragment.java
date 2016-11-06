package com.cskaoyan.liu.mmdusedemo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.cskaoyan.liu.mmdusedemo.R;
import com.cskaoyan.liu.mmdusedemo.adapter.RecyclerAdapter;
import com.cskaoyan.liu.mmdusedemo.entity.RecyclerBean;
import com.cskaoyan.liu.mmdusedemo.listener.RecyclerItemClickListener;
import com.cskaoyan.liu.mmdusedemo.ui.activity.RecyclerDetailActivity;

import java.util.ArrayList;

/**
 * Created by Liu on 2016/11/5.
 */

public class MainFragment extends Fragment {
    private ArrayList<RecyclerBean> mList = new ArrayList<>();
    private String[] title = {"测试文字_01", "测试文字_02", "测试文字_03", "测试文字_04", "测试文字_05", "测试文字_06", "测试文字_07", "测试文字_08", "测试文字_09", "测试文字_10"};
    private int[] imgPath = {R.mipmap.ic_recyclerview_01, R.mipmap.ic_recyclerview_02, R.mipmap.ic_recyclerview_03, R.mipmap.ic_recyclerview_04, R.mipmap.ic_recyclerview_05,
            R.mipmap.ic_recyclerview_06, R.mipmap.ic_recyclerview_07, R.mipmap.ic_recyclerview_08, R.mipmap.ic_recyclerview_09, R.mipmap.ic_recyclerview_10};
    private int[] vpImgPath = {R.mipmap.ic_viewpager_01, R.mipmap.ic_viewpager_02, R.mipmap.ic_viewpager_03};
    private RecyclerView recyclerView;
    private RecyclerViewHeader header;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_main, null, false);
        //header = (RecyclerViewHeader) inflate.findViewById(R.id.header);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);

        //当每个item的高度确定时，设置此项可以避免重复计算每个item的size并且重复绘制，可以提高性能
        recyclerView.setHasFixedSize(true);

        //初始化并且绑定layoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //绑定header
        //header.attachTo(recyclerView);

        //初始化recyclerView需要的数据集list
        initDataList();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), mList);

        //给item设置点击响应监听器
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //当item响应点击事件时，需要将跳转的activity需要的数据带过去
                RecyclerBean main = mList.get(position);
                Intent intent = new Intent(getActivity(), RecyclerDetailActivity.class);
                intent.putExtra("main", main);

                //这里是实现Activity的切入动画
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                view.findViewById(R.id.ivMain),getActivity().getString(R.string.transition_book_img));

                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        }));

        //给recyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);


        return inflate;
    }

    private void initDataList() {
        mList.clear();
        for (int i = 0; i < title.length; i++) {
            RecyclerBean recyclerBean = new RecyclerBean();
            recyclerBean.setImg(imgPath[i]);
            recyclerBean.setInfo(title[i]);
            recyclerBean.setTitle(title[i]);
            recyclerBean.setCatalog(title[i]);
            recyclerBean.setAuthor_intro(title[i]);
            recyclerBean.setSummary(title[i]);
            recyclerBean.setImglarge(imgPath[i]);
            mList.add(recyclerBean);
        }
    }
}
