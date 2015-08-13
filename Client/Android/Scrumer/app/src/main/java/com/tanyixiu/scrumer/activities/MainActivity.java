package com.tanyixiu.scrumer.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.fragments.DevingFragment;
import com.tanyixiu.scrumer.fragments.DoneFragment;
import com.tanyixiu.scrumer.fragments.HomeFragment;
import com.tanyixiu.scrumer.fragments.TestingFragment;
import com.tanyixiu.scrumer.fragments.ToTestFragment;

public class MainActivity extends BaseActivity {

    private TabHolder mTabHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        setContentView(rootView);
        initView(rootView);
    }

    private void initView(View rootView) {
        mTabHolder = new TabHolder(rootView);
        mTabHolder.setDefaultTabSelected();
        mTabHolder.setTabHolderOnClickListener(new TabHolderOnClickListener() {
            @Override
            public void onTabSelected(Fragment fragment) {
                changeFragment(fragment);
            }
        });
    }

    private void changeFragment(Fragment targetFragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment,targetFragment)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    interface TabHolderOnClickListener {
        void onTabSelected(Fragment fragment);
    }

    class TabHolder {
        private TextView tabHome;
        private TextView tabDeving;
        private TextView tabToTest;
        private TextView tabTesting;
        private TextView tabDone;

        private TabHolderOnClickListener mTabHolderOnClickListener;

        public TabHolder(View rootView) {
            this.tabHome    = (TextView) rootView.findViewById(R.id.main_tab_home);
            this.tabDeving  = (TextView) rootView.findViewById(R.id.main_tab_deving);
            this.tabToTest  = (TextView) rootView.findViewById(R.id.main_tab_totest);
            this.tabTesting = (TextView) rootView.findViewById(R.id.main_tab_testing);
            this.tabDone    = (TextView) rootView.findViewById(R.id.main_tab_done);

            this.tabHome    .setOnClickListener(mOnClickListener);
            this.tabDeving  .setOnClickListener(mOnClickListener);
            this.tabToTest  .setOnClickListener(mOnClickListener);
            this.tabTesting .setOnClickListener(mOnClickListener);
            this.tabDone    .setOnClickListener(mOnClickListener);
        }

        public void setTabHolderOnClickListener(TabHolderOnClickListener mTabHolderOnClickListener) {
            this.mTabHolderOnClickListener = mTabHolderOnClickListener;
        }

        public void setDefaultTabSelected(){
            setTabSelected(this.tabHome);
        }

        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelected(v);
            }
        };

        private void setTabSelected(View tab){
            resetBackground();
            tab.setBackgroundResource(R.color.bkg_color_green_dark);
            if(null == this.mTabHolderOnClickListener){
                return;
            }
            Fragment selectedFragment = getSelectedFragment(tab);
            mTabHolderOnClickListener.onTabSelected(selectedFragment);
        }

        private void resetBackground() {
            this.tabHome    .setBackgroundResource(R.color.bkg_color_green_light);
            this.tabDeving  .setBackgroundResource(R.color.bkg_color_green_light);
            this.tabToTest  .setBackgroundResource(R.color.bkg_color_green_light);
            this.tabTesting .setBackgroundResource(R.color.bkg_color_green_light);
            this.tabDone    .setBackgroundResource(R.color.bkg_color_green_light);
        }

        private Fragment getSelectedFragment(View tab) {
            Object tag = tab.getTag();
            if(null != tag){
                return (Fragment) tab.getTag();
            }
            Fragment fragment = null;
            switch (tab.getId()){
                case R.id.main_tab_home:
                    fragment = HomeFragment.newInstance();
                    break;
                case R.id.main_tab_deving:
                    fragment = DevingFragment.newInstance();
                    break;
                case R.id.main_tab_totest:
                    fragment = ToTestFragment.newInstance();
                    break;
                case R.id.main_tab_testing:
                    fragment = TestingFragment.newInstance();
                    break;
                case R.id.main_tab_done:
                    fragment = DoneFragment.newInstance();
                    break;
                default:
                    break;
            }
            tab.setTag(fragment);
            return fragment;
        }
    }
}
