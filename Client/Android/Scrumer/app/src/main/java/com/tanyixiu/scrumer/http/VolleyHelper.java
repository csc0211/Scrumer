package com.tanyixiu.scrumer.http;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tanyixiu.scrumer.App;

import java.net.URLEncoder;

/**
 * Created by Mimo on 2015/8/18.
 */
public class VolleyHelper {

    private static final String SERVER_ADDRESS = "http://192.168.0.104/scrumer/web/index.php/store/sql?sql=%s";

    public interface RequestListener<T> extends Response.Listener<T>, Response.ErrorListener {

    }

    public static void requestServer(String param, RequestListener listener) {
        try {
            param = URLEncoder.encode(param, "UTF-8");
            String url = String.format(SERVER_ADDRESS, param);
            Log.d("TEST", url);
            StringRequest request = new StringRequest(url, listener, listener);
            App.getRequestQueue().add(request);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onErrorResponse(new VolleyError(e.getMessage()));
        }
    }
}
