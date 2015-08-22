package com.tanyixiu.scrumer.data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Mimo on 2015/8/21.
 */
public class UrlHelper {

    private static final String METHOD_READ = "sql?sql=%s";
    private static final String METHOD_WRITE = "update?sql=%s";
    private static final String SERVER_ADDRESS = "http://192.168.0.104/scrumer/web/index.php/store/";

    public static String initReadUrl(String param) {
        String subParam = "";
        try {
            subParam = URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format(SERVER_ADDRESS + METHOD_READ, subParam);
    }

    public static String initWriteUrl(String param) {
        String subParam = "";
        try {
            subParam = URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format(SERVER_ADDRESS + METHOD_WRITE, subParam);
    }
}
