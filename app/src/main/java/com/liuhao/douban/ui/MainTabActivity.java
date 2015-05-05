package com.liuhao.douban.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.liuhao.douban.R;


public class MainTabActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class[] fragmentArray= {MeActivity.class, NewBookActivity.class, BookCommentActivity.class, SearchActivity.class, AboutActivity.class};

    //定义数组来存放按钮图片
    private int[] mImageViewArray = {R.drawable.tab_main_nav_me, R.drawable.tab_main_nav_book,
            R.drawable.detail_comment_selected, R.drawable.tab_main_nav_search,
            R.drawable.tab_main_nav_more
    };

    //Tab选项卡的文字  
    private String mTextViewArray[] = {"我的豆瓣", "新书","书评", "搜索", "关于"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_tab);
        initView();
    }

    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        int count = fragmentArray.length;

        for (int i=0; i<count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextViewArray[i]).setIndicator(getTabItem(i));
            //将Tab按钮添加到Tab选项卡中，同时设置相关联的界面
            mTabHost.addTab(tabSpec,fragmentArray[i],null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab_main_nav_selector);
        }
        //mTabHost.setCurrentTab(2);//设置启动时显示的tab
    }

    private View getTabItem(int i) {
        //得到选项卡的布局
        View view = layoutInflater.inflate(R.layout.tab_item, null);

        //设置显示图片
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[i]);

        //设置显示文字
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextViewArray[i]);

        return view;
    }


}
