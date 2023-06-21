package step_definitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import base.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelReader;

public class Hooks extends Base{
	
	@Before
	public void setup() throws IOException, InvalidFormatException {

		//config file is property file so we need this
		config = new Properties();
		// to file read we need this
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\configuration\\config.properties");
		// we need to load this
		config.load(fis);
		
		//initialize excel file
		excelReader = new ExcelReader();
		//testData = excelReader.getData(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\loginData.xlsx" , "alibabaLogin");
		testData = excelReader.getData(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\loginData.xlsx" , "loginData");
		
		switch (config.getProperty("browser")) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			//for popup hadle
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.setHeadless(false);
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().driverVersion("0.29.1").setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(false);
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
			driver = new InternetExplorerDriver(internetExplorerOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			driver = new EdgeDriver(edgeOptions);
			break;
		} 
		
		host = config.getProperty("env");
		driver.manage().window().maximize();
		int seconds = Integer.parseInt(config.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(seconds,TimeUnit.SECONDS); // Implicit wait
		System.out.println("before run");
	}
	
	
	@After
	public void tearDown(Scenario scenario) throws IOException {
		try {
			String screenshotName= scenario.getName().replace("", "");
			if(scenario.isFailed()) {
				scenario.log("this is my failure message");
				TakesScreenshot ts = (TakesScreenshot)driver;
				byte[]screenshot = ts.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", screenshotName);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		if (scenario.isFailed()) {
//			TakesScreenshot ts = (TakesScreenshot)driver;
//			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
//			scenario.attach(src, "image/png", "screenshot");
//			scenario.log("this is my failed test case screenshot");
//			scenario.log("Scenario: " + scenario.getName() );
//		}
		fis.close();
		System.out.println("After run");
		driver.quit();
		
	}

	
	
}//class
