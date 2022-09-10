package payback.core.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import payback.core.config.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverHelper extends BaseHelper {
    private static AppiumDriver<MobileElement> appiumDriver;

    public AppiumDriver<MobileElement> getDriver() {
        return appiumDriver;
    }

    public boolean isSessionAlive() {
        boolean isSessionAlive = (appiumDriver != null) && (appiumDriver.getSessionId() != null);
        app.log().debug(isSessionAlive);
        if (!isSessionAlive)
            app.log().warn("Appium session is not run");
        return isSessionAlive;
    }

    public void initDriver() {
        app.log().debug();
        if (!isSessionAlive()) appiumDriver = getAndroidDriver();
        else app.log().error("Platform '" + Configuration.PLATFORM_NAME + "' is not supported");
    }

    private AppiumDriver<MobileElement> getAndroidDriver() {
        app.log().debug();
        AppiumDriver<MobileElement> androidDriver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            capabilities.setCapability(MobileCapabilityType.APP, Configuration.APP_PATH);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Configuration.DEVICE_NAME);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Configuration.PLATFORM_NAME);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Configuration.PLATFORM_VERSION);
            capabilities.setCapability("appPackage", Configuration.APP_PACKAGE);
            capabilities.setCapability("appActivity", Configuration.APP_ACTIVITY);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, Configuration.AUTOMATION_NAME);
            capabilities.setCapability("autoDismissAlerts", Configuration.ALERTS_AUTO_DISMISS);
            capabilities.setCapability("autoAcceptAlerts", Configuration.ALERTS_AUTO_ACCEPT);
            capabilities.setCapability("language", Configuration.DEVICE_LANGUAGE);
            capabilities.setCapability("locale", Configuration.DEVICE_LOCALE);
            capabilities.setCapability("adbExecTimeout", Configuration.ADB_EXEC_TIMEOUT);
            capabilities.setCapability("androidInstallTimeout", Configuration.ANDROID_INSTALL_TIMEOUT);
            androidDriver = new AndroidDriver<>(new URL(Configuration.APPIUM_URL), capabilities);
        } catch (MalformedURLException | SessionNotCreatedException e) {
            app.log().error("Cannot start a new remote appium session on Android", e);
        }
        return androidDriver;
    }

    public AppiumDriverHelper type(By elementLocator, String text) {
        app.log().debug(elementLocator, text);
        getDriver().findElement(elementLocator).sendKeys(text);
        return this;
    }

    public AppiumDriverHelper tap(By elementLocator) {
        app.log().debug(elementLocator);
        getDriver().findElement(elementLocator).click();
        return this;
    }

    public void quit() {
        app.log().debug();
        appiumDriver.quit();
    }

    public MobileElement findElement(By elementLocator) {
        app.log().debug(elementLocator);
        MobileElement element = getDriver().findElement(elementLocator);
        return element;
    }

    public void installApp(String appPath) {
        app.log().debug(appPath);
        if (app.appium().isSessionAlive()) {
            try {
                appiumDriver.installApp(appPath);
            } catch (Exception e) {
                app.log().error("Cannot install app: " + appPath, e);
            }
        }
    }

    public boolean isAppInstalled(String appPath) {
        app.log().debug(appPath);
        boolean isAppInstalled = false;
        if (isSessionAlive())
            isAppInstalled = appiumDriver.isAppInstalled(Configuration.APP_BUNDLE_ID);
        app.log().debug(isAppInstalled);
        return isAppInstalled;
    }

    public void uninstallApp(String bundleId) {
        app.log().debug(bundleId);
        if (isSessionAlive())
            appiumDriver.removeApp(bundleId);
    }

    public AppiumDriverHelper closeApp(String bundleId) {
        app.log().debug(bundleId);
        if (isSessionAlive()) {
            try {
                appiumDriver.terminateApp(bundleId);
            } catch (WebDriverException e) {
                app.log().warn("The application with bundleId cannot " + bundleId + " be closed\n" + e);
            }
        }
        return this;
    }

    public AppiumDriverHelper launchApp(String bundleId) {
        if (app.appium().isSessionAlive())
            app.android().launchApp(bundleId);
        return this;
    }
}
