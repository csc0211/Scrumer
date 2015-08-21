package com.tanyixiu.scrumer.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.adapters.TeamListViewAdapter;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.models.Team;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.JsonHelper;
import com.tanyixiu.widgets.CircularProgressDialog;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

public class TeamActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mImgAddTeam;
    private ListView mTeamListView;
    private PullToRefreshView mRefreshView;
    private TeamListViewAdapter mTeamListViewAdapter;

    private static final int REQUEST_CODE = 0;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TeamActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_team, null);
        setContentView(rootView);
        initView(rootView);
        initViewEvent();
        requestData();
    }

    private void initView(View rootView) {
        mImgAddTeam = (ImageView) rootView.findViewById(R.id.team_img_addteam);
        mTeamListView = (ListView) rootView.findViewById(R.id.team_listview);
        mRefreshView = (PullToRefreshView) rootView.findViewById(R.id.team_refreshview);
        mTeamListViewAdapter = new TeamListViewAdapter(this, null);
        mTeamListViewAdapter.setRefreshView(mRefreshView);
        mTeamListView.setAdapter(mTeamListViewAdapter);
    }

    private void initViewEvent() {
        mImgAddTeam.setOnClickListener(this);
        mTeamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Team team = mTeamListViewAdapter.getItem(position);
                ProjectActivity.startActivity(TeamActivity.this, team.getId());
            }
        });
    }

    private void requestData() {
        final CircularProgressDialog dialog = CircularProgressDialog.show(this);

        String sql = SqlHelper.getTeams(App.getLoginUser().getId());
        VolleyHelper.requestServer(sql, new VolleyHelper.RequestListener<String>() {
            @Override
            public void onResponse(String s) {
                dialog.dismiss();
                List<Team> teams = null;
                try {
                    teams = JsonHelper.toEntities(s, Team.class);
                } catch (Exception ex) {
                    CommonUtils.showToast("数据解析错误");
                }
                mTeamListViewAdapter.addItems(teams);
                mTeamListViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();
                CommonUtils.showToast(volleyError.getMessage());
            }
        });
    }


    private void addTeamClick() {
        TeamEditActivity.startActivty(TeamActivity.this, REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.team_img_addteam:
                addTeamClick();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            Team newTeam = data.getParcelableExtra("team");
            mTeamListViewAdapter.addItem(newTeam);
            mTeamListViewAdapter.notifyDataSetChanged();
        }
    }
}
