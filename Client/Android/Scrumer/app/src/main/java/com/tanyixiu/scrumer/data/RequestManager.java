package com.tanyixiu.scrumer.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tanyixiu.scrumer.App;

/**
 * Created by Mimo on 2015/8/21.
 */
public class RequestManager {

    private static RequestQueue sRequestQueue = Volley.newRequestQueue(App.getContext());

    private RequestManager() {

    }

    public static void addRequest(Request<?> request, Object tag) {
        if (null != tag) {
            request.setTag(tag);
        }
        sRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        sRequestQueue.cancelAll(tag);
    }
}
