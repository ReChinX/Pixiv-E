package com.rechinx.pixiv_e.support;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Chin on 2016/7/6.
 */
public class Params extends HashMap<String, String> {

    public String encode() {
        StringBuilder str = new StringBuilder();
        boolean first = true;
        Iterator iter = entrySet().iterator();
        while (iter.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) iter.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if (first) first = false;
            else str.append("&");
            try {
                str.append(URLEncoder.encode(key, "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(value.toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) { }
        }
        return str.toString();
    }

}
