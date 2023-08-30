package ae.livo.admin.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	/*
	 * WebDriver, Properties, Logs, ExtentReports, DB Excel, Mail
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() throws InterruptedException {
	    if (driver == null) {
	        try {
	            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
	            config.load(fis);
	            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
	            OR.load(fis);

	            if (config.getProperty("browser").equals("edge")) {
	                driver = new EdgeDriver();
	            } else if (config.getProperty("browser").equals("chrome")) {
	                driver = new ChromeDriver();
	            }

	            driver.manage().window().maximize();
	            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	            driver.get(config.getProperty("testSiteUrl"));
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the exception properly (log it or throw a custom exception)
	        }
	    }
	}

	@AfterSuite
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}

}
