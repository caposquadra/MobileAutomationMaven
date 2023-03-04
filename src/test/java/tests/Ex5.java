package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Test for My Favorites")
public class Ex5 extends CoreTestCase {

    private static final String

    login = "DmitriiZimin",
    password = "QWEasd123";

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value ="My Favorites")})
    @DisplayName("Add two articles to reading list and remove one of them")
    @Description("User finds an article via search, opens it and adds it to a favorites list. User repeats these steps for another article. Then user opens the favorite list and remove one article from it")
    @Step("Starting testAddTwoArticlesToReadingListAndRemoveOneOfThem test")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAddTwoArticlesToReadingListAndRemoveOneOfThem() throws IllegalAccessException, InterruptedException {

        String search_query_1 = "Java";
        String article_title_1 = "Java (programming language)";
        String search_query_2 = "Selenium";
        String name_of_folder = "Learning programming";

        //Adding the first article to the reading list
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_query_1);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_1_title = ArticlePageObject.getArticleTitle();
        Thread.sleep(3000);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Thread.sleep(3000);
            Auth.clickAuthButton();
            Auth.enterLoginDate(login, password);
            Auth.submitForm();
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);

            NavigationUI.OpenNavigationAfterLogin();
            ArticlePageObject.waitForTitleElement();
        }
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyList();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.clickOnTheAddedArticle("oriented programming language");
        }

        //Adding the second article to the reading list

        if (Platform.getInstance().isMW()) {
            SearchPageObject.initSearchInput();
        }
        if (Platform.getInstance().isAndroid()) {
            NavigationUI.clickSearchIcon();
        }
        SearchPageObject.typeSearchLine(search_query_2);
        SearchPageObject.clickByArticleWithSubstring("Testing framework for web applications");

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistList(name_of_folder);
            ArticlePageObject.closeArticle();
            NavigationUI.returnToThePreviousScreen();
            NavigationUI.clickMyList();
        } else ArticlePageObject.addArticlesToMySaved();

        //Removing the first article from the reading list and check if it has been done correctly
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.swipeByArticleToDelete(article_1_title);
        } else {
            NavigationUI.OpenNavigationIfUserIsLogged();
            MyListsPageObject.clickOnTheSavedArticleToRemoveItFromTheList(article_title_1);
            NavigationUI.refreshThePage();
            MyListsPageObject.waitForArticleToDisappearByTitle(article_title_1);
        }
    }
}
