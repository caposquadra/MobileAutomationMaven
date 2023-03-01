package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex3 extends CoreTestCase {

    @Test
    public void testSearchResultsAmountAndCancel() throws IllegalAccessException, InterruptedException {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultsAreaPresent();
        Thread.sleep(5000);
        if(Platform.getInstance().isAndroid()) {
            SearchPageObject.androidCheckNumberOfSearchResults();}
        else{
            SearchPageObject.mwCheckNumberOfSearchResults();
        }

        if(Platform.getInstance().isAndroid()) {
            SearchPageObject.androidCancelSearch();}
        else{
            SearchPageObject.mwCancelSearch();
        }
        SearchPageObject.waitForCancelButtonToDisappear();
    }
}

