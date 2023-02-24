package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INPUT = "org.wikipedia:id/search_container";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn";
        SEARCH_RESULTS_AREA = "org.wikipedia:id/search_results_list";
        SEARCH_RESULTS_CONTAINER_XPATH = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    }
    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}