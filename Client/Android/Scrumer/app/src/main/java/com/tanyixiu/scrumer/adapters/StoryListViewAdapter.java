package com.tanyixiu.scrumer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.entities.Story;
import com.tanyixiu.scrumer.utils.ColorUtils;
import com.tanyixiu.widgets.CircleImageView;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mimo on 2015/8/14.
 */
public class StoryListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Story> mStories;
    private PullToRefreshView mPullToRefreshView;

    public StoryListViewAdapter(Context context, List<Story> mStories) {
        this.mContext = context;
        this.mStories = mStories;
    }

    public void setRefreshView(View refreshView) {
        this.mPullToRefreshView = (PullToRefreshView) refreshView;
        this.mPullToRefreshView.setOnRefreshListener(mOnRefreshListener);
    }

    public void AddItems(List<Story> stories) {
        if (null == stories || 0 == stories.size()) {
            return;
        }
        if (null == mStories) {
            mStories = new ArrayList<>();
        }
        mStories.addAll(stories);
    }

    @Override
    public int getCount() {
        if (null == mStories) {
            return 0;
        }
        return mStories.size();
    }

    @Override
    public Object getItem(int position) {
        if (null == mStories || 0 == mStories.size()) {
            return null;
        }
        return mStories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Story story = (Story) getItem(position);
        if (story.isParent()) {
            return getParentStoryView(story, convertView);
        }
        return getUserStoryView(story, convertView);
    }


    private View getParentStoryView(Story story, View convertView) {
        int resId = R.layout.story_group_item;
        ParentStoryViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(resId, null);
            holder = new ParentStoryViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            int id = convertView.getId();
            if (id != resId) {
                convertView = LayoutInflater.from(mContext).inflate(resId, null);
                holder = new ParentStoryViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ParentStoryViewHolder) convertView.getTag();
            }
        }
        holder.bindData(story);
        return convertView;
    }

    private View getUserStoryView(Story story, View convertView) {
        int resId = R.layout.story_item;
        UserStoryViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(resId, null);
            holder = new UserStoryViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            int id = convertView.getId();
            if (id != resId) {
                convertView = LayoutInflater.from(mContext).inflate(resId, null);
                holder = new UserStoryViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (UserStoryViewHolder) convertView.getTag();
            }
        }
        holder.bindData(story);
        return convertView;
    }

    private PullToRefreshView.OnRefreshListener mOnRefreshListener
            = new PullToRefreshView.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPullToRefreshView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPullToRefreshView.setRefreshing(false);
                }
            }, 2000);
        }
    };

    class ParentStoryViewHolder {
        private TextView tvName;

        public ParentStoryViewHolder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.storygroup_tv_name);
        }

        public void bindData(Story parentStory) {
            tvName.setText(parentStory.getName());
        }
    }

    class UserStoryViewHolder {
        private TextView tvName;
        private TextView tvDescription;
        private CircleImageView imgPriority;

        public UserStoryViewHolder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.storyitem_tv_name);
            tvDescription = (TextView) convertView.findViewById(R.id.storyitem_tv_description);
            imgPriority = (CircleImageView) convertView.findViewById(R.id.storyitem_img_priority);
        }

        public void bindData(Story story) {
            tvName.setText(story.getName());
            tvDescription.setText(story.getDescription());

            int priority = story.getPriority();
            int colorId = ColorUtils.getColorIdByStoryPriority(priority);
            imgPriority.setImageResource(colorId);
        }
    }
}
