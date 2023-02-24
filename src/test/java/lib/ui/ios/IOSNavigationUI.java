package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";
        SEARCH_ICON = "org.wikipedia:id/menu_page_search";
        RETURN_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
    }
    public IOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}

