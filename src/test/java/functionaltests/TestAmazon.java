package functionaltests;

import amazon.ProductPage;
import amazon.SearchResultsPage;
import base.BaseTest;
import dataproviders.ProductsDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAmazon extends BaseTest {

    private SearchResultsPage searchResultsPage;
    private ProductPage productPage;


    @Test
    public void test_title(){
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "test wegafgagagssdd sddd");
    }

    @Test(dataProvider = "searchItemsDP", dataProviderClass = ProductsDataProvider.class)
    public void test_ProductSearch(String searchText, String prodName) throws Exception {
        searchResultsPage = amazonHomePage.searchProduct(searchText);
        productPage = searchResultsPage.navigateToSearchProduct(prodName);
        if(productPage == null){
            Assert.fail("The given product not found in the search result.");
        }
        productPage.clickBuyNow();
        System.out.println("*************************************************************************************");
    }
}
