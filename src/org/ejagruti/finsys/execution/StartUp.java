package org.ejagruti.finsys.execution;

import org.ejagruti.finsys.testcases.US_SBDC_1000_TC001;

//import org.ejagruti.finsys.testcases.US_SBDC_1001_TC002;

public class StartUp {

	public static void main(String[] arg) {
		US_SBDC_1000_TC001 tc1 = new US_SBDC_1000_TC001();
		tc1.ExecuteTestCase();
	}
}
