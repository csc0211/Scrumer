package com.tanyixiu.scrumer.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Mimo on 2015/8/17.
 */
public class HttpHelper {

    public static final String SERVER_ADDRESS = "http://192.168.0.104/scrumer/web/index.php/store/sql?sql=%s";

    public interface HttpCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }

    public static void sendHttpRequest(final String param,
                                       final HttpCallbackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    String address = String.format(SERVER_ADDRESS, URLEncoder.encode("select * from user", "UTF-8"));
                    Log.d("TEST", address);
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while (null != (line = reader.readLine())) {
                        response.append(line);
                    }
                    if (null != listener) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (null != listener) {
                        listener.onError(e);
                    }
                } finally {
                    if (null != connection) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
