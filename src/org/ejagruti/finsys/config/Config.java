package org.ejagruti.finsys.config;

import org.ejagruti.finsys.operation.Operations;
import org.ejagruti.finsys.operation.PropertiesFileReader;

public class Config {

	//public static Operations op = new Operations();
	//public static Operations op = new Operations("H:\\Result",true);
	public static Operations op= new Operations(true,"H:\\LOG","H:\\Result",true);
	public static PropertiesFileReader obj_or = new PropertiesFileReader("or");
	public static PropertiesFileReader obj_para = new PropertiesFileReader("parameter");
}
