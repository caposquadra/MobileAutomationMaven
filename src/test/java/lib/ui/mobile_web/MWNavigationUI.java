package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "css:a[data-event-name='menu.unStar']";
        SEARCH_ICON = "xpath:org.wikipedia:id/menu_page_search";
        RETURN_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        OPEN_NAVIGATION = "css:label[data-event-name='ui.mainmenu']";
        RETURN_TO_THE_PREVIOUS_PAGE_BUTTON = "xpath://*[contains(text(),'Return to the previous page')]";
    }
    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
