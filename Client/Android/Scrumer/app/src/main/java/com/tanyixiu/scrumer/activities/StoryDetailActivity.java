package com.tanyixiu.scrumer.activities;

import android.os.Bundle;
import android.view.View;

import com.tanyixiu.scrumer.R;

/**
 * Created by Mimo on 2015/8/15.
 */
public class StoryDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
    }

    public void onBackCliked(View v) {
        this.finish();
    }

    public void doEditClick(View v) {
    }
}
