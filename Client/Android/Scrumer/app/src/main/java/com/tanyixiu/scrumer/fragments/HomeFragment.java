package com.tanyixiu.scrumer.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.TestData;
import com.tanyixiu.scrumer.activities.StoryDetailActivity;
import com.tanyixiu.scrumer.adapters.StoryListViewAdapter;
import com.tanyixiu.scrumer.entities.Story;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class HomeFragment extends Fragment{

    private View                    mRefreshView;
    private ListView                mListView;
    private CircularProgressBar     mCircularProgressBar;
    private FloatingActionButton    mFloatingActionButton;
    private StoryListViewAdapter    mStoryListViewAdapter;

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
        initViewEvent();
        requestData();
        return  rootView;
    }

    private void initView(View rootView) {
        mRefreshView            = rootView.findViewById(R.id.home_pulltorefresh);
        mListView               = (ListView) rootView.findViewById(R.id.home_listview);
        mCircularProgressBar    = (CircularProgressBar) rootView.findViewById(R.id.home_cpb);
        mFloatingActionButton   = (FloatingActionButton) rootView.findViewById(R.id.home_fab);
        mStoryListViewAdapter   = new StoryListViewAdapter(getActivity(),null);
        mListView.setAdapter(mStoryListViewAdapter);
    }

    private void initViewEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initListViewData(List<Story> stories){
        mStoryListViewAdapter.AddItems(stories);
        mStoryListViewAdapter.notifyDataSetChanged();
        mStoryListViewAdapter.setRefreshView(mRefreshView);
    }

    private void requestData() {
        new HomeAsyncTask().execute();
    }

    private void toggleLoading(boolean isLoading){
        mCircularProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        mFloatingActionButton.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        mListView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    class HomeAsyncTask extends AsyncTask<Void,Void,List<Story>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            toggleLoading(true);
        }

        @Override
        protected List<Story> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return TestData.loadStorys(0);
        }

        @Override
        protected void onPostExecute(List<Story> stories) {
            super.onPostExecute(stories);
            toggleLoading(false);
            initListViewData(stories);
        }
    }
}