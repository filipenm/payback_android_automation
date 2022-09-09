package payback.core.config;

import io.qameta.allure.Step;
import payback.core.helpers.BaseHelper;


public class Configuration extends BaseHelper {

    public static int WEBDRIVER_WAIT;
    public static int WEB_DRIVER_WAIT_SHORT;
    public static String APPIUM_URL;
    public static String PLATFORM_NAME;
    public static String APP_ACTIVITY;
    public static String PLATFORM_VERSION;
    public static String APP_PATH;
    public static String APP_PATH_PREVIOUS_VERSION;
    public static String ADB_PATH;
    public static String APP_PACKAGE;
    public static String APP_BUNDLE_ID;
    public static String AUTOMATION_NAME;
    public static String DEVICE_NAME;
    public static String DEVICE_ORIENTATION;
    public static boolean ALERTS_AUTO_DISMISS;
    public static boolean ALERTS_AUTO_ACCEPT;
    public static boolean KEYBOARD_HIDE;
    public static boolean DEVICE_BIOMETRICS_TOUCH_SUPPORT;
    public static boolean DEVICE_BIOMETRICS_FACE_SUPPORT;
    public static boolean VIDEO_RECORD;
    public static String VIDEO_PATH;
    public static boolean VIDEO_SAVE_ON_FAILURE_ONLY;
    public static String DEVICE_LANGUAGE;
    public static String DEVICE_LOCALE;
    public static int ADB_EXEC_TIMEOUT;
    public static int ANDROID_INSTALL_TIMEOUT;
    public static String LOG_PATH;
    public static String SERVER;
    public static boolean FIRST_INSTALLATION = true;
    protected final String ANDROID_PROPERTIES_FILE_PATH = "android.properties";


    @Step("Initiate platform name and properties")
    public void init() {
        PLATFORM_NAME = "android";
        loadAndroidProperties(ANDROID_PROPERTIES_FILE_PATH);
    }

    @Step("Load android properties")
    private void loadAndroidProperties(String propertiesFilePath) {
        app.properties().loadFile(propertiesFilePath);
        WEBDRIVER_WAIT = Integer.parseInt(app.properties().getPropertyValueAsResource("webdriver.wait"));
        APPIUM_URL = app.properties().getPropertyValueAsResource("appium.url");
        VIDEO_RECORD = Boolean.parseBoolean(app.properties().getPropertyValueAsResource("video.record"));
        VIDEO_PATH = app.properties().getPropertyValueAsResource("video.path");
        VIDEO_SAVE_ON_FAILURE_ONLY = Boolean.parseBoolean(app.properties().getPropertyValueAsResource("video.save.onFailureOnly"));
        PLATFORM_VERSION = app.properties().getPropertyValueAsResource("platform.version");
        DEVICE_NAME = app.properties().getPropertyValueAsResource("device.name");
        DEVICE_ORIENTATION = app.properties().getPropertyValueAsResource("device.orientation");
        APP_BUNDLE_ID = app.properties().getPropertyValueAsResource("app.bundleId");
        APP_PATH = app.properties().getPropertyValueAsResource("app.path");
        APP_ACTIVITY = app.properties().getPropertyValueAsResource("app.activity");
        APP_PATH_PREVIOUS_VERSION = app.properties().getPropertyValueAsResource("app.path.previousVersion");
        AUTOMATION_NAME = app.properties().getPropertyValueAsResource("automation.name");
        ALERTS_AUTO_DISMISS = Boolean.parseBoolean(app.properties().getPropertyValueAsResource("alerts.auto.dismiss"));
        ALERTS_AUTO_ACCEPT = Boolean.parseBoolean(app.properties().getPropertyValueAsResource("alerts.auto.accept"));
        KEYBOARD_HIDE = Boolean.parseBoolean(app.properties().getPropertyValueAsResource("keyboard.hide"));
        DEVICE_LANGUAGE = app.properties().getPropertyValueAsResource("device.language");
        DEVICE_LOCALE = app.properties().getPropertyValueAsResource("device.locale");
        DEVICE_BIOMETRICS_TOUCH_SUPPORT = Boolean.parseBoolean(app.properties().getPropertyValueAsResource("device.biometrics.touch.support"));
        DEVICE_BIOMETRICS_FACE_SUPPORT = Boolean.parseBoolean(app.properties().getPropertyValueAsResource("device.biometrics.face.support"));
        LOG_PATH = app.properties().getPropertyValueAsResource("log.path");
        WEB_DRIVER_WAIT_SHORT = Integer.parseInt(app.properties().getPropertyValueAsResource("webdriver.wait.short"));
        SERVER = app.properties().getPropertyValueAsResource("server");

        ADB_EXEC_TIMEOUT = Integer.parseInt(app.properties().getPropertyValueAsResource("adb.exec.timeout")) * 1000;
        ANDROID_INSTALL_TIMEOUT = Integer.parseInt(app.properties().getPropertyValueAsResource("android.install.timeout")) * 1000;
        APP_PACKAGE = app.properties().getPropertyValueAsResource("app.package");
        APP_BUNDLE_ID = APP_PACKAGE;
        ADB_PATH = app.properties().getPropertyValueAsResource("adb.path");
    }

    @Step("Run driver")
    public void runDriver() {
        app.appium().initDriver();
    }

    @Step("Install app")
    public void installApp() {
        installApp(Configuration.APP_PATH);
    }

    @Step("Install app \"{appPath}\"")
    public void installApp(String appPath) {
        app.appium().installApp(appPath);
    }

    @Step("Uninstall app \"{bundleId}\"")
    public void uninstallApp(String bundleId) {
        app.appium().uninstallApp(bundleId);
    }

    @Step("Uninstall app \"{appPath}\" and if installed")
    public void uninstallAppIfInstalled(String appPath, String bundleId) {
        app.log().debug(appPath);
        if (app.appium().isAppInstalled(appPath))
            uninstallApp(bundleId);
    }

    @Step("Uninstall app if installed")
    public void uninstallAppIfInstalled() {
        uninstallAppIfInstalled(Configuration.APP_PATH, Configuration.APP_BUNDLE_ID);
    }

    @Step("Launch app with 'Bundle ID' = \"{bundleId}\"")
    public void launchApp(String bundleId) {
        app.appium().launchApp(bundleId);
    }

    @Step("Launch app")
    public void launchApp() {
        launchApp(Configuration.APP_BUNDLE_ID);
    }

    @Step("Install and launch app")
    public void installAndLaunchApp() {
        if (!FIRST_INSTALLATION) {
            uninstallAppIfInstalled();
            installApp();
            launchApp();
        } else {
            FIRST_INSTALLATION = false;
        }
    }

    @Step("Close app")
    public void closeApp() {
        app.appium().closeApp(Configuration.APP_BUNDLE_ID);
    }

    @Step("Close app")
    public void stopDriver() {
        if (app.appium().isSessionAlive()) {
            app.appium().quit();
        }
    }
}
