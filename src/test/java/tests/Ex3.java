package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for search")
public class Ex3 extends CoreTestCase {

    String search_query = "Java";
    @Test
    @Feature("Search")
    @DisplayName("Search by query and cancel  search")
    @Description("User starts searching on Wikipedia, sees the results and cancel search")
    @Step("Starting testSearchResultsAmountAndCancel test")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchResultsAmountAndCancel() throws IllegalAccessException, InterruptedException {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_query);
        SearchPageObject.waitForSearchResultsAreaPresent();

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

