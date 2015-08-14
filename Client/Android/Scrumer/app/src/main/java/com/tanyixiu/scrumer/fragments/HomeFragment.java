package com.tanyixiu.scrumer.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanyixiu.scrumer.R;
import com.yalantis.phoenix.PullToRefreshView;

public class HomeFragment extends Fragment{

    private PullToRefreshView mPullToRefreshView;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);
        return  rootView;
    }

    private void initView(View rootView) {
        mPullToRefreshView = (PullToRefreshView) rootView.findViewById(R.id.home_pulltorefresh);
        initViewEvent();
    }

    private void initViewEvent() {
        mPullToRefreshView.setOnRefreshListener(mOnRefreshListener);
    }


    private PullToRefreshView.OnRefreshListener mOnRefreshListener
            = new PullToRefreshView.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPullToRefreshView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPullToRefreshView.setRefreshing(false);
                }
            },2000);
        }
    };
}
