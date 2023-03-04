package lib;

import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.qameta.allure.*;

import java.io.FileOutputStream;
import java.util.Properties;

public class CoreTestCase {
    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();
    }
    @After
    @Step("Remove driver and session")
    public void tearDown()
    {
       // driver.quit();
    }

    @Step("Rotate emulator to Portrait view")
    protected void rotateScreenPortrait()
    {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() doesn't nothing for platform " +Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Rotate emulator to Landscape view")
    protected void rotateScreenLandscape()
    {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() doesn't nothing for platform " +Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Set the app to background")
    protected void backgroundApp(int seconds)
    {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundApp() doesn't nothing for platform " +Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Open Wikipedia start page on Mobile web")
    protected void openWikiWebPageForMobileWeb()
    {
        if(Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        }
        else {
        System.out.println("Method openWikiWebPageForMobileWeb() doesn't nothing for platform " +Platform.getInstance().getPlatformVar());
    }
    }

    private void createAllurePropertyFile()
    {
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();
        }
        catch (Exception e){
            System.out.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }

    }
}
