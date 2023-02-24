package lib.ui.android;

import io.appium.java_client.AppiumDriver;

import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']";
        ARTICLE_IN_MY_LIST_TPL = "//*[contains(@text, '{ARTICLE_SUBTITLE}')]";
    }

    public AndroidMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}