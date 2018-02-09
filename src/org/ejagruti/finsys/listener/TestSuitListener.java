package org.ejagruti.finsys.listener;

import org.ejagruti.finsys.config.Config;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestSuitListener implements ISuiteListener{

	static boolean execution;
	@Override
	public void onFinish(ISuite arg0) {
		Config.op.AfterRunningSuit();
		
	}

	@Override
	public void onStart(ISuite arg0) {
		Config.op.BeforeRunningSuit("Smoke", "Ajay");
	
	}

}
