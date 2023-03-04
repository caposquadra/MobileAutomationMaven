package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject
{
    private static final String
    LOGIN_BUTTON = "xpath://body/div/div/a[text()='Log in']",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "css:button#wpLoginAttempt";
    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Click Login button to open the login form")
    public void clickAuthButton() throws IllegalAccessException {
        this.waitForElementPresent(LOGIN_BUTTON, "cannot find button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "cannot find and click button", 5);
    }
    @Step("Enter Login and password")
    public void enterLoginDate(String login, String password) throws IllegalAccessException {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "cannot find and put login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "cannot find and put password", 5);
        screenshot(this.takeScreenShot("login_form"));
    }
    @Step("Submit form to log on the wikipedia site")
    public void submitForm() throws IllegalAccessException {
        this.tryClickElementWithFewAttempts(SUBMIT_BUTTON, "cannot find button", 5);
    }

}
