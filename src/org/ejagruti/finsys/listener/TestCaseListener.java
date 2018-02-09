package org.ejagruti.finsys.listener;

import java.io.IOException;

import org.ejagruti.finsys.config.Config;
import org.ejagruti.finsys.operation.HTMLReportGenerator;
import org.ejagruti.finsys.operation.TakeScreenShot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class TestCaseListener implements ITestListener{

	static boolean execution;
	@Override
	public void onFinish(ITestContext arg0) {
		Config.op.AfterRunningTest();
	}

	@Override
	public void onStart(ITestContext arg0) {
		Config.op.BeforeRunningTest(arg0.getName(), arg0.getCurrentXmlTest().getParameter("note"));	
		//execution = arg0.getCurrentXmlTest().getParameter("execute").equalsIgnoreCase("y");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
	
		HTMLReportGenerator.StepDetails("FAIL", arg0.getName(),arg0.getAttributeNames().toString(), "H:\\MavenProject\\Finsys\\Image\\Fail.png");
		try {
			TakeScreenShot.TakeScreenShot("H:\\MavenProject\\Finsys\\Image\\Fail.png",Config.op.driver);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		HTMLReportGenerator.StepDetails("SKIP", arg0.getName(),arg0.getAttributeNames().toString(), "H:\\MavenProject\\Finsys\\Image\\Skip.png");
		try {
			TakeScreenShot.TakeScreenShot("H:\\MavenProject\\Finsys\\Image\\Skip.png",Config.op.driver);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		if(execution==true) {
			System.out.println(execution);
			throw new SkipException("Skip Test Steps");	
		}
		Object[] parmname = arg0.getParameters();
		for (int i = 0; i < parmname.length; i++) {
			System.out.println(parmname[i]);	
			HTMLReportGenerator.StepDetails("PASS", arg0.getName(),parmname[i].toString(), "H:\\MavenProject\\Finsys\\Images\\TestStart.png");
		}
		try {
			TakeScreenShot.TakeScreenShot("H:\\MavenProject\\Finsys\\Image\\Success.png",Config.op.driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
