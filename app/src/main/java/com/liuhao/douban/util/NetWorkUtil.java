package com.liuhao.douban.util;

import android.content.Context;

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

    public static boolean isNeedCaptcha(Context context) throws Exception {
        String loginUrl = context.getResources().getString(R.string.loginUrl);
        URL url = new URL(loginUrl);
        URLConnection conn = url.openConnection();
        Source source = new Source(conn);
        List<Element> elements = source.getAllElements("input");
        for (Element element : elements) {
            if (element.getAttributeValue("name").equals("captcha-id")) {
                return true;
            }
        }
        return false;
    }
}
