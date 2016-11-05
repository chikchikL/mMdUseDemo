package com.cskaoyan.liu.mmdusedemo.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cskaoyan.liu.mmdusedemo.R;

/**
 * Created by Liu on 2016/11/5.
 */

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main, container, false);


        return inflate;
    }
}
