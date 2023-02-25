package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex6 extends CoreTestCase {

    @Test
    public void testArticlesHasTitle() throws IllegalAccessException {

        String search_query_1 = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_query_1);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject.assertElementPresent();
    }
}