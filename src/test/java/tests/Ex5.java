package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex5 extends CoreTestCase {

    @Test
    public void testAddTwoArticlesToReadingListAndRemoveOneOfThem() {

        String search_query_1 = "Java";
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

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.clickOnTheAddedArticle("oriented programming language");

        //Adding the second article to the reading list

        NavigationUI.clickSearchIcon();

        SearchPageObject.typeSearchLine(search_query_2);
        SearchPageObject.clickByArticleWithSubstring("Testing framework for web applications");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToExistList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI.returnToThePreviousScreen();

        NavigationUI.clickMyList();

        //Removing the first article from the reading list and check if it has been done correctly

        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_1_title);
    }
}
