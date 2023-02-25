package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject {

    protected static String
            MY_LIST_LINK,
            SEARCH_ICON,
            RETURN_BUTTON;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickMyList() throws IllegalAccessException {
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Couldn't find button to add an article to My lists",
                10
        );
    }

    public void clickSearchIcon() throws IllegalAccessException {
        this.waitForElementAndClick(
                SEARCH_ICON,
                "Couldn't find search icon to click on it",
                10
        );
    }

    public void returnToThePreviousScreen() throws IllegalAccessException {
        this.waitForElementAndClick(
                RETURN_BUTTON,
                "Couldn't return, there is no the button",
                10
        );
    }
}
