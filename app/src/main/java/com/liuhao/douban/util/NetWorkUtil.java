package com.liuhao.douban.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.liuhao.douban.R;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by liuhao on 2015/5/12.
 */
public class NetWorkUtil {

    public static String isNeedCaptcha(Context context) throws Exception {
        String loginUrl = context.getResources().getString(R.string.loginUrl);
        URL url = new URL(loginUrl);
        URLConnection conn = url.openConnection();
        conn.getContent();
        Source source = new Source(conn);
        //筛选出<img>标签
        List<Element> elements = source.getAllElements("img");
        for (Element element : elements) {
            if ("captcha_image".equals(element.getAttributeValue("id"))) {
                String imgUrl = element.getAttributeValue("src");
                return imgUrl;
            }
        }

//        List<Element> elements = source.getAllElements("input");
//        for (Element element : elements) {
//            if (element.getAttributeValue("name").equals("captcha-id")) {
//                String imgId = element.getAttributeValue("value");
//                return imgId;
//            }
//        }

        return null;
    }


    /**
     * 判断网络是否已打开
     *
     * @param context
     * @return true 已打开  false 未打开
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
