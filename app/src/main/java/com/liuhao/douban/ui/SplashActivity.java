package com.liuhao.douban.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liuhao.douban.R;
import com.liuhao.douban.util.NetWorkUtil;

/**
 * Created by liuhao on 2015/5/2.
 */
public class SplashActivity extends Activity{

    private static final String TAG = "SplashActivity";
    private TextView versionNumber;
    private LinearLayout splashLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        versionNumber = (TextView) this.findViewById(R.id.versionNumber);
        versionNumber.setText(getVersion());
        splashLinearLayout = (LinearLayout) this.findViewById(R.id.SplashLinearLayout);

        //判断当前网络状态是否可用
        if(NetWorkUtil.isNetConnected(getApplicationContext())){
            //设置动画，进入主界面
            Toast.makeText(getApplicationContext(),"网络可用",Toast.LENGTH_SHORT).show();
            AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);//透明度变化动画类
            aa.setDuration(2000);
            splashLinearLayout.setAnimation(aa);
            splashLinearLayout.startAnimation(aa);
            new Handler().postDelayed(new LoadMainActivity(), 2000);
        }else{
            //提示用户进行网络连接
            showSetNetDialog();
        }

    }

    private class LoadMainActivity implements Runnable{

        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this,MainTabActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void showSetNetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("网络设置");
        builder.setMessage("亲，没有网啊，检查一下吧~");
        builder.setCancelable(false);
        builder.setPositiveButton("去设置吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent;
                //判断Android系统版本，不同版本的设置方式不同。
                // API大于10 就是3.0或以上版本
                if(android.os.Build.VERSION.SDK_INT > 10){
                    intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                }else{
                    intent = new Intent();
                    intent.setClassName("com.android.settings","com.android.settings.WirelessSettings");
                }
//                startActivityForResult(intent, 1);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("残忍拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Handler().postDelayed(new LoadMainActivity(), 0);
            }
        });
        builder.create().show();
    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断当前网络状态是否可用
        if(isNetConnected()){
            //设置动画，进入主界面
            Toast.makeText(getApplicationContext(),"网络可用",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new LoadMainActivity(), 2000);
        }else{
            //提示用户进行网络连接
            showSetNetDialog();
        }
        Log.i(TAG, "进入到onActivityResult");
    }
*/

    public String getVersion(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            return "version " + info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

}
