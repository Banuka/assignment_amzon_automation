package amazon;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
        waitForPageLoad();
    }

    @FindBy(xpath = "//span[@id='submit.buy-now']")
    protected WebElement btnBuyNow;

    public void clickBuyNow(){
        btnBuyNow.click();
        waitForPageLoad();
    }
}
