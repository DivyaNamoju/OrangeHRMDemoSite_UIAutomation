package baseClass;

import utils.ReadProperties;

public class BaseClass {

    public static final String BASE_URL= ReadProperties.getTestData("BaseURL");
    public static final String DASHBOARD_URL= ReadProperties.getTestData("DashboardURL");
    public static final String VALID_USERNAME= ReadProperties.getTestData("ValidUserName");
    public static final String INVALID_USERNAME= ReadProperties.getTestData("InvalidUsername");
    public static final String LINKEDIN_URL= ReadProperties.getTestData("LinkedinURL");
    public static final String FACEBOOK_URL= ReadProperties.getTestData("FacebookURL");
    public static final String X_URL= ReadProperties.getTestData("XURL");
    public static final String YOUTUBE_URL= ReadProperties.getTestData("YouTubeURL");
}
