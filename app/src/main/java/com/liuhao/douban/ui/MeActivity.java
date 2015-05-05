package com.liuhao.douban.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.liuhao.douban.R;

/**
 * Created by liuhao on 2015/5/3.
 */
public class MeActivity extends Fragment {

    private TextView myTitle;
    private ListView mListView;
    private static final String[] itemTitleArray = new String[]{"我读", "我看", "我听", "我评", "我的日记", "我的资料", "小组"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.me, null);
        myTitle = (TextView) view.findViewById(R.id.myTitle);
        myTitle.setText("我的豆瓣");

        mListView = (ListView) view.findViewById(R.id.melistview);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.me_item, R.id.item_title, itemTitleArray));

        return view;


    }
}
