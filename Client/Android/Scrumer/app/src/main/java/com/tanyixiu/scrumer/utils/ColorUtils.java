package com.tanyixiu.scrumer.utils;

import com.tanyixiu.scrumer.R;
import com.tanyixiu.scrumer.entities.Story;
import com.tanyixiu.scrumer.entities.enums;

/**
 * Created by Mimo on 2015/8/16.
 */
public class ColorUtils {

    public static int getColorIdByStoryPriority(int priority) {
        switch (priority) {
            case enums.StoryPriority.HIGH:
                return R.color.deep_orange_400;
            case enums.StoryPriority.NORMAL:
                return R.color.light_blue_400;
            case enums.StoryPriority.LOW:
                return R.color.light_green_400;
            default:
                return R.color.light_green_400;
        }
    }
}
