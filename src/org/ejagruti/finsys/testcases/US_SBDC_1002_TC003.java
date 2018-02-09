package org.ejagruti.finsys.testcases;

import org.ejagruti.finsys.config.Config;

public class US_SBDC_1002_TC003 {

	public void ExecuteTestCase() {
		try {
			US_SBDC_1000_TC001.ExecuteTestCase();
			Config.op.ObjectClick(Config.obj_or.GetParameterValue("obj_plussign"));
			
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
