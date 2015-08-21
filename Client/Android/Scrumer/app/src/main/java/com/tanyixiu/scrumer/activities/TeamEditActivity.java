package com.tanyixiu.scrumer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.models.Team;
import com.tanyixiu.scrumer.models.TeamUser;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.StringHelper;
import com.tanyixiu.widgets.CircularProgressDialog;

import org.w3c.dom.Text;

import java.util.UUID;

public class TeamEditActivity extends BaseActivity {

    private TextView mTvName;
    private TextView mTvDescription;

    public static void startActivty(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, TeamEditActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_team_edit, null);
        setContentView(rootView);

        initView(rootView);
    }

    private void initView(View rootView) {
        mTvName = (TextView) rootView.findViewById(R.id.teamedit_ed_name);
        mTvDescription = (TextView) rootView.findViewById(R.id.teamedit_ed_descirption);
    }

    public void doSaveClick(View view) {

        String name = String.valueOf(mTvName.getText());
        String description = String.valueOf(mTvDescription.getText());
        if (TextUtils.isEmpty(name)) {
            CommonUtils.showToast("团队名称不能为空");
            return;
        }

        saveTeam(name, description);

    }

    public void doCanceClick(View view) {
        this.finish();
    }

    private void saveTeam(String name, String description) {

        final CircularProgressDialog dialog = CircularProgressDialog.show(TeamEditActivity.this);

        final Team team = new Team();
        String teamid = String.valueOf(UUID.randomUUID());
        team.setId(teamid);
        team.setName(name);
        team.setDescription(description);
        team.setCreatorId(App.getLoginUser().getId());
        team.setCreateTime(StringHelper.getCurrentTime());

        final TeamUser teamUser = new TeamUser();
        teamUser.setId(String.valueOf(UUID.randomUUID()));
        teamUser.setTeamId(teamid);
        teamUser.setUserId(App.getLoginUser().getId());
        teamUser.setJoinTime(StringHelper.getCurrentTime());

        String sql = SqlHelper.insertTeamSql(team) + ";" + SqlHelper.insertTeamUserSql(teamUser);
        VolleyHelper.requestServer(sql, new VolleyHelper.RequestListener() {
            @Override
            public void onResponse(Object o) {
                dialog.dismiss();
                team.save();
                teamUser.save();
                Intent data = new Intent();
                data.putExtra("team", team);
                setResult(RESULT_OK, data);
                finish();
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();
                CommonUtils.showToast("保存失败");
            }
        });
    }
}
