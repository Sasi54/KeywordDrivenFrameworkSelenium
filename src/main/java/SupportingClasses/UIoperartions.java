package SupportingClasses;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import KeywordDrivenFramework.StarrassistBenefitsCheck;


public class UIoperartions {
	public static WebDriver driver;
	
	 public static WebDriverWait wait;
	
//**************************************UI operations***************************************************************************
public void perform(String p,String operation,String objectType,String value,String dbcolumn_name,String dataFlag,databaseOperartions input,databaseOperartions output,WebDriver driver,String waitingTime) throws Exception
{
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
        	
        }
        catch(StaleElementReferenceException e)
        {
        	this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
        }
        break;
 //---------------------------------------SET TEXT-----------------------------------------------------------------------       
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
        		//System.out.println(input.read_data(dbcolumn_name));
        		driver.findElement(this.getObject(p,objectType)).clear();
        		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        		driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
        		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
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
        	
      }
   catch(StaleElementReferenceException e)
    {
	  this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
    }
    break;
 
 //------------------------------------------------GO TO URL--------------------------------------------------------------------------    
 case "GOTOURL":
	 
	 try
	 {
		 wait = new WebDriverWait(driver, waitingTimeinseconds);
         wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
         wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
         driver.get(value);
        
	 }
	 catch(StaleElementReferenceException e)
	 {
		 this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	 }
	 break;
 //-------------------------------------------------------GET ATTRIBUTE-------------------------------------------------------------
	 
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
               
        }
       catch(StaleElementReferenceException e)
        {
    	   this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
        }
   
   	 break;
 //------------------------------------------------------GET TEXT----------------------------------------------------------------------       
            
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
          
	 }
	 catch(StaleElementReferenceException e)
	 {
		 this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
		 
	 }
	
		 break;
	
	 
  //----------------------------------------------------SELECT OPERATION-------------------------------------------------------------------------
	 
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
        		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        		break;
        		
        case "Default":
        		wait = new WebDriverWait(driver, waitingTimeinseconds);
        	    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
                wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
         		Select dropdown1 = new Select(driver.findElement(this.getObject(p,objectType)));
         	    dropdown1.selectByVisibleText(value);
         	    driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
         		break;
        	}
        	
	 }
	 catch(StaleElementReferenceException e)
	 {
		 this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);    
	 }
    break;
//--------------------------------------------------MOUSE HOVER----------------------------------------------------------------------------------
	 
 case "MOUSEHOVER":
	 
	 try
	 {
		    wait = new WebDriverWait(driver, waitingTimeinseconds);
		    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
     	    wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	Actions mouse_hover = new Actions(driver);
			mouse_hover.moveToElement(driver.findElement(this.getObject(p,objectType))).build().perform();
				
	 }
	 catch(StaleElementReferenceException e)
	 {
		 this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
				 
	 }
	 break;

//-----------------------------------------------AUTO COMPLETE--------------------------------------------------------------------------------------	 
	 
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
        	wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
            wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	driver.findElement(this.getObject(p,objectType)).sendKeys(value);
        	driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        	break;
        }  
	}
	catch(StaleElementReferenceException e)
	{
		this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	       
	}
	 break;
   
	
//-----------------------------------------------------CLICK RADIO BUTTON BY CONCATINATING ITS VALUE ATTRIBUTE---------------------------------------------------------------------------        	
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
      
	}
	catch(StaleElementReferenceException e)
	{
		this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	}
   break;
//------------------------------------------------------ASSERTION-------------------------------------------------------------------	
        	
case "ASSERTTEXT":
	    try
	      {
		        wait = new WebDriverWait(driver, waitingTimeinseconds);	
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
    	        wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
         	    wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
         	    driver.getTitle();	
    	   	   
	       } 	   		
	catch(StaleElementReferenceException e)
	   {
		this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	   }
      break; 	   
//------------------------------------------------SCREENSHOT------------------------------------------------------------------   	
	
case "SCREENSHOT":
    	   
    	        System.out.println("Taking Screenshot"); 
    	   		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           
    	   		String Test_data_name=StarrassistBenefitsCheck.objectInput.read_data("test_data_name");
    	   		FileUtils.copyFile(scrFile, new File("D:\\sas\\sas1\\"+Test_data_name+".png"));
    	   		wait = new WebDriverWait(driver, waitingTimeinseconds);	
    	   		break;
    	   	
 //-----------------------------------------CLICK RADIO BUTTON BY ITS VALUE-------------------------------------------------------------------------------
    	   		
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
		   List<WebElement> RadButtonList1 =driver.findElements(this.getObject(p,objectType));
			for(int i=0; i< RadButtonList1.size() ; i++)
			{
				if(((WebElement) RadButtonList1.get(i)).getAttribute("value").equals(value))
				{
	   				System.out.println("radio button clicked");
	   				wait.until(ExpectedConditions.visibilityOf(RadButtonList1.get(i)));
	   			    //wait.until(ExpectedConditions.elementToBeClickable(RadButtonList.get(i)));
	   			   // wait.until(ExpectedConditions.elementToBeSelected(RadButtonList.get(i)));
	   				((WebElement) RadButtonList1.get(i)).click();	
	   			}
			}
			break;
	}
	
}
catch(StaleElementReferenceException e)
{
	this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
}
	break;
	


//------------------------------------------DATE PICKER-----------------------------------------------------------------------------------------
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
     		//System.out.println(input.read_data(dbcolumn_name));
     		driver.findElement(this.getObject(p,objectType)).sendKeys(input.read_data(dbcolumn_name));
     		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
     		break;
            
     	case "Default":
     		
     		wait = new WebDriverWait(driver, 30);
     	    wait = new WebDriverWait(driver, waitingTimeinseconds);
  		    wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
  		    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
      	    wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
     		//driver.findElement(this.getObject(p,objectType)).click();
      	   	driver.findElement(this.getObject(p,objectType)).sendKeys(value);
      	  //driver.findElement(this.getObject(p,objectType)).click();
             break;
     	}
     	
   }
catch(StaleElementReferenceException e)
 {
	this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
 }
 break;
//-----------------------------------------------------TOGGLE-----------------------------------------------------------------------

case "TOGGLE":
	
	try
	{
	   wait = new WebDriverWait(driver, 30);	
	   wait = new WebDriverWait(driver, waitingTimeinseconds);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	   driver.findElement(this.getObject(p,objectType)).click();
	   driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
	}
	catch(StaleElementReferenceException e)
	{
		this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	}
   break;
//-------------------------------------------------------------WAIT FOR LOAD-----------------------------------------------------------------
case "WAITLOAD":
	
	wait = new WebDriverWait(driver, waitingTimeinseconds); 
	wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	WebElement element = driver.findElement(this.getObject(p,objectType));
	element.isDisplayed();
    // element isSucceededed = element != null;
	break;
	
//-------------------------------------------------------CONTINUES OPERATION------------------------------------------------------------------------------	
case "CONTOPERATION":
	
	 wait = new WebDriverWait(driver, waitingTimeinseconds); 
	 wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	 WebElement element1= driver.findElement(this.getObject(p,objectType));
     Actions builder = new Actions(driver);
     Actions seriesOfActions = builder.moveToElement(element1).click().sendKeys(element1, input.read_data(dbcolumn_name));
     seriesOfActions.perform();
     Thread.sleep(2000);
     break;
     
//--------------------------------------------------------WAITING FOR IMG INVISIBILITY----------------------------------------------------------------------------     
case "IMGIDVISIBLE":
	
	try
	{
	wait = new WebDriverWait(driver, waitingTimeinseconds);
	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getObject(p,objectType)));
	break;
	}
	catch(Exception e)
	{
		System.out.println("waited for invisibility");
		break;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
default:
	    System.out.println("operations not  performed");
	    break;
}
        
}
    
 //============================================Locator Action====================================================================  
    private By getObject(String p,String objectType) throws Exception{
        //Find by xpath
        if(objectType.equalsIgnoreCase("XPATH"))
        {
            
            return By.xpath(p);
        }
        //find by class
        else if(objectType.equalsIgnoreCase("CLASSNAME"))
        {
            
            return By.className(p);
            
        }
        //find by name
        else if(objectType.equalsIgnoreCase("NAME"))
        {
            
            return By.name(p);
            
        }
        //Find by css
        else if(objectType.equalsIgnoreCase("CSS"))
        {
            
            return By.cssSelector(p);
            
        }
        //find by link
        else if(objectType.equalsIgnoreCase("LINK"))
        {
            
            return By.linkText(p);
            
        }
        //find by partial link
        else if(objectType.equalsIgnoreCase("PARTIALLINK"))
        {
            
            return By.partialLinkText(p);
            
        }
        else if(objectType.equalsIgnoreCase("ID"))
        {
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
