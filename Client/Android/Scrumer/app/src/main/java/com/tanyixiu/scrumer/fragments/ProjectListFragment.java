package com.tanyixiu.scrumer.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.melnykov.fab.FloatingActionButton;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.activities.ProjectActivity;
import com.tanyixiu.scrumer.activities.ProjectEditActivity;
import com.tanyixiu.scrumer.activities.StoryActivity;
import com.tanyixiu.scrumer.adapters.ProjectListViewAdapter;
import com.tanyixiu.scrumer.entities.Project;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.JsonHelper;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class ProjectListFragment extends Fragment {

    private View mRootView;
    private View mRefreshView;
    private ListView mProjectListView;
    private CircularProgressBar mCircularProgressBar;
    private FloatingActionButton mFloatingActionButton;
    private ProjectListViewAdapter mProjectListViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null != mRootView) {
            return mRootView;
        }
        mRootView = inflater.inflate(R.layout.fragment_project_list, container, false);
        initView();
        initViewEvent();
        requestData();
        return mRootView;
    }

    private void initView() {
        mRefreshView = mRootView.findViewById(R.id.projectlist_pulltorefresh);
        mProjectListView = (ListView) mRootView.findViewById(R.id.projectlist_listview);
        mFloatingActionButton = (FloatingActionButton) mRootView.findViewById(R.id.projectlist_fab);
        mCircularProgressBar = (CircularProgressBar) mRootView.findViewById(R.id.projectlist_cpb);
        mProjectListViewAdapter = new ProjectListViewAdapter(getActivity(), null);
        mProjectListView.setAdapter(mProjectListViewAdapter);
    }

    private void initViewEvent() {
        mProjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Project project = mProjectListViewAdapter.getItem(position);
                StoryActivity.startActivity(getActivity(), project);
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProjectEditActivity.class);
                startActivity(intent);
            }
        });
    }

    private void requestData() {
        toggleLoading(true);
        String sql = SqlHelper.getProjects(((ProjectActivity) getActivity()).getTeamId());
        VolleyHelper.requestServer(sql, new VolleyHelper.RequestListener<String>() {
            @Override
            public void onResponse(String s) {
                toggleLoading(false);
                List<Project> projects = JsonHelper.toEntities(s, Project.class);
                bindData(projects);
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                toggleLoading(false);
                CommonUtils.showToast(volleyError.getMessage());
            }
        });
    }

    private void bindData(List<Project> projects) {
        mProjectListViewAdapter.AddItems(projects);
        mProjectListViewAdapter.notifyDataSetChanged();
        mProjectListViewAdapter.setRefreshView(mRefreshView);
    }

    private void toggleLoading(boolean isLoading) {
        mCircularProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        mProjectListView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        mFloatingActionButton.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }
}