package com.tanyixiu.scrumer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.models.Team;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mimo on 2015/8/21.
 */
public class TeamListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Team> mTeams;
    private PullToRefreshView mPullToRefreshView;

    public TeamListViewAdapter(Context context, List<Team> teams) {
        mContext = context;
        mTeams = teams;
    }

    public void setRefreshView(View refreshView) {
        this.mPullToRefreshView = (PullToRefreshView) refreshView;
        this.mPullToRefreshView.setOnRefreshListener(mOnRefreshListener);
    }

    public void addItems(List<Team> teams) {
        if (null == teams || 0 == teams.size()) {
            return;
        }
        if (null == mTeams) {
            mTeams = new ArrayList<>();
        }
        mTeams.addAll(teams);
    }

    public void addItem(Team team) {
        if (null == team) {
            return;
        }
        if (null == mTeams) {
            mTeams = new ArrayList<>();
        }
        mTeams.add(team);
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
        if (null == mTeams) {
            return 0;
        }
        return mTeams.size();
    }

    @Override
    public Team getItem(int position) {
        if (null == mTeams || 0 == mTeams.size()) {
            return null;
        }
        return mTeams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeamViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.team_item, null);
            holder = new TeamViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (TeamViewHolder) convertView.getTag();
        }

        Team team = getItem(position);
        holder.bindData(team);
        return convertView;
    }


    class TeamViewHolder {

        private TextView tvName;

        public TeamViewHolder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.teamitem_tv_name);
        }

        public void bindData(Team team) {
            tvName.setText(team.getName());
        }
    }
}
