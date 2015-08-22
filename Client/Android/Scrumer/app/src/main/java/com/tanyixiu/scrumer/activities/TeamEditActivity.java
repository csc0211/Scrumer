package com.tanyixiu.scrumer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.data.UrlHelper;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.models.Team;
import com.tanyixiu.scrumer.models.TeamUser;
import com.tanyixiu.scrumer.util.CommonUtils;
import com.tanyixiu.scrumer.util.StringHelper;
import com.tanyixiu.scrumer.util.ToastUtil;
import com.tanyixiu.widgets.LoadingDialog;

import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TeamEditActivity extends BaseActivity {

    private ViewHolder mHolder;

    public static void startActivityForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, TeamEditActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_team_edit, null);
        setContentView(rootView);
        init(rootView);
    }

    private void init(View rootView) {
        mHolder = new ViewHolder(rootView);

        mHolder.mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mHolder.mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mHolder.mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveClick();
            }
        });
    }

    private void doSaveClick() {
        String name = String.valueOf(mHolder.mEtName.getText());
        String description = String.valueOf(mHolder.mEtDescription.getText());
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showLong(R.string.teamedit_toast_name_not_null);
            return;
        }
        saveTeam(name, description);
    }

    private void saveTeam(String name, String description) {
        Team team = new Team();
        String teamId = String.valueOf(UUID.randomUUID());
        team.setId(teamId);
        team.setName(name);
        team.setDescription(description);
        team.setCreatorId(App.getLoginUser().getId());
        team.setCreateTime(StringHelper.getCurrentTime());

        TeamUser teamUser = new TeamUser();
        teamUser.setId(String.valueOf(UUID.randomUUID()));
        teamUser.setTeamId(teamId);
        teamUser.setUserId(App.getLoginUser().getId());
        teamUser.setJoinTime(StringHelper.getCurrentTime());

        String param = SqlHelper.insertTeamSql(team) + ";" + SqlHelper.insertTeamUserSql(teamUser);
        String url = UrlHelper.initWriteUrl(param);

        setLoading(true);
        executeRequest(new StringRequest(url, responseListener(team, teamUser), errorListener()));
    }

    private Response.Listener<String> responseListener(final Team team, final TeamUser teamUser) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                team.save();
                teamUser.save();
                setLoading(false);
                Intent data = new Intent();
                data.putExtra("team", team);
                setResult(RESULT_OK, data);
                finish();
            }
        };
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'activity_team_edit.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    protected static class ViewHolder {
        @InjectView(R.id.teamedit_img_back)
        ImageView mImgBack;
        @InjectView(R.id.teamedit_et_name)
        EditText mEtName;
        @InjectView(R.id.teamedit_et_descirption)
        EditText mEtDescription;
        @InjectView(R.id.teamedit_btn_cancel)
        Button mBtnCancel;
        @InjectView(R.id.teamedit_btn_save)
        Button mBtnSave;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
