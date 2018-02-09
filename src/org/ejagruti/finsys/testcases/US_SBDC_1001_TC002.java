package org.ejagruti.finsys.testcases;

import org.ejagruti.finsys.config.Config;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class US_SBDC_1001_TC002 {

	@Test
	public void ExecuteTestCase() {
		try {
			
			US_SBDC_1000_TC001.ExecuteTestCase();
			Config.op.ObjectClick(Config.obj_or.GetParameterValue("obj_finsysanalysis"));
			Config.op.ObjectClick(Config.obj_or.GetParameterValue("obj_managecompanylink"));
			Config.op.FrameSwitchByIndex(0);
			Config.op.ObjectClick(Config.obj_or.GetParameterValue("obj_newlink"));
			Config.op.TextBoxSetValue(Config.obj_or.GetParameterValue("obj_companynametextfield"),Config.obj_para.GetParameterValue("_companyname"));
			Config.op.DropDownSelectByOptionValue(Config.obj_or.GetParameterValue("obj_companytype"),Config.obj_para.GetParameterValue("_companytype"));
			Config.op.TextBoxSetValue(Config.obj_or.GetParameterValue("obj_addressfield"),Config.obj_para.GetParameterValue("_address"));
			Config.op.TextBoxSetValue(Config.obj_or.GetParameterValue("obj_emailtextfield"),Config.obj_para.GetParameterValue("_emailid"));
			Config.op.TextBoxSetValue(Config.obj_or.GetParameterValue("obj_pandetailstextfield"),Config.obj_para.GetParameterValue("_pannumber"));
			Config.op.TextBoxSetValue(Config.obj_or.GetParameterValue("obj_tindetailstextfield"),Config.obj_para.GetParameterValue("_tinnumber"));
			Config.op.DropDownSelectByVisibleText(Config.obj_or.GetParameterValue("obj_country"),Config.obj_para.GetParameterValue("_country"));
			Config.op.DropDownSelectByOptionValue(Config.obj_or.GetParameterValue("obj_state"),Config.obj_para.GetParameterValue("_state"));
			Config.op.DropDownSelectByOptionValue(Config.obj_or.GetParameterValue("obj_cityname"),Config.obj_para.GetParameterValue("_city"));
			Config.op.TextBoxSetValue(Config.obj_or.GetParameterValue("obj_totalempolyee"),Config.obj_para.GetParameterValue("_totalemployee"));
			Config.op.ObjectClick(Config.obj_or.GetParameterValue("obj_savebutton"));
			//Config.op.ObjectClick("//div[.='Financial Analysis']");
			//Config.op.ObjectClick("//div[@id='_easyui_tree_2']//a[.='Manage Company']");
			//Config.op.ObjectClick("//div[@id='toolbar']//span[.='New']");
			//Config.op.TextBoxSetValue("//input[@name='name']", "Cybage1");
			//Config.op.ObjectClick("//select[@id='companytype']");
			//Config.op.DropDownSelectByOptionValue("//select[@id='companytype']", "IT");
			/*Config.op.TextBoxSetValue("//span/input//preceding-sibling::textarea","cybage pvt. ltd. kalyani nagar");
			Config.op.TextBoxSetValue("//input[@name='email']","abc@cybage.com");
			Config.op.TextBoxSetValue("//input[@name='pan']", "1234567890");
			Config.op.TextBoxSetValue("//input[@name='tin']", "1111111111");*/
			//Config.op.ObjectClick("//select[@id='countryid']");
			//Config.op.DropDownSelectByIndex("//select[@id='countryid']", 1);
			//Config.op.ObjectClick("//select[@id='stateidlist']");
			//Config.op.DropDownSelectByOptionValue("//select[@id='stateidlist']", "MAHARASHTRA");
			//Config.op.ObjectClick("//select[@id='citylist']");
			//Config.op.DropDownSelectByOptionValue("//select[@id='citylist']", "PUNE");
			//Config.op.TextBoxSetValue("//input[@name='totalemployee']/preceding-sibling::input","1500");////span//input[@name='totalemployee']/preceding-sibling::input
			//Config.op.ObjectClick("//span[.='Save']");

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
