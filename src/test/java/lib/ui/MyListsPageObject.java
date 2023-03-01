package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_IN_MY_LIST_TPL,
        ARTICLE_BY_TITLE_TPL,
        REMOVED_FROM_MY_LIST_BUTTON;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getArticleXpathBySubtitle(String article_subtitle)
    {
        return ARTICLE_IN_MY_LIST_TPL.replace("{ARTICLE_SUBTITLE}", article_subtitle);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) throws IllegalAccessException {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
        folder_name_xpath,
        "Cannot find folder by name " + name_of_folder,
        10
        );
    }

    public void clickOnTheAddedArticle(String article_subtitle) throws IllegalAccessException {
        String article_subtitle_xpath = getArticleXpathBySubtitle(article_subtitle);
        this.waitForElementAndClick(
                article_subtitle_xpath,
                "Cannot find an article by xpath " + article_subtitle,
                10
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) throws IllegalAccessException {
        String article_xpath = getArticleXpathBySubtitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " +article_title,
                10
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) throws IllegalAccessException {
        String article_xpath = getArticleXpathBySubtitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " +article_title,
                10
        );
    }

    public void swipeByArticleToDelete(String article_title) throws IllegalAccessException {
        String article_xpath = getArticleXpathBySubtitle(article_title);

        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Couldn't find article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void clickOnTheSavedArticleToRemoveItFromTheList(String article_title_1) throws IllegalAccessException {

        this.waitForElementAndClick(
                REMOVED_FROM_MY_LIST_BUTTON,
                "Cannot find an article by xpath " + article_title_1,
                10
        );
    }




}
