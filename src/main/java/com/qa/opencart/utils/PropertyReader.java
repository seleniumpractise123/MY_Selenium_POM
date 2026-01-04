package com.qa.opencart.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	//private static final String FILE_PATH = "./src/main/resource/OutPutDataCapture/OutPut.txt"; 
	
	/*
	public static void write(String data) 
	{ 
		System.out.println("Driver calling this method");
		try(FileWriter writer = new FileWriter(FILE_PATH, true)){
			writer.write(data + System.lineSeparator());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	private static final String FILE_PATH = "output.properties"; 
	private static Properties props = new Properties();
	
	public static void write(String key, String value) {
		try(FileOutputStream fos = new FileOutputStream(FILE_PATH,true)) {
			props.setProperty(key, value);
			props.store(fos, "Automation Output");
			
		}catch(IOException e) {
			  e.printStackTrace();
		}
	} 

		    

}
