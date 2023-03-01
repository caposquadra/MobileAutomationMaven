package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULTS_AREA,
            RESULTS_AREA,
            SEARCH_RESULTS_CONTAINER_XPATH;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */
    public void initSearchInput() throws IllegalAccessException {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find Search input after clicking on the element", 15);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search input", 15);
    }

    public void waitForCancelButtonToAppear() throws IllegalAccessException {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 15);
    }

    public void waitForCancelButtonToDisappear() throws IllegalAccessException {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 15);
    }

    public void clickCancelButton() {
        try {
            this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 15);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void androidCancelSearch() {
        clickCancelButton();
        clickCancelButton();
    }

    public void mwCancelSearch() {
        clickCancelButton();
    }
    public void typeSearchLine(String search_line) throws IllegalAccessException {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type the search line",
                15);
    }

    public void waitForSearchResults(String substring) throws IllegalAccessException {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search results with substring " + substring, 10);
    }

    public void clickByArticleWithSubstring(String substring) throws IllegalAccessException {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click ih search results with substring " + substring, 30);
    }

    public void waitForSearchResultsAreaPresent() throws IllegalAccessException {
        this.waitForElementPresent(SEARCH_RESULTS_AREA, "Cannot find search results area", 10);
    }

    public void androidCheckNumberOfSearchResults() throws IllegalAccessException {

        By mySelector = this.getLocatorByString(SEARCH_RESULTS_CONTAINER_XPATH);
        List<WebElement> myElements = driver.findElements(mySelector);
        System.out.println("Number of elements:" + myElements.size());
        Assert.assertTrue("Several articles were not found in search results area",
                myElements.size() >= 2);
    }

    public void mwCheckNumberOfSearchResults() {

        WebElement element = driver.findElement(By.cssSelector("ul.page-list.thumbs.actionable"));

        int numberOfChild = Integer.parseInt(element.getAttribute("childElementCount"));
        System.out.println(numberOfChild);

        Assert.assertTrue("Several articles were not found in search results area",
                numberOfChild >= 2);
    }
}
