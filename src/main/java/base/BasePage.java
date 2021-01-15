package base;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver webDriver;
    JavascriptExecutor js;

    public BasePage(WebDriver driver){
        this.webDriver = driver;
    }

    public void waitForPageLoad(){
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    @NullableDecl
                    @Override
                    public Boolean apply(@NullableDecl WebDriver input) {
                        return ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(pageLoadCondition);
    }


}
