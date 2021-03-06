package SupportingClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.net.UrlChecker;




public class browserLaunching {
	
	public static WebDriver driver=null;
	public static WebDriverWait wait=null; 
	 public WebDriver launch_browser(String browser,String url)
	 {
		
		 DesiredCapabilities capabilities = new DesiredCapabilities();
			switch(browser) 
			{
			case "IE":
				
				 String driver_path = "D:\\sas\\";  //C:\\Windows\\System32\\
				 System.setProperty("webdriver.ie.driver",driver_path + "IEDriverServer.exe");
				 	capabilities.setCapability("browser", "IE");
				 	capabilities.setCapability("browser_version", "10.0");
				 	capabilities.setCapability("os", "Windows");
				 	capabilities.setCapability("os_version", "7");
				 	capabilities.setCapability("resolution", "800x600");
				 	capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
				 	capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				    capabilities.setCapability("ignoreZoomSetting", true);
					capabilities.setCapability("javascriptEnabled", true);
					capabilities.setCapability("platform", "WINDOWS");
					capabilities.setCapability("ignoreProtectedModeSettings", true);
					capabilities.setCapability("ie.ensureCleanSession", true);
					capabilities.setCapability("browserName", "internet explorer");
					capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,"dismiss");
					capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,0);
					capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,true);
					capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,true);
					capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,false);
					//InternetExplorerDriver.ignoreSynchronization = true;
					capabilities.setCapability("browserstack.debug", true);
					System.out.println(browser);
				
				 driver = new InternetExplorerDriver(capabilities);
				 driver.manage().deleteAllCookies();
				 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				 driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
				 driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
				
				 driver.get(url);
				 break;
				 
			case "Chrome":
				
					System.out.println(browser);
				
					String driver_path1 = "D:\\sas\\";
				
					System.setProperty("webdriver.chrome.driver",driver_path1 + "chromedriver.exe");
				 	capabilities.setCapability("browser", "Chrome");
					capabilities.setCapability("browser_version", "56.0");
					capabilities.setCapability("os", "Windows");
					capabilities.setCapability("os_version", "7");
					capabilities.setCapability("resolution", "800x600");
					capabilities.setCapability("browserstack.debug", true);
					// System.setProperty("webdriver.chrome.port","26244");
					driver = new ChromeDriver(capabilities);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					wait = new WebDriverWait(driver, 30);
					driver.get(url);
					break;
				
				
			case "firefox":
				
				  	System.out.println(browser);
				  	System.setProperty("webdriver.gecko.driver","D:\\sas\\geckodriver.exe");
				
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
					capabilities.setCapability("ignoreZoomSetting", true);
					capabilities.setCapability("javascriptEnabled", true);
					capabilities.setCapability("platform", "WINDOWS");
					capabilities.setCapability("ignoreProtectedModeSettings", true);
					
					
					capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,"dismiss");
					capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,0);
					capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,true);
					capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,true);
					capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,false);
					
					driver=new FirefoxDriver(capabilities);  
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
					//wait = new WebDriverWait(driver, 30);
					driver.get(url);
					break;
				
			
			
		default	:
			 
			System.out.println("not a valid browser");
			break;
			
	
		
		}
		return driver;
	 }
	 public void login(String url,String username,String password)
		{
			 //driver.get(url);
			 driver.findElement(By.name("answer(Object::UserDetail::userName)")).sendKeys(username);
			 driver.findElement(By.name("answer(Object::UserDetail::passWord)")).sendKeys(password);
			 driver.findElement(By.xpath("//input[@value='Log In']")).click(); 
		}

		
	 public void stop_browser()
		{
			driver.quit();
		}
}
