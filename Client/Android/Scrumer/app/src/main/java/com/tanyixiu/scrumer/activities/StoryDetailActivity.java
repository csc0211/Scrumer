package com.tanyixiu.scrumer.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.melnykov.fab.FloatingActionButton;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.entities.State;
import com.tanyixiu.scrumer.entities.Story;
import com.tanyixiu.scrumer.entities.StoryDetail_X;
import com.tanyixiu.scrumer.entities.StoryUser;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.JsonHelper;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by Mimo on 2015/8/15.
 */
public class StoryDetailActivity extends BaseActivity {

    private static final String KEY_PARAM_STORY = "STORY";

    private Story mStory;

    private TextView mTvName;
    private TextView mTvState;
    private TextView mTvPriority;
    private TextView mTvDeveloper;
    private TextView mTvTester;
    private TextView mTvDescription;
    private LinearLayout mLinearLayoutDetail;
    private CircularProgressBar mCircularProgressBar;
    private FloatingActionButton mFloatingActionButton;


    public static void startActivity(Context context, Story story) {
        Intent intent = new Intent(context, StoryDetailActivity.class);
        intent.putExtra(KEY_PARAM_STORY, story);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_story_detail, null);
        setContentView(rootView);

        mStory = getIntent().getExtras().getParcelable(KEY_PARAM_STORY);

        initView(rootView);
        requestData();
    }

    private void initView(View rootView) {
        mTvName = (TextView) rootView.findViewById(R.id.storydetail_tv_name);
        mTvState = (TextView) rootView.findViewById(R.id.storydetail_tv_state);
        mTvPriority = (TextView) rootView.findViewById(R.id.storydetail_tv_priority);
        mTvDeveloper = (TextView) rootView.findViewById(R.id.storydetail_tv_developer);
        mTvTester = (TextView) rootView.findViewById(R.id.storydetail_tv_tester);
        mTvDescription = (TextView) rootView.findViewById(R.id.storydetail_tv_description);
        mCircularProgressBar = (CircularProgressBar) rootView.findViewById(R.id.storydetail_cpb);
        mFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.storydetail_fab);
        mLinearLayoutDetail = (LinearLayout) rootView.findViewById(R.id.storydetail_ll_detail);
        mTvName.setText(mStory.getName());
    }

    private void requestData() {
        toggleLoading(true);
        final StoryDetail_X storyDetail_x = new StoryDetail_X();
        storyDetail_x.setStory(mStory);


        try {
            String sql_state = SqlHelper.getStoryState(mStory.getStateId());
            VolleyHelper.requestServer(sql_state, new VolleyHelper.RequestListener<String>() {
                @Override
                public void onResponse(String s) {
                    State state = JsonHelper.toEntity(s, State.class);
                    storyDetail_x.setState(state);

                    String sql_user = SqlHelper.getStoryUser(mStory.getId());
                    VolleyHelper.requestServer(sql_user, new VolleyHelper.RequestListener<String>() {
                        @Override
                        public void onResponse(String s) {
                            toggleLoading(false);
                            List<StoryUser> storyUsers = JsonHelper.toEntities(s, StoryUser.class);
                            storyDetail_x.setStoryUsers(storyUsers);
                            initViewData(storyDetail_x);
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            volleyError.printStackTrace();
                            toggleLoading(false);
                            CommonUtils.showToast(volleyError.getMessage());
                        }
                    });
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyError.printStackTrace();
                    toggleLoading(false);
                    CommonUtils.showToast(volleyError.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtils.showToast(e.getMessage());
        }
    }

    private void initViewData(StoryDetail_X storyDetail) {
        mTvName.setText(storyDetail.getStory().getName());
        mTvState.setText(storyDetail.getState().getStateName());
        mTvPriority.setText(String.valueOf(storyDetail.getStory().getPriority()));//toname

//        if (story.getState() >= Story.StoryState.DEVING) {
//            mTvDeveloper.setText("Jane");
//        }
//        if (story.getState() >= Story.StoryState.TESTING) {
//            mTvTester.setText("Lily");
//        }

        mTvDescription.setText(storyDetail.getStory().getDescription());
    }

    private void toggleLoading(boolean isLoading) {
        mCircularProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        mFloatingActionButton.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        mLinearLayoutDetail.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }
}