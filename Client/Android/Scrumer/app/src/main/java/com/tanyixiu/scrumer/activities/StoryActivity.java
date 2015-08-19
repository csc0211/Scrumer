package com.tanyixiu.scrumer.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.entities.Project;
import com.tanyixiu.scrumer.entities.State;
import com.tanyixiu.scrumer.fragments.StoryListFragment;
import com.tanyixiu.scrumer.http.SqlHelper;
import com.tanyixiu.scrumer.http.VolleyHelper;
import com.tanyixiu.scrumer.utils.CommonUtils;
import com.tanyixiu.scrumer.utils.JsonHelper;

import java.util.HashMap;
import java.util.List;

public class StoryActivity extends BaseActivity {

    private static final String KEY_PARAM = "PROJECT";

    private Project mProject;
    private HashMap<String, StoryListFragment> mFragments = new HashMap<>();
    private SectionPagerAdapter mSectionPagerAdapter;

    private View mRootView;
    private TextView mTvProjectName;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    public static void startActivity(Context context, Project project) {
        Intent intent = new Intent(context, StoryActivity.class);
        intent.putExtra(KEY_PARAM, project);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = LayoutInflater.from(this).inflate(R.layout.activity_story, null);
        setContentView(mRootView);
        initParam();
        initView();
        requestData();
    }

    private void initParam() {
        Intent intent = getIntent();
        mProject = intent.getParcelableExtra(KEY_PARAM);
    }

    private void initView() {
        mViewPager = (ViewPager) mRootView.findViewById(R.id.story_pager);
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.story_tab_layout);
        mTvProjectName = (TextView) mRootView.findViewById(R.id.story_tv_projectname);
        mTvProjectName.setText(mProject.getName());

        int normalColor = getResources().getColor(R.color.txt_color_white);
        int selectColor = getResources().getColor(R.color.txt_color_green_dark);
        mTabLayout.setTabTextColors(normalColor, selectColor);

    }

    private void requestData() {
        String sql = SqlHelper.getProjectState(mProject.getSectionId());
        VolleyHelper.requestString(sql, new VolleyHelper.RequestListener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    List<State> states = JsonHelper.toEntities(s, State.class);
                    if (null == states) {
                        return;
                    }
                    mSectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), states);
                    mViewPager.setAdapter(mSectionPagerAdapter);
                    mTabLayout.setupWithViewPager(mViewPager);
                } catch (Exception e) {
                    CommonUtils.showToast(e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                CommonUtils.showToast(volleyError.getMessage());
            }
        });
    }


    public class SectionPagerAdapter extends FragmentPagerAdapter {

        private List<State> mStates;

        public SectionPagerAdapter(FragmentManager fm, List<State> states) {
            super(fm);
            mStates = states;
        }

        private boolean isEmpty() {
            return null == mStates || 0 == mStates.size();
        }

        @Override
        public Fragment getItem(int position) {
            if (isEmpty()) {
                return null;
            }

            String stateId = mStates.get(position).getId();
            StoryListFragment storyListFragment = mFragments.get(stateId);
            if (null != storyListFragment) {
                return storyListFragment;
            }
            storyListFragment = StoryListFragment.newInstance(mProject.getId(), stateId);
            mFragments.put(stateId, storyListFragment);
            return storyListFragment;
        }

        @Override
        public int getCount() {
            if (isEmpty()) {
                return 0;
            }
            return mStates.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (isEmpty()) {
                return null;
            }
            return mStates.get(position).getStateName();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (isEmpty()) {
                return null;
            }
            StoryListFragment fg = (StoryListFragment) super.instantiateItem(container, position);
            if (null == fg) {
                return null;
            }

            fg.setProjectId(mProject.getId());
            fg.setStateId(mStates.get(position).getId());
            return fg;
        }
    }
}
