package util;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import static base.BaseTest.properties;

public class DriverConnection {
    private String browser = "chrome";
    private WebDriver driver;


    public WebDriver getConnection(){
        String browser = properties.getProperty("browser");
        try {
            switch (browser){
                case "chrome":
                    driver = initChromeDriver();
                break;
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            String star_url = properties.getProperty("base.url");
            driver.get(star_url);
        }catch (Throwable e){
            e.printStackTrace();
            throw e;
        }
        return driver;
    }

    private WebDriver initChromeDriver(){
        System.setProperty("webdriver.chrome.driver",DriverConnection.class.getClassLoader().getResource("drivers/chromedriver.exe").getPath());
        driver = new ChromeDriver();
        return driver;
    }

    public void closeDriver(){
        driver.quit();
    }

    public static Properties loadProps() throws IOException {
        Properties props = new Properties();
        URL inputStream = null;
        inputStream = DriverConnection.class.getClassLoader().getResource("config/env.properties");
        FileInputStream fileInputStream = new FileInputStream(inputStream.getPath());
        props.load(fileInputStream);
        return props;
    }
}
