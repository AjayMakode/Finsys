package org.ejagruti.finsys.testcases;

import org.ejagruti.finsys.config.Config;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class US_SBDC_1000_TC001 {

	@Test
	public static void ExecuteTestCase() {
		try {
			
			Config.op.LaunchApplication(Config.obj_para.GetParameterValue("_browser"),Config.obj_para.GetParameterValue("_url"),"");
			//System.out.println("Hello");
			Config.op.TextBoxSetValue(Config.obj_or.GetParameterValue("obj_username"),Config.obj_para.GetParameterValue("_username"));
			Config.op.TextBoxSetValue(Config.obj_or.GetParameterValue("obj_password"),Config.obj_para.GetParameterValue("_password"));
			Config.op.ObjectClick(Config.obj_or.GetParameterValue("obj_loginbutton"));
			//Config.op.LaunchApplication("ff", "http://localhost:90/finsys/login.html", "");
			//Config.op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
			//Config.op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rd");
			//Config.op.ObjectClick("//span[.='Login']");
			String Hometext = Config.op.driver.findElement(By.xpath(Config.obj_or.GetParameterValue("obj_hometext"))).getText();
			if (Hometext.equalsIgnoreCase("Welcome dummy[FM]")) {
				System.out.println("User is successfully login");
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
