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

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tanyixiu.scrumer.App;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.data.UrlHelper;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.models.Project;
import com.tanyixiu.scrumer.models.Team;
import com.tanyixiu.scrumer.models.TeamUser;
import com.tanyixiu.scrumer.util.StringHelper;
import com.tanyixiu.scrumer.util.ToastUtil;

import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProjectEditActivity extends BaseActivity {

    private static final String KEY_PARAM_TEAMID = "TEAMID";
    private ViewHolder mHolder;
    private String mTeamId;

    public static void startActivityForResult(Activity activity, int requestCode, String teamId) {
        Intent intent = new Intent(activity, ProjectEditActivity.class);
        intent.putExtra(KEY_PARAM_TEAMID, teamId);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_project_edit, null);
        setContentView(rootView);
        init(rootView);
    }

    private void init(View rootView) {
        mHolder = new ViewHolder(rootView);
        mTeamId = getIntent().getStringExtra(KEY_PARAM_TEAMID);
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

    public void doSaveClick() {
        String name = mHolder.mEtName.getText().toString();
        String description = mHolder.mEtDescirption.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ToastUtil.showLong(R.string.projectedit_toast_name_notnull);
            return;
        }

        saveProject(name, description);
    }

    private void saveProject(String name, String description) {
        Project project = new Project();
        project.setId(String.valueOf(UUID.randomUUID()));
        project.setTeamId(mTeamId);
        project.setName(name);
        project.setDescription(description);
        project.setCreatorId(App.getLoginUser().getId());
        project.setCreateTime(StringHelper.getCurrentTime());
        project.setState(0);
        project.setSectionId("1");

        String param = SqlHelper.insertProjectSql(project);
        String url = UrlHelper.initWriteUrl(param);
        setLoading(true);
        executeRequest(new StringRequest(url, responseListener(project), errorListener()));
    }

    private Response.Listener<String> responseListener(final Project project) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                project.save();
                setLoading(false);
                Intent data = new Intent();
                data.putExtra("project", project);
                setResult(RESULT_OK, data);
                finish();
            }
        };
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'activity_project_edit.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    protected static class ViewHolder {
        @InjectView(R.id.projectedit_img_back)
        ImageView mImgBack;
        @InjectView(R.id.projectedit_et_name)
        EditText mEtName;
        @InjectView(R.id.projectedit_et_descirption)
        EditText mEtDescirption;
        @InjectView(R.id.projectedit_btn_cancel)
        Button mBtnCancel;
        @InjectView(R.id.projectedit_btn_save)
        Button mBtnSave;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
