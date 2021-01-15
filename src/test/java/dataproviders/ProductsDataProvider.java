package dataproviders;

import org.testng.annotations.DataProvider;
import util.ExcelUtils;

public class ProductsDataProvider {

    @DataProvider(name = "searchItemsDP")
    public Object[][] getProducts() throws Exception {
        Object values [][]= ExcelUtils.getExcelData(System.getProperty("user.dir")+"//src//test//resources//testdata//" + "product_list.xlsx");
        return values;
    }
}
