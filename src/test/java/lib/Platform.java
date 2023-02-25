package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUMURL = "http://127.0.0.1:4723/wd/hub";
    private static Platform instance;
    private Platform (){}
    public static Platform getInstance(){
        if(instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL = new URL (APPIUMURL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else if (this.isMW()) {
            return new ChromeDriver(this.getMWChromeOption());
        } else {
            throw new Exception("Cannot detect type of Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }
    public boolean isMW()
    {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("avd", "and80");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\dmitr\\OneDrive\\Desktop\\Автоматизация МП\\02 Установка и настройка инструментов\\04. Архив с утилитами\\Tools\\org.wikipedia.apk");
        return capabilities;
    }
    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14");
        capabilities.setCapability("platformVersion", "16.4");
        return capabilities;
    }

    private ChromeOptions getMWChromeOption()
        {
            Map<String, Object> deviceMetrics = new HashMap<String, Object>();
            deviceMetrics.put("widht", 360);
            deviceMetrics.put("height", 640);
            deviceMetrics.put("pixelRation", 3.0);

            Map<String, Object> mobileemulation = new HashMap<String, Object>();
            mobileemulation.put("deviceMetrics", deviceMetrics);
            mobileemulation.put("userAgent", "Mozilla/5.0 (Linux; Android 8.0.0; Android SDK built for x86) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Mobile Safari/537.36");

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("window-size=360,640");

            return chromeOptions;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    public String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }

}
