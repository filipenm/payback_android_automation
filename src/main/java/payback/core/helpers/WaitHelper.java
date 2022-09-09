package payback.core.helpers;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import payback.core.config.Configuration;

public class WaitHelper extends BaseHelper {

    public void waitForVisibility(MobileElement... elements) {
        for (MobileElement element : elements)
            waitForVisibility(element);
    }

    public void sleep(int seconds) {
        app.log().debug(seconds + " seconds");
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            app.log().error(e.getMessage(), e);
        }
    }

    public void waitForVisibility(By elementLocator) {
        try {
            app.log().debug(elementLocator);
            WebDriverWait wait = new WebDriverWait(app.appium().getDriver(), Configuration.WEBDRIVER_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            app.log().debug(true);
        } catch (TimeoutException e) {
            app.log().debug(false);
        }
    }
}
