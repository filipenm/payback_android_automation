package payback.step_definitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import payback.core.ApplicationManager;

public class MainPageStepDefinitions {
    protected ApplicationManager app = ApplicationManager.get();

    private static final By couponsButton = By.id("coupon_center_dest");
    private static final By popup = By.id("ve_feed_refresh_layout");
    private static final By dismissPopup = By.id("ve_feed_refresh_layout");

    @And("I proceed to coupons page")
    public void i_proceed_to_coupons_page() {
        app.log().info();
        if (app.appium().findElement(popup).isDisplayed())
            app.appium().tap(dismissPopup);
        app.appium().tap(couponsButton);
    }
}
