package lib.ui;
import io.qameta.allure.Step;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTION_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            VIEW_LIST,
            CLOSE_ARTICLE_BUTTON,
            ARTICLE_TITLE_ID,
            OPTION_REMOVE_FROM_MY_LIST_BUTTON;
    public static final String
            FOLDER_BY_EXIST_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
    private static String getExistFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_EXIST_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    @Step("Wait for title element")
    public WebElement waitForTitleElement() throws IllegalAccessException {
            return this.waitForElementPresent(TITLE, "Cannot find article title",  10);
    }
    @Step("Get article title")
    public String getArticleTitle() throws IllegalAccessException {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swipe an article to footer")
    public void swipeToFooter() throws IllegalAccessException {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "can't find the end of article",
                20
        );
    }

    @Step("Add article to the existing My Favorites list")
    public void addArticleToExistList(String name_of_folder) throws IllegalAccessException {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Couldn't find button to open article option",
                10
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Couldn't find button to add the article to reading list",
                10
        );
        String exist_folder_name_xpath = getExistFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                exist_folder_name_xpath,
                "Couldn't find '"+name_of_folder+"' + reading list",
                10
        );
    }

    @Step("Add an article to my favorites list on Android")
    public void addArticleToMyList(String name_of_folder) throws IllegalAccessException {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Couldn't find button to open article option",
                10
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Couldn't find button to add the article to reading list",
                10
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Couldn't find 'Got it' button",
                10
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Couldn't find input to set name for an article",
                10
        );
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Couldn't put text in the folder name",
                10
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Couldn't press OK button",
                10
        );
        screenshot(this.takeScreenShot("my_favorites_list_for_android"));
    }
    @Step("Close an article")
    public void closeArticle() throws IllegalAccessException {

        if (Platform.getInstance().isMW() || Platform.getInstance().isIOS())
        {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Couldn't close article, there is no X button",
                10
        );
        }
        else {
            System.out.println("this is not a method for MW");
        }

    }
    @Step("Check if element exists on the page")
    public void assertElementPresent() throws IllegalAccessException {
        By mySelector = this.getLocatorByString(ARTICLE_TITLE_ID);
        WebElement element = driver.findElement(mySelector);
        Assert.assertTrue("There is no an article title element on the page", element != null);
    }
    @Step("Add an article to my favorites list for mobile web")
    public void addArticlesToMySaved() throws IllegalAccessException {
        if(Platform.getInstance().isMW()){
            this.removeArticleFromListIfItAdded();
        }
        this.waitForElementAndClick(ADD_TO_MY_LIST_BUTTON, "cannot click and add articls", 5);
        screenshot(this.takeScreenShot("my_favorites_list_for_mobile_web"));
    }
    @Step("Remove an article from my favorites list")
    public void removeArticleFromListIfItAdded() throws IllegalAccessException {
        if(this.isElementPresent(OPTION_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTION_REMOVE_FROM_MY_LIST_BUTTON,
                    "there is no button to remove",
                    5
            );
            this.waitForElementPresent(
                    ADD_TO_MY_LIST_BUTTON,
                    "there is not this button",
                    5
            );
        }
    }
}