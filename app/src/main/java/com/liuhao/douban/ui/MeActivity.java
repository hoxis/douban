package com.liuhao.douban.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.liuhao.douban.R;

/**
 * Created by liuhao on 2015/5/3.
 */
public class MeActivity extends Fragment implements AdapterView.OnItemClickListener {

    private TextView myTitle;
    private ListView mListView;
    private SharedPreferences sp;
    private static final String[] itemTitleArray = new String[]{"我读", "我看", "我听", "我评", "我的日记", "我的资料", "小组"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.me, null);
        myTitle = (TextView) view.findViewById(R.id.myTitle);
        myTitle.setText("我的豆瓣");
        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);

        mListView = (ListView) view.findViewById(R.id.melistview);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.me_item, R.id.item_title, itemTitleArray));

        mListView.setOnItemClickListener(this);

        return view;
    }

    /**
     * 判断用户是否已授权
     *
     * @return
     */
    private boolean isUserAuthorized() {
        String apiKey = sp.getString("apiKey", null);
        String accessToken = sp.getString("accessToken", null);
        if (apiKey == null || accessToken == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(isUserAuthorized()){
            //进入到对应界面

        }else{
            //定向到登录界面 LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
