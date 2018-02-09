package org.ejagruti.finsys.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.ejagruti.generic.TextOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Operations {

	public static WebDriver driver=null;
	public static WebDriverWait wait=null;
	private int timeout=30;
	private int counter=0;
	private boolean isLogEnabled=false;
	private boolean isResultEnabled = false;
	private String LogFolderPath;
	private String LogFilePath;
	private String ResultFolderPath;
	public Operations()
	{
		
	}
	public Operations(boolean isLogEnabled,String LogFolderPath)
	{
			this.isLogEnabled=isLogEnabled;
			this.LogFolderPath=LogFolderPath;
			this.LogFilePath=LogFolderPath+"\\LOG_"+TextOperations.getDateTime("ddMMyyyyHHmmSS")+".txt";
			if(this.isLogEnabled)
			{
				TextOperations.CreateTextFile(LogFilePath);
			}
	}
	public Operations(String ResultFolderPath,boolean isResultEnabled) {
		this.isResultEnabled = isResultEnabled;
		this.ResultFolderPath = ResultFolderPath;
	
	}
	public Operations(boolean isLogEnabled,String LogFolderPath,String ResultFolderPath,boolean isResultEnabled) {
		
		this.isLogEnabled = isLogEnabled;
		this.isResultEnabled = isResultEnabled;
		this.LogFilePath = LogFolderPath;
		this.ResultFolderPath = ResultFolderPath;
		this.LogFilePath=LogFolderPath+"\\LOG_"+TextOperations.getDateTime("ddMMyyyyHHmmSS")+".txt";
		if(this.isLogEnabled)
		{
			TextOperations.CreateTextFile(LogFilePath);
		}
		
		                 
	}
	public void BeforeRunningSuit(String suitname,String owner)
	{
		try {
			HTMLReportGenerator.TestSuiteStart(ResultFolderPath+"\\Result_"+suitname+"_"+TextOperations.getDateTime("ddMMyyyyHHmmSSS")+".html", owner);
		} catch (Exception ex) {
		
		}
	}
	public void AfterRunningSuit() 
	{
		HTMLReportGenerator.TestSuiteEnd();
	}
	public void BeforeRunningTest(String testcaseid,String description) 
	{
		HTMLReportGenerator.TestCaseStart(testcaseid, description);
	}
	public void AfterRunningTest() 
	{
		HTMLReportGenerator.TestCaseEnd();
	}
	public void LaunchApplication(String BrowserName,String URL,String WebDriverExePath)
	{
			if(BrowserName.equalsIgnoreCase("ff"))
			{
				driver=new FirefoxDriver();
			}
			if(BrowserName.equalsIgnoreCase("ch"))
			{
				System.setProperty("webdriver.chrome.driver",WebDriverExePath);
				driver=new ChromeDriver();
			}
			if(BrowserName.equalsIgnoreCase("ie"))
			{
				System.setProperty("webdriver.ie.driver",WebDriverExePath);
				driver=new InternetExplorerDriver();
			}
	    	wait=new WebDriverWait(driver,timeout);
			driver.get(URL);
			driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			try {
				String message= "Step number :"+(counter++)+"Application Launched"+TextOperations.getDateTime();
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("PASS", "ApplicationLaunch", message, "");
				}
			} catch (Exception e) {
				String message= "Step number :"+(counter++)+"Application Launched Failed"+TextOperations.getDateTime();
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("FAIL", "ApplicationLaunch", message, "");
				}
				throw new WebDriverException(message);
			}
	 }
	public WebElement IsObjectExists(String xPath)
	{
		WebElement obj=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		try {
			String message= "Step number :"+(counter++)+"ObjectExist"+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("PASS", "ObjectExist", message, "");
			}
			return obj;
		} catch (Exception e) {
			String message= "Step number :"+(counter++)+"Object is not Exist"+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("FAIL", "Object is not exist", message, "");
			}
			throw new WebDriverException(message);
		}
		
	}
	public boolean IsObjectPresent(String xPath)
	{
		WebElement obj=IsObjectExists(xPath);
		try {
			String message= "Step number :"+(counter++)+"ObjectPresent on using xpath"+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("PASS", "ObjectPresent", message, "");
			}
			return obj.isDisplayed();
		} catch (Exception e) {
			String message= "Step number :"+(counter++)+"Object is not Present"+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("FAIL", "Object is not Present", message, "");
			}
			throw new WebDriverException(message);
		}
		 
	}
	public String ObjectGetAttributeValue(String xPath,String AttributeName)
	{
		try {
		WebElement obj=IsObjectExists(xPath);
		String attributeValue= obj.getAttribute(AttributeName);
		String message="Step Number:"+(counter++)+" GetAttributeValue on Object using  xPath="+xPath;
		if(isLogEnabled) { 
			TextOperations.AppendTextFile(LogFilePath, message);
			}
		if (isResultEnabled) {
			HTMLReportGenerator.StepDetails("PASS", "Get Attribute value", message, "");
		}
		return attributeValue;
		}
		catch(Exception ex)
		{
			String message="Step Number:"+(counter++)+" NotAble to get attribute value on Object using  xPath="+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("FAIL", "NotAble to get attribute value", message, "");
			}
			throw new WebDriverException(message);
		}
	}
	public void BrowserNavigate(String Url)
	{
		driver.navigate().to(Url);
	}
	//###############Button######################
	public void ObjectClick(String xPath)
	{
			try {
				WebElement obj=IsObjectExists(xPath);
				obj.click();
				String message="Step Number:"+(counter++)+" Able to Click on Object using  xPath="+xPath;
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
			} catch (Exception e) {
				String message="Step Number:"+(counter++)+" Not able to Click on Object using  xPath="+xPath +"/n Exception details"+e.getLocalizedMessage();
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				throw new WebDriverException(message);
			}
			
	
	}
	public void ObjectDoubleClick(String xPath)
	{
	try{	
		WebElement obj=IsObjectExists(xPath);
		Actions act=new Actions(driver);
		act.doubleClick(obj).build().perform();
		String message="Step Number:"+(counter++)+" Able to DoubleClick on Object using  xPath="+xPath;
		if(isLogEnabled) { 
			TextOperations.AppendTextFile(LogFilePath, message);
			}
	} catch (Exception e) {
		String message="Step Number:"+(counter++)+" Not able to DoubleClick on Object using  xPath="+xPath +"/n Exception details"+e.getLocalizedMessage();
		
		if(isLogEnabled) { 
			TextOperations.AppendTextFile(LogFilePath, message);
			}
		throw new WebDriverException(message);
	}
	}

	//###############CheckBox######################
	public void CheckBoxCheck(String xPath) {
		
	try{
		WebElement obj=IsObjectExists(xPath);
		obj.click();
		String message="Step Number:"+(counter++)+" Able to Click on Object using  xPath="+xPath;
		if(isLogEnabled) { 
			TextOperations.AppendTextFile(LogFilePath, message);
			}
	} catch (Exception e) {
		String message="Step Number:"+(counter++)+" Not able to Click on Object using  xPath="+xPath +"/n Exception details"+e.getLocalizedMessage();
		if(isLogEnabled) { 
			TextOperations.AppendTextFile(LogFilePath, message);
			}
		throw new WebDriverException(message);
	}
	}
	public void CheckBoxUnCheck(String xPath) {
	try{
		WebElement obj=IsObjectExists(xPath);
		obj.click();
		String message="Step Number:"+(counter++)+" Able to Click on Object using  xPath="+xPath;
		if(isLogEnabled) { 
			TextOperations.AppendTextFile(LogFilePath, message);
			}
	} catch (Exception e) {
		String message="Step Number:"+(counter++)+" Not able to Click on Object using  xPath="+xPath +"/n Exception details"+e.getLocalizedMessage();
		if(isLogEnabled) { 
			TextOperations.AppendTextFile(LogFilePath, message);
			}
		throw new WebDriverException(message);
	}
	}
	//###############TextBox######################
	public void TextBoxSetValue(String xPath,String Value) throws InterruptedException
	{
		
	try{
		WebElement obj=IsObjectExists(xPath);
		obj.clear();
		Thread.sleep(1000);
	
		obj.sendKeys(Value);
		String message="Step Number:"+(counter++)+" Able to SetValue on Object using  xPath="+xPath;
		if(isLogEnabled) { 
			TextOperations.AppendTextFile(LogFilePath, message);
			}
	} catch (Exception e) {
		String message="Step Number:"+(counter++)+" Notable  to SetValue on Object using  xPath="+xPath +"/n Exception details"+e.getLocalizedMessage();
		if(isLogEnabled) {
			TextOperations.AppendTextFile(LogFilePath, message);
			}
		throw new WebDriverException(message);
	}
	}
	public void TextBoxAppendValue(String xPath,String Value)
	{
		try {
			WebElement obj=IsObjectExists(xPath);
			obj.sendKeys(Value);
			String message="Step Number:"+(counter++)+" Able to AppendValue on Object using  xPath="+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
		} catch (Exception e) {
			String message="Step Number:"+(counter++)+" Not able to AppendValue on Object using  xPath="+xPath +"/n Exception details"+e.getLocalizedMessage();
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			throw new WebDriverException(message);
		}
	}
	public void TextBoxClearValue(String xPath) {
		try {
			WebElement obj=IsObjectExists(xPath);
			obj.clear();
			String message="Step Number:"+(counter++)+" Able to ClearValue on Object using  xPath="+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
		} catch (Exception e) {
			String message="Step Number:"+(counter++)+" Not able to ClearValue on Object using  xPath="+xPath +"/n Exception Details" +e.getLocalizedMessage();
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			throw new WebDriverException(message);
		}
	}
	public void ObjectRightClick(String xPath)
	{
		try {
			WebElement obj=IsObjectExists(xPath);
			Actions action= new Actions(driver);
			action.contextClick(obj).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
			String message="Step Number:"+(counter++)+" Able to RightClick on Object using  xPath="+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
		} catch (Exception e) {
			String message="Step Number:"+(counter++)+" Not able to RightClick on Object using  xPath="+xPath +"/n Exception Details" +e.getLocalizedMessage();
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			throw new WebDriverException(message);
		}
	}
	//#################Table#####################
		public int TableGetRowCount(String xPath)
		{
			WebElement obj=IsObjectExists(xPath);
			return obj.findElements(By.tagName("tr")).size();
		}
		public int TableGetColumnCount(String xPath,int RowNumber)
		{
			WebElement obj=IsObjectExists(xPath);
			return obj.findElements(By.tagName("tr")).get(RowNumber).findElements(By.tagName("td")).size();
		}
		public String TableGetCellValue(String xPath,int RowNumber,int ColumnNumber)
		{
			WebElement obj=IsObjectExists(xPath);
			return obj.findElements(By.tagName("tr")).get(RowNumber).findElements(By.tagName("td")).get(ColumnNumber).getText();
		}
		//######################Frame#################
		public WebDriver FrameSwitchByIndex(int Index)
		{
			return   driver.switchTo().frame(Index);
		}
		public WebDriver FrameSwitchByXPath(String xPath)
		{
			 WebElement obj=  driver.findElement(By.xpath(xPath));
			 return  driver.switchTo().frame(obj);
		}
		public WebDriver FrameSwitchByName(String NameOfTheFrame)
		{
			  return  driver.switchTo().frame(NameOfTheFrame);
		}
		//######################Drop Down#################
		public void DropDownSelectByVisibleText(String xPath,String Value)
		{
		try{
			WebElement obj=IsObjectExists(xPath);
			Select sel=new Select(obj);
			sel.selectByVisibleText(Value);
			String message="Step Number:"+(counter++)+" Able to SelectByVisibleText on Object using  xPath="+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("PASS", "Visible text is selected from DropDown", message, "");
			}
		} catch (Exception e) {
			String message="Step Number:"+(counter++)+" Not able to SelectByVisibleText on Object using  xPath="+xPath+"/n Exception details"+e.getLocalizedMessage();
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("FAIL", "Not able to Selet visible text from DropDown", message, "");
			}
			throw new WebDriverException(message);
		}
		}
		public void DropDownSelectByIndex(String xPath,int Index)
		{
		try {	
			WebElement obj=IsObjectExists(xPath);
			Select sel=new Select(obj);
			sel.selectByIndex(Index);
			String message="Step Number:"+(counter++)+" Able to SelectByIndex on Object using  xPath="+xPath;
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("PASS", "Option value is selected by index from DropDown", message, "");
			}
		} catch (Exception e) {
			String message="Step Number:"+(counter++)+" Not able to SelectByIndex on Object using  xPath="+xPath+"/n Exception details"+e.getLocalizedMessage();
			if(isLogEnabled) { 
				TextOperations.AppendTextFile(LogFilePath, message);
				}
			if (isResultEnabled) {
				HTMLReportGenerator.StepDetails("FAIL", "Not able to select value by index from DropDown", message, "");
			}
			throw new WebDriverException(message);
		}
		}
		public void DropDownSelectByOptionValue(String xPath,String OptionValue)
		{
			try {
				WebElement obj=IsObjectExists(xPath);
				Select sel=new Select(obj);
				sel.selectByValue(OptionValue);
				String message="Step Number:"+(counter++)+" Able to SelectByOptionValue on Object using  xPath="+xPath;
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("PASS", "Opton value is selected from DropDown", message, "");
				}
			} catch (Exception e) {
				String message="Step Number:"+(counter++)+" Not able to SelectByOptionValue on Object using  xPath="+xPath+"/n Exception details"+e.getLocalizedMessage();
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("Fail", "Not able to select Option value from DropDown", message, "");
				}
				throw new WebDriverException(message);
			}
		}
		public String DropDownGetSelectedValue(String xPath)
		{
			try {
				WebElement obj=IsObjectExists(xPath);
				Select sel=new Select(obj);
				String message="Step Number:"+(counter++)+"Geting  selected value from DropDown on Object using Xpath" +xPath;
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("PASS", "Geting selected value from DropDown", message, "");
				}
				return sel.getFirstSelectedOption().getText();
			} catch (Exception e) {
				String message="Step Number:"+(counter++)+"Not able to get selected value from DropDown on Object using Xpath" +xPath;
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("FAIL", "Not able to get selected value from DropDown", message, "");
				}
				throw new WebDriverException(message);
			}
		}
		public ArrayList<String> DropDownGetAllSelectedValue(String xPath)
		{
			ArrayList<String> allSelectedValue;
			try {
				WebElement obj=IsObjectExists(xPath);
				Select sel=new Select(obj);
				allSelectedValue = new ArrayList<String>();
				 for(WebElement ele: sel.getAllSelectedOptions())
				 {
					 allSelectedValue.add(ele.getText());
				 }
				 String message="Step Number:"+(counter++)+"Geting all selected value from DropDown on Object using Xpath" +xPath;
					if(isLogEnabled) { 
						TextOperations.AppendTextFile(LogFilePath, message);
						}
					if (isResultEnabled) {
						HTMLReportGenerator.StepDetails("PASS", "Geting all selected value from DropDown", message, "");
					}
					return allSelectedValue;
			} catch (Exception e) {
				String message="Step Number:"+(counter++)+"Not able to get selected value from DropDown on Object using Xpath" +xPath;
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("FAIL", "Not able to get selected value from DropDown", message, "");
				}
				throw new WebDriverException(message);
			}
			 
		}
		public int DropDownGetItemsCount(String xPath) {
			List<WebElement> count;
			try {
				WebElement obj=IsObjectExists(xPath);
				Select sel=new Select(obj);
				count = sel.getOptions();
				String message="Step Number:"+(counter++)+"Geting count of DropDown on Object using Xpath" +xPath;
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("PASS", "Geting count of DropDown", message, "");
				}
				return count.size();
			} catch (Exception e) {
				String message="Step Number:"+(counter++)+"Not able to get count of DropDown on Object using Xpath" +xPath;
				if(isLogEnabled) { 
					TextOperations.AppendTextFile(LogFilePath, message);
					}
				if (isResultEnabled) {
					HTMLReportGenerator.StepDetails("FAIL", "NotAble to Get count of DropDown", message, "");
				}
				throw new WebDriverException(message);
			}
		}
	/*private void TestCase1(Operations op) throws InterruptedException
	{
		
		op.LaunchApplication("ff", "http://localhost:1979/investcorp1/AdminLogin.jsp", "");
		op.TextBoxSetValue("//input[@placeholder='Username']", "superadmin");
		//op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
		op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rd");
	 	op.ObjectClick("//input[@value='Sign In']");
	 	String val=op.ObjectGetAttributeValue("//a[.='Logout']", "innerText");
	 	if(val.equalsIgnoreCase("Logout"))
	 	{
	 		
	 		 System.out.println("Test Case 1 is passed");
	 	}
	 	else
	 	{
	 		System.out.println("Test Case 2 is Failed");
	 	}
	}
		public static void main(String[] args) throws InterruptedException {
	
		Operations op=new Operations("H:\\Result",true);
		op.BeforeRunningSuit("smoke", "manksh");//example
		op.BeforeRunningTest("tc001", "verify login func");
		op.TestCase1(op);
		op.AfterRunningTest();
		op.AfterRunningSuit();
			
	}*/

}
