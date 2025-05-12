package com.hexaware.util;

import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static Properties loadProperties(String filename) {
        Properties props = new Properties();
        try {
            ClassLoader loader = DBPropertyUtil.class.getClassLoader();
            props.load(loader.getResourceAsStream(filename));
        } catch (IOException | NullPointerException e) {
            System.out.println("❌ Failed to load properties file: " + filename);
            e.printStackTrace();
        }
        return props;
    }
}





	

		
	  
		 
		
		

