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
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.adapters.ProjectListViewAdapter;
import com.tanyixiu.scrumer.data.UrlHelper;
import com.tanyixiu.scrumer.models.Project;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.util.JsonHelper;
import com.tanyixiu.scrumer.util.ToastUtil;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProjectActivity extends BaseActivity {

    private static final int REQUEST_CODE = 0;
    private String mTeamId;
    private ViewHolder mHolder;
    private static final String KEY_PARAM = "TEAMID";
    private ProjectListViewAdapter mProjectListViewAdapter;

    public static void startActivity(Context context, String teamId) {
        Intent intent = new Intent(context, ProjectActivity.class);
        intent.putExtra(KEY_PARAM, teamId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_project, null);
        setContentView(rootView);
        init(rootView);
        requestData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE != requestCode || RESULT_OK != resultCode) {
            return;
        }
        Project project = data.getParcelableExtra("project");
        mProjectListViewAdapter.addItem(project);
        mProjectListViewAdapter.notifyDataSetChanged();
    }

    private void init(View rootView) {
        mHolder = new ViewHolder(rootView);
        mTeamId = getIntent().getStringExtra(KEY_PARAM);
        mProjectListViewAdapter = new ProjectListViewAdapter(this, null);
        mHolder.mtListview.setAdapter(mProjectListViewAdapter);
        mProjectListViewAdapter.setRefreshView(mHolder.mRefreshView);

        mHolder.mtListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Project project = mProjectListViewAdapter.getItem(position);
                StoryActivity.startActivity(ProjectActivity.this, project);
            }
        });

        mHolder.mImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectEditActivity.startActivityForResult(ProjectActivity.this, REQUEST_CODE, mTeamId);
            }
        });
    }

    private void requestData() {
        String param = SqlHelper.getProjects(mTeamId);
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
                    List<Project> projects = JsonHelper.toEntities(s, Project.class);
                    mProjectListViewAdapter.addItems(projects);
                    mProjectListViewAdapter.notifyDataSetChanged();
                } catch (Exception ex) {
                    ToastUtil.showLong(R.string.toast_parseerror);
                }
            }
        };
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'activity_project.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    protected static class ViewHolder {
        @InjectView(R.id.project_img_add)
        ImageView mImgAdd;
        @InjectView(R.id.project_listview)
        ListView mtListview;
        @InjectView(R.id.project_pulltorefresh)
        PullToRefreshView mRefreshView;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
