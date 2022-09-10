package payback.step_definitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import payback.core.ApplicationManager;

public class MainPageStepDefinitions {
    private static final By couponsButton = By.id("coupon_center_dest");
    private static final By popup = By.xpath("//*[contains(@text, 'Deine MOBILE PAYBACK KARTE hat ein neues')]");
    private static final By dismissPopup = By.xpath("//*[contains(@text, 'Deine MOBILE PAYBACK KARTE hat ein neues')]");
    protected ApplicationManager app = ApplicationManager.get();

    @And("I proceed to coupons page")
    public void i_proceed_to_coupons_page() {
        app.log().info();
        app.waits().waitForVisibility(popup);
        app.appium().tap(dismissPopup);
        app.appium().tap(couponsButton);
    }
}
