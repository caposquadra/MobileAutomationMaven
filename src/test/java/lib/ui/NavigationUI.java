package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    protected static String
            MY_LIST_LINK,
            SEARCH_ICON,
            RETURN_BUTTON;

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_LINK),
                "Couldn't find button to add an article to My lists",
                10
        );
    }

    public void clickSearchIcon()
    {
        this.waitForElementAndClick(
                By.id(SEARCH_ICON),
                "Couldn't find search icon to click on it",
                10
        );
    }

    public void returnToThePreviousScreen() {
        this.waitForElementAndClick(
                By.xpath(RETURN_BUTTON),
                "Couldn't return, there is no the button",
                10
        );
    }
}
