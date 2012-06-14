package com.recipeapplication.tests.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FileReader {

	public String readImageFile(String filePath) 
			throws java.io.IOException{
		
		URL url = this.getClass().getResource(filePath);
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        char[] buf = new char[1024];
        
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        
        reader.close();
        
        return fileData.toString();		
        
	}
	
}
