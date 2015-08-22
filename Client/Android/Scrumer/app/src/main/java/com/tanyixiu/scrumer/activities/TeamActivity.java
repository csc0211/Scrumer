package com.tanyixiu.scrumer.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.adapters.TeamListViewAdapter;
import com.tanyixiu.scrumer.data.UrlHelper;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.models.Team;
import com.tanyixiu.scrumer.util.JsonHelper;
import com.tanyixiu.scrumer.util.ToastUtil;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TeamActivity extends BaseActivity {

    private ViewHolder mHolder;
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
        init(rootView);
        requestData();
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

    private void init(View rootView) {
        mHolder = new ViewHolder(rootView);
        mHolder.mImgAddTeam = (ImageView) rootView.findViewById(R.id.team_img_addteam);
        mHolder.mListview = (ListView) rootView.findViewById(R.id.team_listview);
        mHolder.mRefreshview = (PullToRefreshView) rootView.findViewById(R.id.team_refreshview);
        mTeamListViewAdapter = new TeamListViewAdapter(this, null);
        mTeamListViewAdapter.setRefreshView(mHolder.mRefreshview);
        mHolder.mListview.setAdapter(mTeamListViewAdapter);

        mHolder.mImgAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamEditActivity.startActivityForResult(TeamActivity.this, REQUEST_CODE);
            }
        });

        mHolder.mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Team team = mTeamListViewAdapter.getItem(position);
                ProjectActivity.startActivity(TeamActivity.this, team.getId());
            }
        });
    }

    private void requestData() {
        String param = SqlHelper.getTeams(App.getLoginUser().getId());
        String url = UrlHelper.initReadUrl(param);
        setLoading(true);
        executeRequest(new StringRequest(url, responseListener(), errorListener()));
    }

    private Response.Listener<String> responseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                setLoading(false);
                try {
                    List<Team> teams = JsonHelper.toEntities(s, Team.class);
                    mTeamListViewAdapter.addItems(teams);
                    mTeamListViewAdapter.notifyDataSetChanged();
                } catch (Exception ex) {
                    ToastUtil.showLong(R.string.team_toast_parseerror);
                }
            }
        };
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'activity_team.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.team_img_addteam)
        ImageView mImgAddTeam;
        @InjectView(R.id.team_listview)
        ListView mListview;
        @InjectView(R.id.team_refreshview)
        PullToRefreshView mRefreshview;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
