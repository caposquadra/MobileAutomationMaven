package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";
        SEARCH_ICON = "org.wikipedia:id/menu_page_search";
        RETURN_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
    }
    public IOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}

