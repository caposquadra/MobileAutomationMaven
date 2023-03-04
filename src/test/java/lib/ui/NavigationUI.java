package lib.ui;
import io.qameta.allure.Step;
import lib.Platform;
import lib.ui.factories.NavigationUIFactory;
import org.openqa.selenium.remote.RemoteWebDriver;


public class NavigationUI extends MainPageObject {

    protected static String
            MY_LIST_LINK,
            MY_LIST_LINK_ANDROID,
            SEARCH_ICON,
            RETURN_BUTTON,
            OPEN_NAVIGATION,
            RETURN_TO_THE_PREVIOUS_PAGE_BUTTON;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Click on My Favorites to open it")
    public void clickMyList() throws IllegalAccessException {

        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "Couldn't find button to add an article to My lists",
                    10
            );
        } else {
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.returnToThePreviousScreen();
            this.waitForElementAndClick(
                    MY_LIST_LINK_ANDROID,
                    "Couldn't find button to add an article to My lists",
                    10
            );
        }
    }
    @Step("Click search icom")
    public void clickSearchIcon() throws IllegalAccessException {
        this.waitForElementAndClick(
                SEARCH_ICON,
                "Couldn't find search icon to click on it",
                10
        );
    }
    @Step("Return to the previous screen")
    public void returnToThePreviousScreen() throws IllegalAccessException {
        this.waitForElementAndClick(
                RETURN_BUTTON,
                "Couldn't return, there is no the button",
                10
        );
    }
    @Step("Open navigation menu after login on mobile web")
    public void OpenNavigationAfterLogin() throws IllegalAccessException {
        if (Platform.getInstance().isMW()){
        this.tryClickElementWithFewAttempts(
                RETURN_TO_THE_PREVIOUS_PAGE_BUTTON,
                "cannot find the link to return to the previous button",
                5
        );

        this.tryClickElementWithFewAttempts(
                OPEN_NAVIGATION,
                "cannot find and click the Open Navigation button",
                5
        );

        this.tryClickElementWithFewAttempts(
                MY_LIST_LINK,
                "cannot find and click My List link",
                5
        );

    } else {
        System.out.println("OpenNavigationAfterLogin() method do nothing for platform: " +Platform.getInstance().getPlatformVar());
    }

    }
    @Step("Open navigation menu if user is logged in on mobile web")
    public void OpenNavigationIfUserIsLogged() throws IllegalAccessException {
        if (Platform.getInstance().isMW()){

            this.tryClickElementWithFewAttempts(
                    OPEN_NAVIGATION,
                    "cannot find and click the Open Navigation button",
                    5
            );

            this.tryClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "cannot find and click My List link",
                    5
            );

        } else {
            System.out.println("OpenNavigationIfUserIsLogged() method do nothing for platform: " +Platform.getInstance().getPlatformVar());
        }

    }

    @Step("Refresh the page")
    public void refreshThePage() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
    }
}
