package com.liuhao.douban.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhao.douban.R;

/**
 * Created by liuhao on 2015/5/3.
 */
public class SearchActivity extends Fragment {

    private TextView myTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me, null);
        myTitle = (TextView) view.findViewById(R.id.myTitle);
        myTitle.setText("搜索");

        return view;
    }
}
