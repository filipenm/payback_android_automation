package payback.core.helpers;

public class AndroidHelper extends BaseHelper {
    public AndroidHelper launchApp(String bundleId) {
        app.log().debug(bundleId);
        app.appium().getDriver().activateApp(bundleId);
        return this;
    }
}

