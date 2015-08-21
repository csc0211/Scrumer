package com.tanyixiu.scrumer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.activities.StoryDetailActivity;
import com.tanyixiu.scrumer.adapters.StoryListViewAdapter;
import com.tanyixiu.scrumer.entities.Story;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.JsonHelper;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class StoryListFragment extends Fragment {

    private View mRootView;
    private View mRefreshView;
    private ListView mListView;
    private CircularProgressBar mCircularProgressBar;
    private StoryListViewAdapter mStoryListViewAdapter;

    private String mStateId;
    private String mProjectId;

    public static StoryListFragment newInstance(String projectId, String stateId) {
        StoryListFragment fragment = new StoryListFragment();
        fragment.setProjectId(projectId);
        fragment.setStateId(stateId);
        return fragment;
    }

    public void setProjectId(String projectId) {
        mProjectId = projectId;
    }

    public void setStateId(String stateId) {
        mStateId = stateId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null != mRootView) {
            return mRootView;
        }
        mRootView = inflater.inflate(R.layout.fragment_story_list, container, false);
        initView(mRootView);
        initViewEvent();
        requestData();
        return mRootView;
    }

    private void initView(View rootView) {
        mRefreshView = rootView.findViewById(R.id.storylist_pulltorefresh);
        mListView = (ListView) rootView.findViewById(R.id.storylist_listview);
        mCircularProgressBar = (CircularProgressBar) rootView.findViewById(R.id.storylist_cpb);
        mStoryListViewAdapter = new StoryListViewAdapter(getActivity(), null);
        mListView.setAdapter(mStoryListViewAdapter);
    }

    private void initViewEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Story story = mStoryListViewAdapter.getItem(position);
                StoryDetailActivity.startActivity(getActivity(), story);
            }
        });
    }

    private void initListViewData(List<Story> stories) {
        mStoryListViewAdapter.AddItems(stories);
        mStoryListViewAdapter.notifyDataSetChanged();
        mStoryListViewAdapter.setRefreshView(mRefreshView);
    }

    private void toggleLoading(boolean isLoading) {
        mCircularProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        mListView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    private void requestData() {
        toggleLoading(true);
        String sql = SqlHelper.getStories(mProjectId, mStateId);
        VolleyHelper.requestServer(sql, new VolleyHelper.RequestListener<String>() {
            @Override
            public void onResponse(String s) {
                toggleLoading(false);
                try {
                    List<Story> stories = JsonHelper.toEntities(s, Story.class);
                    if (null == stories || 0 == stories.size()) {
                        return;
                    }
                    initListViewData(stories);
                } catch (Exception ex) {
                    CommonUtils.showToast(ex.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                toggleLoading(false);
                CommonUtils.showToast(volleyError.getMessage());
            }
        });

    }
}