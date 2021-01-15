package amazon;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForPageLoad();
    }

    @FindBy(xpath = "//div[@data-component-type='s-search-result']//a[@class='a-link-normal a-text-normal']")
    private List<WebElement> listSearchResults;

    /**
     *
     * @param productName
     * @return
     */
    public ProductPage navigateToSearchProduct(String productName){
        for(WebElement w : listSearchResults){
            if(w.getText().equals(productName)){
                w.click();
                ProductPage productPage = new ProductPage(this.webDriver);
                PageFactory.initElements(webDriver, productPage);
                return productPage;
            }
        }
        return null;
    }
}
