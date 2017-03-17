package SupportingClasses;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.sql.Connection;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.Timer;
import com.thoughtworks.selenium.webdriven.commands.Open;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;

import KeywordDrivenFramework.StarrassistBenefitsCheck;


public class UIoperartions_old<IWebDriver> {
	public static WebDriver driver;
	
	 public static WebDriverWait wait;
	
 //*****************************browser operations**************************************************************************
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
    
	 public void stop_browser()
		{
			driver.quit();
		}
//***************************************************************************************************************************
	 
	 

//**************************************UI operations***************************************************************************
public void perform(String p,String operation,String objectType,String value,String dbcolumn_name,String dataFlag,databaseOperartions input,databaseOperartions output,WebDriver driver,String waitingTime) throws Exception
{
	//driver=UIoperartions.driver;
	//wait=UIoperartions.wait;
	
	long waitingTimeinseconds=Long.parseLong(waitingTime);
       
switch (operation.toUpperCase())
{
//-------------------------------click operation-------------------------------------------------------------------------------
case "CLICK":
        	try
        	{
        	wait = new WebDriverWait(driver, waitingTimeinseconds);
        	wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
    		wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	driver.findElement(this.getObject(p,objectType)).click();
        	
        	driver.getTitle();
        	break;
        	}
        	catch(StaleElementReferenceException|TimeoutException|JavascriptException e)
        	{
        		wait = new WebDriverWait(driver, waitingTimeinseconds);
        		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
             	driver.findElement(this.getObject(p,objectType)).click();
             	break;
        	}
        	
 //-------------------------------------       	
        	
        	
        	
            	
 case "SETTEXT":
     try{
    	   

        	switch(dataFlag)
        	{
        	case "Read":
        		wait = new WebDriverWait(driver, 30);
        		 wait = new WebDriverWait(driver, waitingTimeinseconds);
     		   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
     		   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
         	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        	
        		System.out.println(input.read_data(dbcolumn_name));
        		driver.findElement(this.getObject(p,objectType)).clear();
        		driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
        		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        		//driver.getTitle();
        		//wait = new WebDriverWait(driver, waitingTimeinseconds);	
        		
        		//driver.findElement(this.getObject(p,objectType)).click();
        		break;
        		
               
        	case "Default":
        		wait = new WebDriverWait(driver, waitingTimeinseconds);
        		wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        		wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
        		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        		driver.findElement(this.getObject(p,objectType)).clear();
         	   	driver.findElement(this.getObject(p,objectType)).sendKeys(value);
         	   driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
         	    break;
        	}
        	break;
      }
   catch(StaleElementReferenceException|TimeoutException|JavascriptException e)
    {
    	   switch(dataFlag)
       	{
       	case "Read":
       		wait = new WebDriverWait(driver, waitingTimeinseconds);
       		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
       		driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
       		
            break;
              
       	case "Default":
       		wait = new WebDriverWait(driver, waitingTimeinseconds);
       		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	driver.findElement(this.getObject(p,objectType)).sendKeys(value);
        	
            break;
       	}
       	break;
    }

 //------------------------------------------------------------------------------------------------------------------
     
 case "GOTOURL":
	 
	 try
	 {
		 wait = new WebDriverWait(driver, waitingTimeinseconds);
         wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
         wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        
         driver.get(value);
         break;
	 }
	 catch(StaleElementReferenceException|TimeoutException e)
	 {
		 wait = new WebDriverWait(driver, waitingTimeinseconds);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
		 wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
 		
     	 driver.get(value);
     	break;
	 }
	 
 //--------------------------------------------------------------------------------------------------------------------
	 
 case "GETATTRIBUTE":
        try
        {
            	
            	wait = new WebDriverWait(driver, waitingTimeinseconds);
            	
            	
        		wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
        		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
                String text=browserLaunching.driver.findElement(this.getObject(p,objectType)).getAttribute("value");
                System.out.println(text+dbcolumn_name);
                output.write_data(dbcolumn_name,text);
                output.update_row();
                break;
        }
        catch(StaleElementReferenceException|TimeoutException e)
        {
        	
        	wait = new WebDriverWait(driver, waitingTimeinseconds);
        	wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
            String text=driver.findElement(this.getObject(p,objectType)).getAttribute("value");
            System.out.println(text+dbcolumn_name);
            output.write_data(dbcolumn_name,text);
            output.update_row();
            break;
        }
 //----------------------------------------------------------------------------------------------------------------------------       
            
 case "GETTEXT":
	 try
	 {
		        wait = new WebDriverWait(driver, waitingTimeinseconds);
		        wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
            	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
            	String text1=driver.findElement(this.getObject(p,objectType)).getText();
            	System.out.println(text1+dbcolumn_name);
            	output.write_data(dbcolumn_name,text1);
            	output.update_row();
                break;
	 }
	 catch(StaleElementReferenceException|TimeoutException e)
	 {
		    wait = new WebDriverWait(driver, waitingTimeinseconds);
		 	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
		 	String text1=driver.findElement(this.getObject(p,objectType)).getText();
		 	System.out.println(text1+dbcolumn_name);
		 	output.write_data(dbcolumn_name,text1);
		 	output.update_row();
		 	break;
	 }
	 
  //-----------------------------------------------------------------------------------------------------------------------------
	 
 case "SELECT":
	 try
	 {
	 
	        System.out.println("operation select performing");
        	switch(dataFlag)
        	{
        	   
        	case "Read":
        		System.out.println(waitingTimeinseconds);
        	   wait = new WebDriverWait(driver, waitingTimeinseconds);
        	   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
        	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        	   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        		
        		Select dropdown = new Select(driver.findElement(this.getObject(p,objectType)));
        		
        		dropdown.selectByVisibleText(input.read_data(dbcolumn_name));
        		driver.getTitle();
        		break;
        		
        	case "Default":
        		
        		wait = new WebDriverWait(driver, waitingTimeinseconds);
        	    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
                wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
         		Select dropdown1 = new Select(driver.findElement(this.getObject(p,objectType)));
         	    dropdown1.selectByVisibleText(value);
         		
         		break;
        	}
        	break;
	 }
	 catch(StaleElementReferenceException|TimeoutException e)
	 {
		 System.out.println("operation select performing");
     	switch(dataFlag)
     	{
     	   
     	case "Read":
     		
     		wait = new WebDriverWait(driver, waitingTimeinseconds);
     	    wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
     		Select dropdown = new Select(driver.findElement(this.getObject(p,objectType)));
     		
     		dropdown.selectByVisibleText(input.read_data(dbcolumn_name));
     		break;
     		
     	case "Default":
     		
     		wait = new WebDriverWait(driver, waitingTimeinseconds);
     		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
      		Select dropdown1 = new Select(driver.findElement(this.getObject(p,objectType)));
      		dropdown1.selectByVisibleText(value);
      		break;
     	}
     	break;
	 }
//------------------------------------------------------------------------------------------------------------------------------------
	 
 case "MOUSEHOVER":
	 
	 try
	 {
		    wait = new WebDriverWait(driver, waitingTimeinseconds);
		    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
     	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	Actions mouse_hover = new Actions(driver);
			mouse_hover.moveToElement(driver.findElement(this.getObject(p,objectType))).build().perform();
			driver.getTitle();
        	break;
	 }
	 catch(StaleElementReferenceException|TimeoutException e)
	 {
		    wait = new WebDriverWait(driver, waitingTimeinseconds);	
		 	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
		 	Actions mouse_hover = new Actions(driver);
			mouse_hover.moveToElement(driver.findElement(this.getObject(p,objectType))).build().perform();
			break;
	 }

//-------------------------------------------------------------------------------------------------------------------------------------	 
	 
case "AUTOCOMPLETE":
	try
	{
        	switch(dataFlag)
        	{
        case "Read":
        	wait = new WebDriverWait(driver, waitingTimeinseconds);	
        	wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
            wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
        	driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        	
        	break;
        	
        case "Default":
        	wait = new WebDriverWait(driver, waitingTimeinseconds);	
        	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	driver.findElement(this.getObject(p,objectType)).sendKeys(value);
        	driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        	
        	break;
        	}
        	
        	break;
	}
	catch(StaleElementReferenceException|TimeoutException e)
	{
		switch(dataFlag)
    	{
    case "Read":
    	wait = new WebDriverWait(driver, waitingTimeinseconds);	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
    	driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
    	driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
    	break;
    	
    case "Default":
    	wait = new WebDriverWait(driver, waitingTimeinseconds);	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
    	driver.findElement(this.getObject(p,objectType)).sendKeys(value);
    	driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
    	break;
    	}
    	
    	break;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------        	
case "RADIOBUTTONVAL":
	try
	{
    	 
       
       wait = new WebDriverWait(driver, waitingTimeinseconds);	
       String dbvalue=input.read_data(dbcolumn_name);
       System.out.println(p+dbvalue+"']");
	   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	   
       driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
       driver.findElement(this.getObject(p,objectType)).click();
       System.out.println("Click Completed");
       break;
	}
	catch(StaleElementReferenceException|TimeoutException e)
	{
		 	System.out.println(p+value+"']");
		 	 wait = new WebDriverWait(driver, waitingTimeinseconds);	
		   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p+value+"']",objectType)));
		   
		   driver.findElement(this.getObject(p+value+"']",objectType)).click();
		  
		   System.out.println("Click Completed");
		   break;
	}

//-------------------------------------------------------------------------------------------------------------------------	
        	
case "ASSERTTEXT":
	try
	{
		        wait = new WebDriverWait(driver, waitingTimeinseconds);	
		        //wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)(this.getObject(p,objectType)));
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
    	        wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
         	    wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
         	   driver.getTitle();
    	       // wait = new WebDriverWait(driver, waitingTimeinseconds);	
    	   		break;
	} 	   		
	catch(StaleElementReferenceException|TimeoutException e)
	{
		    wait = new WebDriverWait(driver, waitingTimeinseconds);	
			wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
			//wait = new WebDriverWait(driver, waitingTimeinseconds);	
			break;
	}
    	   
//------------------------------------------------------------------------------------------------------------------   	
	
case "SCREENSHOT":
    	   
    	        System.out.println("Taking Screenshot"); 
    	   		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           
    	   		String Test_data_name=StarrassistBenefitsCheck.objectInput.read_data("test_data_name");
    	   		FileUtils.copyFile(scrFile, new File("D:\\sas\\sas1\\"+Test_data_name+".png"));
    	   		wait = new WebDriverWait(driver, waitingTimeinseconds);	
    	   		break;
    	   	
 //------------------------------------------------------------------------------------------------------------------------
    	   		
case "RADIOBUTTON":
	
try
{
	switch(dataFlag)
	{
	case "Read":
		   wait = new WebDriverWait(driver, waitingTimeinseconds);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
		   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
    	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
    	   List<WebElement> RadButtonList =driver.findElements(this.getObject(p,objectType));
   			for(int i=0; i< RadButtonList.size() ; i++)
   			{
   				System.out.println(((WebElement) RadButtonList.get(i)).getAttribute("value"));
   				System.out.println(input.read_data(dbcolumn_name));
   			if(((WebElement) RadButtonList.get(i)).getAttribute("value").equals(input.read_data(dbcolumn_name)))
   			{
   				
   				System.out.println("radio button clicked");
   				wait.until(ExpectedConditions.visibilityOf(RadButtonList.get(i)));
   			    //wait.until(ExpectedConditions.elementToBeClickable(RadButtonList.get(i)));
   			   // wait.until(ExpectedConditions.elementToBeSelected(RadButtonList.get(i)));
   				((WebElement) RadButtonList.get(i)).click();
   			  
   				
				
   			}
   			}
   			break;
	case "Default":
		   wait = new WebDriverWait(driver, waitingTimeinseconds);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
		   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
  	       wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
		   List RadButtonList1 =driver.findElements(this.getObject(p,objectType));
			for(int i=0; i< RadButtonList1.size() ; i++)
			{
			if(((WebElement) RadButtonList1.get(i)).getAttribute("value").equals(input.read_data(dbcolumn_name)))
			{
				
				((WebElement) RadButtonList1.get(i)).click();
				
				
			}
			}
			break;
	}
	break;
}
catch(TimeoutException|WebDriverException e)
{
	switch(dataFlag)
	{
	case "Read":
		   wait = new WebDriverWait(driver, waitingTimeinseconds);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
		   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
    	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
    	   List<WebElement> RadButtonList =driver.findElements(this.getObject(p,objectType));
   			for(int i=0; i< RadButtonList.size() ; i++)
   			{
   				System.out.println(((WebElement) RadButtonList.get(i)).getAttribute("value"));
   				System.out.println(input.read_data(dbcolumn_name));
   			if(((WebElement) RadButtonList.get(i)).getAttribute("value").equals(input.read_data(dbcolumn_name)))
   			{
   				
   				System.out.println("radio button clicked");
   				
   				((WebElement) RadButtonList.get(i)).click();
   				
   			}
   			}
   			break;
	case "Default":
		List<WebElement> CheckBoxList1 = driver.findElements(this.getObject(p,objectType));
			for(int i=0; i< CheckBoxList1.size() ; i++)
			{
			if(((WebElement) CheckBoxList1.get(i)).getAttribute("value").equals(input.read_data(dbcolumn_name)))
			{
				((WebElement) CheckBoxList1.get(i)).click();
				((WebElement) CheckBoxList1.get(i)).click();
			}
			}
			break;
	}
	break;
	
}

//-----------------------------------------------------------------------------------------------------------------------------------
case "DATEPICKER":
	
try{
  	   
       switch(dataFlag)
     	{
     	case "Read":
     		wait = new WebDriverWait(driver, 30);
     		 wait = new WebDriverWait(driver, waitingTimeinseconds);
  		   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
  		   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
      	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
     		driver.findElement(this.getObject(p,objectType)).clear();
     		System.out.println(input.read_data(dbcolumn_name));
     		driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
     		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
     		driver.getTitle();
     		//wait = new WebDriverWait(driver, waitingTimeinseconds);	
     		
     		//driver.findElement(this.getObject(p,objectType)).click();
     		break;
            
     	case "Default":
     		wait = new WebDriverWait(driver, 30);
     		 wait = new WebDriverWait(driver, waitingTimeinseconds);
  		   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
  		   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
      	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
     		//driver.findElement(this.getObject(p,objectType)).click();
      	   	driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
      	  //driver.findElement(this.getObject(p,objectType)).click();
             break;
     	}
     	break;
   }
catch(StaleElementReferenceException|TimeoutException e)
 {
 	   switch(dataFlag)
    	{
    	case "Read":
    		wait = new WebDriverWait(driver, 30);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
    		driver.findElement(this.getObject(p,objectType)).click();
    		driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
         break;
           
    	case "Default":
    		wait = new WebDriverWait(driver, 30);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
    		driver.findElement(this.getObject(p,objectType)).click();
     	driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
         break;
    	}
    	break;
 }

//----------------------------------------------------------------------------------------------------------------------------

case "TOGGLE":
	
	try
	{
	wait = new WebDriverWait(driver, 30);	
	 wait = new WebDriverWait(driver, waitingTimeinseconds);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
    break;
	}
	catch(StaleElementReferenceException|TimeoutException e)
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        break;
	}

//------------------------------------------------------------------------------------------------------------------------------
case "WAITLOAD":
	
	wait = new WebDriverWait(driver, waitingTimeinseconds); 
	wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	    WebElement element = driver.findElement(this.getObject(p,objectType));
	    element.isDisplayed();
	   // element isSucceededed = element != null;
	break;
	
//-----------------------------------------------------------------------------------------------------------------------------------
case "SENDKEYS":
	
	wait = new WebDriverWait(driver, waitingTimeinseconds); 
	wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	WebElement element_enter = driver.findElement(this.getObject(p,objectType));
	element_enter.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
	element_enter.sendKeys(Keys.RETURN);
	
	break;
	
case "CONTOPERATION":
	
	 wait = new WebDriverWait(driver, waitingTimeinseconds); 
	 wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	 WebElement element1= driver.findElement(this.getObject(p,objectType));
     Actions builder = new Actions(driver);
     Actions seriesOfActions = builder.moveToElement(element1).click().sendKeys(element1, input.read_data(dbcolumn_name));
     seriesOfActions.perform();
     
     break;
     
case "IMGIDVISIBLE":
	
	   wait = new WebDriverWait(driver, waitingTimeinseconds);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getObject(p,objectType)));
	   break;
	
	
	
  default:
	    System.out.println("operations not  performed");
	    break;
}
        
}

    
 //============================================Locator Action====================================================================  
    private By getObject(String p,String objectType) throws Exception{
        //Find by xpath
        if(objectType.equalsIgnoreCase("XPATH")){
            
            return By.xpath(p);
        }
        //find by class
        else if(objectType.equalsIgnoreCase("CLASSNAME")){
            
            return By.className(p);
            
        }
        //find by name
        else if(objectType.equalsIgnoreCase("NAME")){
            
            return By.name(p);
            
        }
        //Find by css
        else if(objectType.equalsIgnoreCase("CSS")){
            
            return By.cssSelector(p);
            
        }
        //find by link
        else if(objectType.equalsIgnoreCase("LINK")){
            
            return By.linkText(p);
            
        }
        //find by partial link
        else if(objectType.equalsIgnoreCase("PARTIALLINK")){
            
            return By.partialLinkText(p);
            
        }
        else if(objectType.equalsIgnoreCase("ID")){
        	return By.id(p);
        }
        else if(objectType.equalsIgnoreCase("VALUE"))
        {
        	return By.id(p);
        }
        else
        {
            throw new Exception("Wrong object type");
        }
    }
}
