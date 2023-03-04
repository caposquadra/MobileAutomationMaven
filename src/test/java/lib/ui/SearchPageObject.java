package lib.ui;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
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

    @Step("Click on Search button to start searching")
    public void initSearchInput() throws IllegalAccessException {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find Search input after clicking on the element", 15);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search input", 15);
    }

    public void waitForCancelButtonToAppear() throws IllegalAccessException {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 15);
    }

    @Step("Wait until Cancel button disappears")
    public void waitForCancelButtonToDisappear() throws IllegalAccessException {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 15);
    }
    @Step("Click on Cancel button to cancel searching")
    public void clickCancelButton() {
        try {
            this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 15);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Cancel search on Android emulator")
    public void androidCancelSearch() {
        clickCancelButton();
        clickCancelButton();
    }
    @Step("Cancel search on Mobile Web")
    public void mwCancelSearch() {
        clickCancelButton();
    }

    @Step("Typing a search query: '{search_line}' in Search field")
    public void typeSearchLine(String search_line) throws IllegalAccessException {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type the search line",
                15);
    }
    @Step("Wait for Search results")
    public void waitForSearchResults(String substring) throws IllegalAccessException {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search results with substring " + substring, 10);
    }

    @Step("Click on the article with subtitle - '{substring}' in search results to open the article")
    public void clickByArticleWithSubstring(String substring) throws IllegalAccessException {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click ih search results with substring " + substring, 30);
    }
    @Step("Wait for search results")
    public void waitForSearchResultsAreaPresent() throws IllegalAccessException, InterruptedException {
        this.waitForElementPresent(SEARCH_RESULTS_AREA, "Cannot find search results area", 10);
        Thread.sleep(3000);
        screenshot(this.takeScreenShot("search_results"));
    }
    @Step("Check Number of results in search on Android emulator")
    public void androidCheckNumberOfSearchResults() throws IllegalAccessException {

        By mySelector = this.getLocatorByString(SEARCH_RESULTS_CONTAINER_XPATH);
        List<WebElement> myElements = driver.findElements(mySelector);
        System.out.println("Number of elements:" + myElements.size());
        Assert.assertTrue("Several articles were not found in search results area",
                myElements.size() >= 2);
    }
    @Step("Check Number of results in search on Mobile Web")
    public void mwCheckNumberOfSearchResults() {

        WebElement element = driver.findElement(By.cssSelector("ul.page-list.thumbs.actionable"));

        int numberOfChild = Integer.parseInt(element.getAttribute("childElementCount"));
        System.out.println(numberOfChild);

        Assert.assertTrue("Several articles were not found in search results area",
                numberOfChild >= 2);
    }
}
