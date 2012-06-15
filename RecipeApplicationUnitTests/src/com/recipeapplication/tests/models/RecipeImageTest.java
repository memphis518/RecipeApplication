package com.recipeapplication.tests.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.recipeapplication.tests.utils.FileReader;
import com.recipewebservice.models.RecipeImage;

public class RecipeImageTest {

	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig(),
	        						   new LocalBlobstoreServiceTestConfig());
	
	Text testImageString;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		FileReader fr = new FileReader();
		testImageString = new Text(fr.readImageFile("/resources/hamburger.jpeg"));
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
	
	
	
}
