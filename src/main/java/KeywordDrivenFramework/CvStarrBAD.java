package KeywordDrivenFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.net.UrlChecker;

import SupportingClasses.propertiesHandle;
import SupportingClasses.ConditionsChecking;
//import SupportingClasses.UIoperartions_old;
import SupportingClasses.databaseOperartions;
import SupportingClasses.ExcelOperationsJXL;
import SupportingClasses.browserLaunching;




public class CvStarrBAD {
	//***************************global objects and variables****************************************
	
	public static  WebDriver driver=null;
	public static databaseOperartions objectInput;
	public static databaseOperartions objectOutput;
	public static String dbColumnNmae=null;
	//public static UIoperartions_old objectUIoperations=null;
	public static ExcelOperationsJXL objectTestScript=null;
	public static browserLaunching objectBrowse=null;
	public static propertiesHandle configFile;
	public static ConditionsChecking objectconditions=null;
	
	
	
	public static void main(String args[]) throws Exception
	
	
	{
		//objectBrowse=new browserLaunching();
		//objectUIoperations=new UIoperartions_old();
		objectconditions=new ConditionsChecking();
		configFile = new propertiesHandle("D:/sas/config_selenium_Starr_BAD.properties");
		databaseOperartions.conn_setup(configFile);
		System.setProperty("jsse.enableSNIExtension", "false");
		
		
		objectInput = new databaseOperartions();
		objectOutput = new databaseOperartions();
		
		
		
		objectInput.get_dataobjects(configFile.getProperty("input_query"));
		objectOutput.get_dataobjects(configFile.getProperty("output_query"));
		
		
//********************Login operation*************************************************************************************************************
		
	  
	String browser=configFile.getProperty("browser");
		String url=configFile.getProperty("url");
		//driver=objectUIoperations.launch_browser(browser,url);
		
		if(driver!=null)
		{
			driver.findElement(By.name("answer(Object::UserDetail::userName)")).sendKeys(configFile.getProperty("userName"));
			driver.findElement(By.name("answer(Object::UserDetail::passWord)")).sendKeys(configFile.getProperty("password"));
			driver.findElement(By.xpath("//input[@value='Log In']")).click(); 
		}
		else
		{
			System.out.println("driver object is null");
		}
		
		
//************************************************************************************************************************************************
		objectTestScript = new ExcelOperationsJXL(configFile.getProperty("Test_script_path")+configFile.getProperty("File_name"));
		objectTestScript.getsheets(configFile.getProperty("Sheet_name"));
		objectTestScript.set_rownumber(1);
		System.out.println(objectTestScript.get_sheetname());
		
  do
	{
	  objectTestScript.set_rownumber(1);
	  if(objectInput.read_data("flag_for_execution").equals("Y"))
		{  
		
		
		while(objectTestScript.has_next_row())
		{
			//log.info(testscript.read_data(testscript.get_rownumber(),5).toString());
			String conditions=objectTestScript.read_data(objectTestScript.get_rownumber(),7);
			// boolean blnConditionStatus=objectconditions.condition_reading(conditions, objectInput, objectOutput);
			 //System.out.println(blnConditionStatus);
			if(objectTestScript.read_data(objectTestScript.get_rownumber(),8).toString().equals("enabled")&& objectconditions.condition_reading(conditions, objectInput, objectOutput))
			{	
				   System.out.println("condition true for "+objectTestScript.read_data(objectTestScript.get_rownumber(),1));
					String pageName = objectTestScript.read_data(objectTestScript.get_rownumber(),0);
					String fieldName = objectTestScript.read_data(objectTestScript.get_rownumber(),1);
					String actionKeyword = objectTestScript.read_data(objectTestScript.get_rownumber(),2);
					String ObjectType = objectTestScript.read_data(objectTestScript.get_rownumber(),3);
					String PropertyString= objectTestScript.read_data(objectTestScript.get_rownumber(),4);
					String dbcolumnNmae = objectTestScript.read_data(objectTestScript.get_rownumber(),5);
					String value = objectTestScript.read_data(objectTestScript.get_rownumber(),6);
				    String condtions1=objectTestScript.read_data(objectTestScript.get_rownumber(),7);
					String dataProvidingFlag=objectTestScript.read_data(objectTestScript.get_rownumber(),9);
					String  waitingTime=objectTestScript.read_data(objectTestScript.get_rownumber(),10);
				
					
					//objectUIoperations.perform(PropertyString,actionKeyword,ObjectType,value,dbcolumnNmae,dataProvidingFlag,objectInput,objectOutput,driver,waitingTime);
		
			} //end of if 
			objectTestScript.next_row();
		} //end of while
		
		} //end of if
	     objectInput.write_data("Flag_for_execution", "Completed");
		 objectOutput.write_data("Flag_for_execution", "Completed");
		 objectInput.update_row();
		 objectOutput.update_row();
	 
	}while(objectInput.move_forward() && objectOutput.move_forward()); //end of do
   databaseOperartions.close_conn(); 	
		
		
	}
}
