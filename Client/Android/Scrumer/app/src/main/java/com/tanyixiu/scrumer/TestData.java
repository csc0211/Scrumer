package com.tanyixiu.scrumer;

/**
 * Created by Mimo on 2015/8/15.
 */
public class TestData {

/*
    public static List<Story> loadStories(String projectId, int storyState, int pageIndex) {

        switch (storyState) {
            case Story.StoryState.TO_DEV:
                return loadStoriesToDev(projectId);

            case Story.StoryState.DEVING:
                return loadStoriesIsDevinng(projectId);

            case Story.StoryState.TO_TEST:
                return loadStoriesToTest(projectId);

            case Story.StoryState.TESTING:
                return loadStoriesIsTesting(projectId);

            case Story.StoryState.DONE:
                return loadStoriesIsDone(projectId);

            default:
                break;
        }
        return null;
    }

    private static List<Story> loadStoriesToDev(String projectId) {
        List<Story> stories = new ArrayList<>();
        String creatorId = "7fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";

        Story storyParent0 = new Story();
        String parentId0 = "8fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";
        storyParent0.setId(parentId0);
        storyParent0.setName("Design the page of Home");
        storyParent0.setParentId("0");
        storyParent0.setCreatorId(creatorId);
        storyParent0.setProjectId(projectId);
        storyParent0.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent0);

        Story userStory0 = new Story();
        userStory0.setId("9fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory0.setName("Load more");
        userStory0.setDescription("Add load more progress bar at the bottom of listview.");
        userStory0.setProjectId(projectId);
        userStory0.setParentId(parentId0);
        userStory0.setCreatorId(creatorId);
        userStory0.setPriority(Story.StoryPriority.HIGHT);
        userStory0.setState(Story.StoryState.TO_DEV);
        stories.add(userStory0);

        Story userStory1 = new Story();
        userStory1.setId("0fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory1.setName("Network Request");
        userStory1.setDescription("Add network request when the listview is refresing or loading more.");
        userStory1.setProjectId(projectId);
        userStory1.setParentId(parentId0);
        userStory1.setCreatorId(creatorId);
        userStory1.setPriority(Story.StoryPriority.HIGHT);
        userStory1.setState(Story.StoryState.TO_DEV);
        stories.add(userStory1);

        Story userStory2 = new Story();
        userStory2.setId("1fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory2.setName("Priority Colors");
        userStory2.setDescription("Design color for each story priority.");
        userStory2.setProjectId(projectId);
        userStory2.setCreatorId(creatorId);
        userStory2.setParentId(parentId0);
        userStory2.setPriority(Story.StoryPriority.NORMAL);
        userStory2.setState(Story.StoryState.TO_DEV);
        stories.add(userStory2);

        Story userStory3 = new Story();
        userStory3.setId("2fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory3.setName("Handle 'Add' Click");
        userStory3.setDescription("Handle the click event of add story (float action button).");
        userStory3.setProjectId(projectId);
        userStory3.setCreatorId(creatorId);
        userStory3.setParentId(parentId0);
        userStory3.setPriority(Story.StoryPriority.NORMAL);
        userStory3.setState(Story.StoryState.TO_DEV);
        stories.add(userStory3);

        Story userStory4 = new Story();
        userStory4.setId("3fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory4.setName("Handle Item Click");
        userStory4.setDescription("Handle the click event of listview item");
        userStory4.setProjectId(projectId);
        userStory4.setParentId(parentId0);
        userStory4.setCreatorId(creatorId);
        userStory4.setPriority(Story.StoryPriority.LOW);
        userStory4.setState(Story.StoryState.TO_DEV);
        stories.add(userStory4);


        Story storyParent1 = new Story();
        String parentId1 = "4fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";
        storyParent1.setId(parentId1);
        storyParent1.setName("Design the page of 'Deving'");
        storyParent1.setParentId("0");
        storyParent1.setCreatorId(creatorId);
        storyParent1.setProjectId(projectId);
        storyParent1.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent1);

        Story userStory00 = new Story();
        userStory00.setId("5fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory00.setName("Load Data");
        userStory00.setDescription("Load data when first come to the page");
        userStory00.setProjectId(projectId);
        userStory00.setParentId(parentId1);
        userStory00.setCreatorId(creatorId);
        userStory00.setPriority(Story.StoryPriority.HIGHT);
        userStory00.setState(Story.StoryState.TO_DEV);
        stories.add(userStory00);

        Story userStory11 = new Story();
        userStory11.setId("80e5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory11.setName("Head Photo");
        userStory11.setDescription("Show the photos of developers , alse contains pari programming");
        userStory11.setProjectId(projectId);
        userStory11.setCreatorId(creatorId);
        userStory11.setParentId(parentId1);
        userStory11.setPriority(Story.StoryPriority.NORMAL);
        userStory11.setState(Story.StoryState.TO_DEV);
        stories.add(userStory11);

        Story userStory33 = new Story();
        userStory33.setId("81e5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
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

    private static List<Story> loadStoriesIsDevinng(String projectId) {

        List<Story> stories = new ArrayList<>();
        String creatorId = "7fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";

        Story storyParent0 = new Story();
        String parentId0 = "8fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";
        storyParent0.setId(parentId0);
        storyParent0.setName("Design the page of Home");
        storyParent0.setParentId("0");
        storyParent0.setCreatorId(creatorId);
        storyParent0.setProjectId(projectId);
        storyParent0.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent0);

        Story userStory0 = new Story();
        userStory0.setId("9fe5ba90-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory0.setName("Apps provide multiple entry points");
        userStory0.setDescription("Android apps are built as a combination of distinct components that can be invoked individually. For instance, an individual activity provides a single screen for a user interface, and a service independently performs work in the background..");
        userStory0.setProjectId(projectId);
        userStory0.setParentId(parentId0);
        userStory0.setCreatorId(creatorId);
        userStory0.setPriority(Story.StoryPriority.HIGHT);
        userStory0.setState(Story.StoryState.DEVING);
        stories.add(userStory0);

        Story userStory1 = new Story();
        userStory1.setId("0fe5ba91-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory1.setName("Apps adapt to different devices");
        userStory1.setDescription("Android provides an adaptive app framework that allows you to provide unique resources for different device configurations. For example, you can create different XML layout files for different screen sizes and the system determines which layout to apply based on the current device's screen size.");
        userStory1.setProjectId(projectId);
        userStory1.setParentId(parentId0);
        userStory1.setCreatorId(creatorId);
        userStory1.setPriority(Story.StoryPriority.NORMAL);
        userStory1.setState(Story.StoryState.DEVING);
        stories.add(userStory1);

        Story userStory2 = new Story();
        userStory2.setId("1fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory2.setName("App Components");
        userStory2.setDescription("App components are the essential building blocks of an Android app. Each component is a different point through which the system can enter your app. Not all components are actual entry points for the user and some depend on each other, but each one exists as its own entity and plays a specific role—each one is a unique building block that helps define your app's overall behavior.");
        userStory2.setProjectId(projectId);
        userStory2.setCreatorId(creatorId);
        userStory2.setParentId(parentId0);
        userStory2.setPriority(Story.StoryPriority.NORMAL);
        userStory2.setState(Story.StoryState.DEVING);
        stories.add(userStory2);

        Story userStory3 = new Story();
        userStory3.setId("2fe5ba93-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory3.setName("An activity represents a single screen");
        userStory3.setDescription("with a user interface. For example, an email app might have one activity that shows a list of new emails, another activity to compose an email, and another activity for reading emails. Although the activities work together to form a cohesive user experience in the email app, each one is independent of the others. As such, a different app can start any one of these activities (if the email app allows it). For example, a camera app can start the activity in the email app that composes new mail, in order for the user to share a picture.");
        userStory3.setProjectId(projectId);
        userStory3.setCreatorId(creatorId);
        userStory3.setParentId(parentId0);
        userStory3.setPriority(Story.StoryPriority.NORMAL);
        userStory3.setState(Story.StoryState.DEVING);
        stories.add(userStory3);

        Story userStory4 = new Story();
        userStory4.setId("3fe5ba94-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory4.setName("A service is a component that runs");
        userStory4.setDescription(" in the background to perform long-running operations or to perform work for remote processes. A service does not provide a user interface. For example, a service might play music in the background while the user is in a different app, or it might fetch data over the network without blocking user interaction with an activity. Another component, such as an activity, can start the service and let it run or bind to it in order to interact with it.");
        userStory4.setProjectId(projectId);
        userStory4.setParentId(parentId0);
        userStory4.setCreatorId(creatorId);
        userStory4.setPriority(Story.StoryPriority.LOW);
        userStory4.setState(Story.StoryState.DEVING);
        stories.add(userStory4);


        Story storyParent1 = new Story();
        String parentId1 = "4fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";
        storyParent1.setId(parentId1);
        storyParent1.setName("Design the page of 'Deving'");
        storyParent1.setParentId("0");
        storyParent1.setCreatorId(creatorId);
        storyParent1.setProjectId(projectId);
        storyParent1.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent1);

        Story userStory00 = new Story();
        userStory00.setId("5fe5ba95-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory00.setName("A content provider manages");
        userStory00.setDescription("a shared set of app data. You can store the data in the file system, an SQLite database, on the web, or any other persistent storage location your app can access. Through the content provider, other apps can query or even modify the data (if the content provider allows it). For example, the Android system provides a content provider that manages the user's contact information. As such, any app with the proper permissions can query part of the content provider (such as ContactsContract.Data) to read and write information about a particular person.");
        userStory00.setProjectId(projectId);
        userStory00.setParentId(parentId1);
        userStory00.setCreatorId(creatorId);
        userStory00.setPriority(Story.StoryPriority.HIGHT);
        userStory00.setState(Story.StoryState.DEVING);
        stories.add(userStory00);

        return stories;
    }

    private static List<Story> loadStoriesToTest(String projectId) {

        List<Story> stories = new ArrayList<>();
        String creatorId = "7fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";

        Story storyParent0 = new Story();
        String parentId0 = "8fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";
        storyParent0.setId(parentId0);
        storyParent0.setName("Design the page of Home");
        storyParent0.setParentId("0");
        storyParent0.setCreatorId(creatorId);
        storyParent0.setProjectId(projectId);
        storyParent0.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent0);

        Story userStory0 = new Story();
        userStory0.setId("9fe5ba90-ce1f-4dd6-b3a9-35b227e19ceb");
        userStory0.setName("You can start an activity");
        userStory0.setDescription("For broadcast receivers, the intent simply defines the announcement being broadcast (for example, a broadcast to indicate the device battery is low includes only a known action string that indicates battery is low).");
        userStory0.setProjectId(projectId);
        userStory0.setParentId(parentId0);
        userStory0.setCreatorId(creatorId);
        userStory0.setPriority(Story.StoryPriority.HIGHT);
        userStory0.setState(Story.StoryState.TO_TEST);
        stories.add(userStory0);

        Story userStory1 = new Story();
        userStory1.setId("0fe5ba91-ce2f-4dd6-b3a9-35b227e19ceb");
        userStory1.setName("Activating Components");
        userStory1.setDescription("Three of the four component types—activities, services, and broadcast receivers—are activated by an asynchronous message called an intent. Intents bind individual components to each other at runtime (you can think of them as the messengers that request an action from other components), whether the component belongs to your app or another.");
        userStory1.setProjectId(projectId);
        userStory1.setParentId(parentId0);
        userStory1.setCreatorId(creatorId);
        userStory1.setPriority(Story.StoryPriority.NORMAL);
        userStory1.setState(Story.StoryState.TO_TEST);
        stories.add(userStory1);

        Story userStory2 = new Story();
        userStory2.setId("1fe5ba92-ce3f-4dd6-b3a9-35b227e19ceb");
        userStory2.setName("App Components");
        userStory2.setDescription("App components are the essential building blocks of an Android app. Each component is a different point through which the system can enter your app. Not all components are actual entry points for the user and some depend on each other, but each one exists as its own entity and plays a specific role—each one is a unique building block that helps define your app's overall behavior.");
        userStory2.setProjectId(projectId);
        userStory2.setCreatorId(creatorId);
        userStory2.setParentId(parentId0);
        userStory2.setPriority(Story.StoryPriority.LOW);
        userStory2.setState(Story.StoryState.TO_TEST);
        stories.add(userStory2);

        Story userStory3 = new Story();
        userStory3.setId("2fe5ba93-ce4f-4dd6-b3a9-35b227e19ceb");
        userStory3.setName("A unique aspect of the Android ");
        userStory3.setDescription("For example, if you want the user to capture a photo with the device camera, there's probably another app that does that and your app can use it, instead of developing an activity to capture a photo yourself. You don't need to incorporate or even link to the code from the camera app. Instead, you can simply start the activity in the camera app that captures a photo. When complete, the photo is even returned to your app so you can use it. To the user, it seems as if the camera is actually a part of your app.");
        userStory3.setProjectId(projectId);
        userStory3.setCreatorId(creatorId);
        userStory3.setParentId(parentId0);
        userStory3.setPriority(Story.StoryPriority.LOW);
        userStory3.setState(Story.StoryState.TO_TEST);
        stories.add(userStory3);

        Story userStory4 = new Story();
        userStory4.setId("3fe5ba94-ce5f-4dd6-b3a9-35b227e19ceb");
        userStory4.setName("A broadcast receiver is a component");
        userStory4.setDescription(" Many broadcasts originate from the system—for example, a broadcast announcing that the screen has turned off, the battery is low, or a picture was captured. Apps can also initiate broadcasts—for example, to let other apps know that some data has been downloaded to the device and is available for them to use. ");
        userStory4.setProjectId(projectId);
        userStory4.setParentId(parentId0);
        userStory4.setCreatorId(creatorId);
        userStory4.setPriority(Story.StoryPriority.LOW);
        userStory4.setState(Story.StoryState.TO_TEST);
        stories.add(userStory4);

        return stories;
    }

    private static List<Story> loadStoriesIsTesting(String projectId) {
        List<Story> stories = new ArrayList<>();
        String creatorId = "7fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";

        Story storyParent0 = new Story();
        String parentId0 = "8fe5ba92-ce1f-0dd6-b3a9-35b227e19ceb";
        storyParent0.setId(parentId0);
        storyParent0.setName("Design the page of Home");
        storyParent0.setParentId("0");
        storyParent0.setCreatorId(creatorId);
        storyParent0.setProjectId(projectId);
        storyParent0.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent0);

        Story userStory0 = new Story();
        userStory0.setId("9fe5ba90-ce1f-1dd6-b3a9-35b227e19ceb");
        userStory0.setName("Links to this post");
        userStory0.setDescription("To further provide a consistent user experience in the action bar, we suggest that you use action icons designed by the Android UX Team where appropriate. The available icons support common user actions such as Refresh, Delete, Attach, Star, Share and more, and are designed for the light and dark Holo themes; they’re available on the Android Design downloads page.");
        userStory0.setProjectId(projectId);
        userStory0.setParentId(parentId0);
        userStory0.setCreatorId(creatorId);
        userStory0.setPriority(Story.StoryPriority.HIGHT);
        userStory0.setState(Story.StoryState.TESTING);
        stories.add(userStory0);

        Story userStory1 = new Story();
        userStory1.setId("0fe5ba91-ce2f-2dd6-b3a9-35b227e19ceb");
        userStory1.setName("Android no longer requires a dedicated");
        userStory1.setDescription("For information about how you should design your action bar, see Android Design’s Action Bar guide. More information about implementing the action bar is also available in the Action Bar developer guide.");
        userStory1.setProjectId(projectId);
        userStory1.setParentId(parentId0);
        userStory1.setCreatorId(creatorId);
        userStory1.setPriority(Story.StoryPriority.NORMAL);
        userStory1.setState(Story.StoryState.TESTING);
        stories.add(userStory1);

        Story userStory2 = new Story();
        userStory2.setId("1fe5ba92-ce3f-3dd6-b3a9-35b227e19ceb");
        userStory2.setName("Removing the action bar");
        userStory2.setDescription("If you don’t need the action bar, you can remove it from your entire app or from individual activities. This is appropriate for apps that never used the options menu or for apps in which the action bar doesn’t meet design needs (such as games). You can remove the action bar using a theme such as Theme.Holo.NoActionBar or Theme.DeviceDefault.NoActionBar.");
        userStory2.setProjectId(projectId);
        userStory2.setCreatorId(creatorId);
        userStory2.setParentId(parentId0);
        userStory2.setPriority(Story.StoryPriority.LOW);
        userStory2.setState(Story.StoryState.TESTING);
        stories.add(userStory2);

        Story storyParent1 = new Story();
        String parentId1 = "4fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";
        storyParent1.setId(parentId1);
        storyParent1.setName("Design the page of 'Deving'");
        storyParent1.setParentId("0");
        storyParent1.setCreatorId(creatorId);
        storyParent1.setProjectId(projectId);
        storyParent1.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent1);

        Story userStory00 = new Story();
        userStory00.setId("5fe5ba92-ce1f-5dd6-b3a9-35b227e19ceb");
        userStory00.setName("Migrating to the action bar");
        userStory00.setDescription("If you have activities that use the options menu (they implement onCreateOptionsMenu()), then once the legacy overflow button disappears from the system/navigation bar (because you’ve set targetSdkVersion to 14), you need to provide an alternative means for the user to access the activity’s actions and other options. Fortunately, the system provides such a means by default: the action bar.");
        userStory00.setProjectId(projectId);
        userStory00.setParentId(parentId1);
        userStory00.setCreatorId(creatorId);
        userStory00.setPriority(Story.StoryPriority.HIGHT);
        userStory00.setState(Story.StoryState.TESTING);
        stories.add(userStory00);

        Story userStory11 = new Story();
        userStory11.setId("80e5ba92-ce1f-6dd6-b3a9-35b227e19ceb");
        userStory11.setName("Action overflow button for legacy apps");
        userStory11.setDescription("If you’ve already developed an app to support Android 2.3 and lower, then you might have noticed that when it runs on a device without a hardware Menu button (such as a Honeycomb tablet or Galaxy Nexus), the system adds the action overflow button beside the system navigation.");
        userStory11.setProjectId(projectId);
        userStory11.setCreatorId(creatorId);
        userStory11.setParentId(parentId1);
        userStory11.setPriority(Story.StoryPriority.NORMAL);
        userStory11.setState(Story.StoryState.TESTING);
        stories.add(userStory11);

        Story userStory22 = new Story();
        userStory22.setId("80e5ba92-ce1f-7dd6-b3a9-35b227e19ceb");
        userStory22.setName("Don’t call it a menu");
        userStory22.setDescription("Not only should your apps stop relying on the hardware Menu button, but you should stop thinking about your activities using a “menu button” at all. Your activities should provide buttons for important user actions directly in the action bar (or elsewhere on screen). Those that can’t fit in the action bar end up in the action overflow.");
        userStory22.setProjectId(projectId);
        userStory22.setCreatorId(creatorId);
        userStory22.setParentId(parentId1);
        userStory22.setPriority(Story.StoryPriority.NORMAL);
        userStory22.setState(Story.StoryState.TESTING);
        stories.add(userStory22);

        Story userStory33 = new Story();
        userStory33.setId("81e5ba92-ce1f-8dd6-b3a9-35b227e19ceb");
        userStory33.setName("Introducing ViewPropertyAnimator");
        userStory33.setCreatorId(creatorId);
        userStory33.setDescription("This new animation system makes it easy to animate any kind of property on any object, including the new properties added to the View class in 3.0. In the 3.1 release, we added a small utility class that makes animating these properties even easier.");
        userStory33.setProjectId(projectId);
        userStory33.setParentId(parentId1);
        userStory33.setPriority(Story.StoryPriority.LOW);
        userStory33.setState(Story.StoryState.TESTING);
        stories.add(userStory33);
        return stories;
    }

    private static List<Story> loadStoriesIsDone(String projectId) {

        List<Story> stories = new ArrayList<>();
        String creatorId = "7fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";

        Story storyParent0 = new Story();
        String parentId0 = "8fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";
        storyParent0.setId(parentId0);
        storyParent0.setName("Design the page of Home");
        storyParent0.setParentId("0");
        storyParent0.setCreatorId(creatorId);
        storyParent0.setProjectId(projectId);
        storyParent0.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent0);

        Story userStory0 = new Story();
        userStory0.setId("9fe5ba90-ce1f-4dd6-b0a9-35b227e19ceb");
        userStory0.setName("Design Considerations");
        userStory0.setDescription("Notifications, as an important part of the Android user interface, have their own design guidelines. The material design changes introduced in Android 5.0 (API level 21) are of particular importance, and you should review the Material Design training for more information. ");
        userStory0.setProjectId(projectId);
        userStory0.setParentId(parentId0);
        userStory0.setCreatorId(creatorId);
        userStory0.setPriority(Story.StoryPriority.HIGHT);
        userStory0.setState(Story.StoryState.DONE);
        stories.add(userStory0);

        Story userStory1 = new Story();
        userStory1.setId("0fe5ba91-ce2f-4dd6-b1a9-35b227e19ceb");
        userStory1.setName("Creating a Notification");
        userStory1.setDescription("Three of the four component types—activities, services, and broadcast receivers—are activated by an asynchronous message called an intent. Intents bind individual components to each other at runtime (you can think of them as the messengers that request an action from other components), whether the component belongs to your app or another.");
        userStory1.setProjectId(projectId);
        userStory1.setParentId(parentId0);
        userStory1.setCreatorId(creatorId);
        userStory1.setPriority(Story.StoryPriority.NORMAL);
        userStory1.setState(Story.StoryState.DONE);
        stories.add(userStory1);

        Story userStory2 = new Story();
        userStory2.setId("1fe5ba92-ce3f-4dd6-b2a9-35b227e19ceb");
        userStory2.setName("Required notification contents");
        userStory2.setDescription("Although they're optional, you should add at least one action to your notification. An action allows users to go directly from the notification to an Activity in your application, where they can look at one or more events or do further work.");
        userStory2.setProjectId(projectId);
        userStory2.setCreatorId(creatorId);
        userStory2.setParentId(parentId0);
        userStory2.setPriority(Story.StoryPriority.LOW);
        userStory2.setState(Story.StoryState.DONE);
        stories.add(userStory2);

        Story userStory3 = new Story();
        userStory3.setId("2fe5ba93-ce4f-4dd6-b3a9-35b227e19ceb");
        userStory3.setName("Notification priority");
        userStory3.setDescription("If you wish, you can set the priority of a notification. The priority acts as a hint to the device UI about how the notification should be displayed. To set a notification's priority, call");
        userStory3.setProjectId(projectId);
        userStory3.setCreatorId(creatorId);
        userStory3.setParentId(parentId0);
        userStory3.setPriority(Story.StoryPriority.LOW);
        userStory3.setState(Story.StoryState.DONE);
        stories.add(userStory3);

        Story storyParent1 = new Story();
        String parentId1 = "4fe5ba92-ce1f-4dd6-b3a9-35b227e19ceb";
        storyParent1.setId(parentId1);
        storyParent1.setName("Design the page of 'Deving'");
        storyParent1.setParentId("0");
        storyParent1.setCreatorId(creatorId);
        storyParent1.setProjectId(projectId);
        storyParent1.setPriority(Story.StoryPriority.LOW);
        stories.add(storyParent1);

        Story userStory00 = new Story();
        userStory00.setId("5fe5ba92-ce1f-5dd6-b4a9-35b227e19ceb");
        userStory00.setName("Creating a simple notification");
        userStory00.setDescription("The following snippet illustrates a simple notification that specifies an activity to open when the user clicks the notification. Notice that the code creates a TaskStackBuilder object and uses it to create the PendingIntent for the action. This pattern is explained in more detail in the section Preserving Navigation when Starting an Activity.");
        userStory00.setProjectId(projectId);
        userStory00.setParentId(parentId1);
        userStory00.setCreatorId(creatorId);
        userStory00.setPriority(Story.StoryPriority.HIGHT);
        userStory00.setState(Story.StoryState.DONE);
        stories.add(userStory00);

        return stories;
    }
*/
}
