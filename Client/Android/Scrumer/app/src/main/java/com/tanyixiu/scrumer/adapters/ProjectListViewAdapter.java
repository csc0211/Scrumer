package com.tanyixiu.scrumer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.models.Project;
import com.tanyixiu.widgets.CircleImageView;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mimo on 2015/8/17.
 */
public class ProjectListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Project> mProjects;
    private PullToRefreshView mPullToRefreshView;

    public ProjectListViewAdapter(Context context, List<Project> projects) {
        this.mContext = context;
        this.mProjects = projects;
    }

    public void setRefreshView(View refreshView) {
        this.mPullToRefreshView = (PullToRefreshView) refreshView;
        this.mPullToRefreshView.setOnRefreshListener(mOnRefreshListener);
    }

    public void addItems(List<Project> projects) {
        if (null == projects || 0 == projects.size()) {
            return;
        }
        if (null == mProjects) {
            mProjects = new ArrayList<>();
        }
        mProjects.addAll(projects);
    }

    public void addItem(Project project) {
        if (null == project) {
            return;
        }
        if (null == mProjects) {
            mProjects = new ArrayList<>();
        }
        mProjects.add(project);
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
            }, 2000);
        }
    };

    @Override
    public int getCount() {
        if (null == mProjects) {
            return 0;
        }
        return mProjects.size();
    }

    @Override
    public Project getItem(int position) {
        if (null == mProjects || 0 == mProjects.size()) {
            return null;
        }
        return mProjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProjectViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.project_item, null);
            holder = new ProjectViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ProjectViewHolder) convertView.getTag();
        }

        Project project = getItem(position);
        holder.bindData(project);
        return convertView;
    }

    class ProjectViewHolder {
        private TextView tvName;
        private TextView tvDescription;
        private CircleImageView mCircleImageView;

        public ProjectViewHolder(View convertView) {
            this.tvName = (TextView) convertView.findViewById(R.id.projectitem_tv_name);
            this.tvDescription = (TextView) convertView.findViewById(R.id.projectitem_tv_description);
            this.mCircleImageView = (CircleImageView) convertView.findViewById(R.id.projectitem_img);
        }

        public void bindData(Project project) {
            this.tvName.setText(project.getName());
            this.tvDescription.setText(project.getDescription());
        }
    }
}
