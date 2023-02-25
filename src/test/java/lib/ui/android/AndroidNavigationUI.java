package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";
        SEARCH_ICON = "id:org.wikipedia:id/menu_page_search";
        RETURN_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }
    public AndroidNavigationUI(RemoteWebDriver driver)

    {
        super(driver);
    }
}

