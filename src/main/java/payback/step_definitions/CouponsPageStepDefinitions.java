package payback.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import payback.core.ApplicationManager;

public class CouponsPageStepDefinitions {

    protected ApplicationManager app = ApplicationManager.get();

    private static final By filterButton = By.id("filter_button");
    private static final By partnerButton = By.id("image");
    private static final By activateButton = By.id("not_activated_button");
    private static final By activatedCoupon = By.id("activated_icon");
    private static final By redeemOffline = By.id("redeem_offline_button");

    @And("I filter by partner and activate a coupon")
    public void i_filter_by_partner_and_activate_coupon() {
        app.log().info();
        app.appium().tap(filterButton);
        app.appium().tap(partnerButton);
        app.waits().waitForVisibility(activateButton);
        app.appium().tap(activateButton);
    }

    @Then("I see an activated coupon")
    public void i_see_an_activated_coupon() {
        app.log().info();
        Assertions.assertTrue(app.appium().findElement(activatedCoupon).isDisplayed());
        Assertions.assertTrue(app.appium().findElement(redeemOffline).isDisplayed());
    }
}
