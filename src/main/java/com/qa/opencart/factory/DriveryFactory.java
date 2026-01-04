package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexceptions.FrameException;

public class DriveryFactory {
	
	WebDriver driver;
	optionManager options_manager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser Name is====> " + browserName);
		options_manager = new optionManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver(options_manager.getChromeOptions());
			tlDriver.set(new ChromeDriver(options_manager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(options_manager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(options_manager.getFirefoxOptions()));
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;

		default:
			System.out.println("plz pass the right Browser====> " + browserName);
			throw new FrameException("NO_BROWSER_FOUND_EXCEPTION");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
		
	}
	
	//return the thread local copy of the driver
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		
		//mvn clean install -Devn="which environment in need to execute" -- help to run cmd line and jenkins
		//mvn clean install
		
		Properties prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on env: " + envName);
		try {
		if(envName == null) {
			System.out.println("no env is given... hence running it on QA env....");
			try {
				ip = new FileInputStream("./src/main/resource/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			switch (envName.toLowerCase().trim()) {
			case "qa":
					ip = new FileInputStream("./src/main/resource/config/qa.config.properties");
				
				break;
			case "dev":
				ip = new FileInputStream("./src/main/resource/config/dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/main/resource/config/stage.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/main/resource/config/uat.config.properties");
				break;
			case "prod":	
				ip = new FileInputStream("./src/main/resource/config/config.properties");
				break;

			default:
				System.out.println("plz pass the right environment ====> " + envName);
				throw new FrameException("NO_VALID_ENV_GIVEN");
			}
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return prop;
		
	}
	
	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
