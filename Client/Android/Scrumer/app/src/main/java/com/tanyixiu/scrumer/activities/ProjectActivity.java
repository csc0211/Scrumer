package com.tanyixiu.scrumer.activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.fragments.ProjectListFragment;

public class ProjectActivity extends BaseActivity {

    private String mTeamId;
    private static final String KEY_PARAM = "TEAMID";

    public static void startActivity(Context context, String teamId) {
        Intent intent = new Intent(context, ProjectActivity.class);
        intent.putExtra(KEY_PARAM, teamId);
        context.startActivity(intent);
    }

    public String getTeamId() {
        return mTeamId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        mTeamId = getIntent().getStringExtra(KEY_PARAM);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.project_fragment, new ProjectListFragment())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
