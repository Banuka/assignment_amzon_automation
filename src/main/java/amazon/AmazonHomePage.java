package amazon;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage extends BasePage {
    public AmazonHomePage(WebDriver driver) {
        super(driver);
        waitForPageLoad();
    }

    //input[@id='twotabsearchtextbox']

    @FindBy(id = "twotabsearchtextbox")
    private WebElement txtSearchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement btnGo;

    public SearchResultsPage searchProduct(String prodName){
        txtSearchBox.sendKeys(prodName);
        btnGo.click();
        SearchResultsPage searchResultsPage = new SearchResultsPage(this.webDriver);
        PageFactory.initElements(webDriver, searchResultsPage);
        return searchResultsPage;
    }
}
