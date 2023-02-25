package lib.ui.android;

import io.appium.java_client.AppiumDriver;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_IN_MY_LIST_TPL = "xpath://*[contains(@text, '{ARTICLE_SUBTITLE}')]";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}