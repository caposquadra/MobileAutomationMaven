package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
@Epic("Tests for search")
public class Ex6 extends CoreTestCase {

    @Test
    @Feature("Search")
    @DisplayName("Check that article has title")
    @Description("User starts searching on Wikipedia, open an article from search and check the title")
    @Step("Starting testArticlesHasTitle test")
    @Severity(value = SeverityLevel.NORMAL)
    public void testArticlesHasTitle() throws IllegalAccessException, InterruptedException {

        String search_query_1 = "Java";
        String substring_1 = "Object-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_query_1);
        SearchPageObject.clickByArticleWithSubstring(substring_1);
        Thread.sleep(3000);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertElementPresent();
    }
}