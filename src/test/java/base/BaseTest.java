package base;

import amazon.AmazonHomePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import util.DriverConnection;
import util.ScreenShotHelper;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {
    private DriverConnection driverConnection;
    protected static WebDriver webDriver;
    protected AmazonHomePage amazonHomePage;
    protected static ExtentTest extentTest;
    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;

    public static Properties properties;

    static {
        try{
            properties = DriverConnection.loadProps();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void start_test(){
        extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//src//test//resources//reports//amazon_results.html");
        extentHtmlReporter.config().setEncoding("utf-8");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
    }

    @BeforeMethod()
    public void set_up(Method method){
        driverConnection = new DriverConnection();
        webDriver = driverConnection.getConnection();
        amazonHomePage = new AmazonHomePage(webDriver);
        extentTest = extentReports.createTest(method.getName());
        PageFactory.initElements(webDriver, amazonHomePage);


    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        String methodName = result.getMethod().getMethodName();
        if(result.getStatus() == ITestResult.FAILURE){
            String screenshot = ScreenShotHelper.getScreenshot(webDriver);
            extentTest.fail("<details><summary><b><font color=red>Test was failed click to see details:</font></b></summary>"
            + result.getThrowable().getMessage() + "</details> \n");
            extentTest.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());

            String logText = "<b>Test Method" + methodName + " Failed</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.CYAN);
            extentTest.log(Status.FAIL, m);
        } else if(result.getStatus() == ITestResult.SUCCESS){
            String logText = "<b>Test Method " + methodName + " Successful</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            extentTest.log(Status.PASS, m);
        }
        driverConnection.closeDriver();
    }

    @AfterClass
    public static void endTest(){
        extentReports.flush();
    }
}
