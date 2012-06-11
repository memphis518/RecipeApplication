package com.recipeapplication.tests.models;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.recipewebservice.models.RecipeImage;

public class RecipeImageTest {

	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig(),
	        						   new LocalBlobstoreServiceTestConfig());
	
	String testImageString;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();

		testImageString = readImageFile("/resources/hamburger.jpeg");
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testRecipeImage(){
		RecipeImage recipeImage = new RecipeImage();
		recipeImage.setImage(testImageString);
		
		assertEquals(recipeImage.getImage(), testImageString);
		
	}
	
	private String readImageFile(String filePath) 
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
