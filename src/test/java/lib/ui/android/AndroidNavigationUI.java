package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";
        SEARCH_ICON = "org.wikipedia:id/menu_page_search";
        RETURN_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
    }
    public AndroidNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}

