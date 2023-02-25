package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']";
        ARTICLE_IN_MY_LIST_TPL = "//*[contains(@text, '{ARTICLE_SUBTITLE}')]";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}