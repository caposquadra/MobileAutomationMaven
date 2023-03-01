package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_IN_MY_LIST_TPL = "xpath://ul[contains(@class, 'mw-mf-watchlist-page-list')]//h3[contains(text(), 'Java (programming language)')]";
        REMOVED_FROM_MY_LIST_BUTTON = "xpath://ul[contains(@class, 'mw-mf-watchlist-page-list')]//a[contains(@class, 'watched mw-ui-button mw-ui-quiet')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}