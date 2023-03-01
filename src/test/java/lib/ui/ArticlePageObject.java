package lib.ui;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

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
    public WebElement waitForTitleElement() throws IllegalAccessException {
            return this.waitForElementPresent(TITLE, "Cannot find article title",  10);
    }
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
    public void swipeToFooter() throws IllegalAccessException {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "can't find the end of article",
                20
        );
    }
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
    }
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
    public static void assertElementPresent() {
        WebElement element = driver.findElement(By.id(ARTICLE_TITLE_ID));
        Assert.assertTrue("There is no an article title element on the page", element != null);
    }

    public void addArticlesToMySaved() throws IllegalAccessException {
        if(Platform.getInstance().isMW()){
            this.removeArticleFromListIfItAdded();
        }
        this.waitForElementAndClick(ADD_TO_MY_LIST_BUTTON, "cannot click and add articls", 5);
    }

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