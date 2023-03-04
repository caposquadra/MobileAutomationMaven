package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected static RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) throws IllegalAccessException {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) throws IllegalAccessException {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) throws IllegalAccessException {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) throws IllegalAccessException {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void SwipeUp(int timeoutswipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action.press(x, start_y).waitAction().moveTo(x, end_y).release().perform();
        } else {
            System.out.println("Method SwipeUp doesn't nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void SwipeUpQuick() {
        SwipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) throws IllegalAccessException {
        int already_swiped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swipe \n" + error_message, 0);
                return;
            }
            SwipeUpQuick();
            ++already_swiped;
        }
    }

    public int getAmountOfElements(String locator) throws IllegalAccessException {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void AssertElementNotPresent(String locator, String error_message) throws IllegalAccessException {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator + " ' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, int timeout) throws IllegalAccessException {
        WebElement element = waitForElementPresent(locator, error_message, timeout);
        return element.getAttribute(attribute);
    }
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) throws IllegalAccessException {
    By by = this.getLocatorByString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
          return wait.until(
  ExpectedConditions.presenceOfElementLocated(by)
   );
   }
//
    public void swipeElementToLeft(String locator, String error_message) throws IllegalAccessException {
        By by = this.getLocatorByString(locator);
        if (driver instanceof AppiumDriver) {

            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(right_x, middle_y)
                    .waitAction(150)
                    .moveTo(left_x, middle_y)
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeElementToLeft() doesn't nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public By getLocatorByString(String locator_with_type) throws IllegalAccessException {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if(by_type.equals("xpath")) {
            return By.xpath(locator);
        }
        else if(by_type.equals("id")){
            return By.id(locator);
        }
        else if(by_type.equals("css")){
            return By.cssSelector(locator);
        } else {
            throw new IllegalAccessException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    public boolean isElementPresent(String locator) throws IllegalAccessException {
        return getAmountOfElements(locator)>0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts) throws IllegalAccessException {

        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_message, amount_of_attempts);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message, amount_of_attempts);
                }
            }
            ++current_attempts;
        }
    }
    public String takeScreenShot(String name) {
        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("Screenshot was taken: " + path);

        } catch (Exception e) {
            System.out.println("Cannot take screenshot: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path){
        byte[] bytes = new byte[0];

        try{
            bytes = Files.readAllBytes(Paths.get(path));
        }
        catch (IOException e){
            System.out.println("Cannot take bytes from screenshot: " + e.getMessage());
        }
        return bytes;
    }
}
