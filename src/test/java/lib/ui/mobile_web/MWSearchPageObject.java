package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>Input[type='search']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "css:button.clear";
        SEARCH_RESULTS_AREA = "xpath://div[contains(@class,'search-results-view')]";
        SEARCH_RESULTS_CONTAINER_XPATH = "xpath://*[contains(@class,'search-results-view')]//*[contains(@class,'results-list-container view-border-box')]";
        RESULTS_AREA = "css:ul.page-list li";
    }
    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
