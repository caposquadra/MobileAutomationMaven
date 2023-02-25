package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex3 extends CoreTestCase {

    @Test
    public void testSearchResultsAmountAndCancel() throws IllegalAccessException {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultsAreaPresent();
        SearchPageObject.checkNumberOfSearchResults();
        SearchPageObject.cancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
}

