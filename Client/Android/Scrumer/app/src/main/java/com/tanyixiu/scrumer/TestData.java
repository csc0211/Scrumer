package com.tanyixiu.scrumer;

import com.tanyixiu.scrumer.entities.Story;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mimo on 2015/8/15.
 */
public class TestData {
    public static List<Story> loadStorys(int pageIndex) {
        if(0 == pageIndex){
            return loadFirstPageStory();
        }
        return null;
    }

    private static List<Story> loadFirstPageStory(){
        List<Story> stories = new ArrayList<>();
        String creatorId = String.valueOf(UUID.randomUUID());
        String projectId = String.valueOf(UUID.randomUUID());

        Story storyParent0 = new Story();
        String parentId0 = String.valueOf(UUID.randomUUID());
        storyParent0.setId(parentId0);
        storyParent0.setName("Design the page of 'Home'");
        storyParent0.setParentId("0");
        storyParent0.setCreatorId(creatorId);
        storyParent0.setProjectId(projectId);
        storyParent0.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent0);

        Story userStory0 = new Story();
        userStory0.setId(String.valueOf(UUID.randomUUID()));
        userStory0.setName("Load more");
        userStory0.setDescription("Add load more progress bar at the bottom of listview.");
        userStory0.setProjectId(projectId);
        userStory0.setParentId(parentId0);
        userStory0.setCreatorId(creatorId);
        userStory0.setPriority(Story.StoryPriority.HIGHT);
        userStory0.setState(Story.StoryState.TO_DEV);
        stories.add(userStory0);

        Story userStory1 = new Story();
        userStory1.setId(String.valueOf(UUID.randomUUID()));
        userStory1.setName("Network Request");
        userStory1.setDescription("Add network request when the listview is refresing or loading more.");
        userStory1.setProjectId(projectId);
        userStory1.setParentId(parentId0);
        userStory1.setCreatorId(creatorId);
        userStory1.setPriority(Story.StoryPriority.HIGHT);
        userStory1.setState(Story.StoryState.TO_DEV);
        stories.add(userStory1);

        Story userStory2 = new Story();
        userStory2.setId(String.valueOf(UUID.randomUUID()));
        userStory2.setName("Priority Colors");
        userStory2.setDescription("Design color for each story priority.");
        userStory2.setProjectId(projectId);
        userStory2.setCreatorId(creatorId);
        userStory2.setParentId(parentId0);
        userStory2.setPriority(Story.StoryPriority.NORMAL);
        userStory2.setState(Story.StoryState.TO_DEV);
        stories.add(userStory2);

        Story userStory3 = new Story();
        userStory3.setId(String.valueOf(UUID.randomUUID()));
        userStory3.setName("Handle 'Add' Click");
        userStory3.setDescription("Handle the click event of add story (float action button).");
        userStory3.setProjectId(projectId);
        userStory3.setCreatorId(creatorId);
        userStory3.setParentId(parentId0);
        userStory3.setPriority(Story.StoryPriority.NORMAL);
        userStory3.setState(Story.StoryState.TO_DEV);
        stories.add(userStory3);

        Story userStory4 = new Story();
        userStory4.setId(String.valueOf(UUID.randomUUID()));
        userStory4.setName("Handle Item Click");
        userStory4.setDescription("Handle the click event of listview item");
        userStory4.setProjectId(projectId);
        userStory4.setParentId(parentId0);
        userStory4.setCreatorId(creatorId);
        userStory4.setPriority(Story.StoryPriority.LOW);
        userStory4.setState(Story.StoryState.TO_DEV);
        stories.add(userStory4);


        Story storyParent1 = new Story();
        String parentId1 = String.valueOf(UUID.randomUUID());
        storyParent1.setId(parentId1);
        storyParent1.setName("Design the page of 'Deving'");
        storyParent1.setParentId("0");
        storyParent1.setCreatorId(creatorId);
        storyParent1.setProjectId(projectId);
        storyParent1.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent1);

        Story userStory00 = new Story();
        userStory00.setId(String.valueOf(UUID.randomUUID()));
        userStory00.setName("Load Data");
        userStory00.setDescription("Load data when first come to the page");
        userStory00.setProjectId(projectId);
        userStory00.setParentId(parentId1);
        userStory00.setCreatorId(creatorId);
        userStory00.setPriority(Story.StoryPriority.HIGHT);
        userStory00.setState(Story.StoryState.TO_DEV);
        stories.add(userStory00);

        Story userStory11 = new Story();
        userStory11.setId(String.valueOf(UUID.randomUUID()));
        userStory11.setName("Head Photo");
        userStory11.setDescription("Show the photos of developers , alse contains pari programming");
        userStory11.setProjectId(projectId);
        userStory11.setCreatorId(creatorId);
        userStory11.setParentId(parentId1);
        userStory11.setPriority(Story.StoryPriority.NORMAL);
        userStory11.setState(Story.StoryState.DEVING);
        stories.add(userStory11);

        Story userStory33 = new Story();
        userStory33.setId(String.valueOf(UUID.randomUUID()));
        userStory33.setName("Show priority");
        userStory33.setCreatorId(creatorId);
        userStory33.setDescription("How to dispaly the priority");
        userStory33.setProjectId(projectId);
        userStory33.setParentId(parentId1);
        userStory33.setPriority(Story.StoryPriority.LOW);
        userStory33.setState(Story.StoryState.TO_DEV);
        stories.add(userStory33);

        return stories;
    }
}
