package lib.ui.ios;

import io.appium.java_client.AppiumDriver;

import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']";
        ARTICLE_IN_MY_LIST_TPL = "//*[contains(@text, '{ARTICLE_SUBTITLE}')]";
    }

    public IOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}