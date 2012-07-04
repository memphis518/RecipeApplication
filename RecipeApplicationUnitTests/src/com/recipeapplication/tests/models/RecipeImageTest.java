package com.recipeapplication.tests.models;

import static org.junit.Assert.*;

import java.lang.reflect.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.blobstore.BlobKey;
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
	
	@Test
	public void testImageBlobRetrieve(){
		BlobKey blobKey = null;
		try {
			Class recipeImageReflect = Class.forName("com.recipewebservice.models.RecipeImage");
			Object receiptImageReflectInstance = recipeImageReflect.newInstance();
			Class params[] = new Class[1];
			params[0] = Text.class;
			Method saveImageMethod = recipeImageReflect.getDeclaredMethod("saveImageToBlobStore", params);
			saveImageMethod.setAccessible(true);
			Text args[] = new Text[1];
			args[0] = testImageString;
			blobKey = (BlobKey) saveImageMethod.invoke(receiptImageReflectInstance, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(blobKey);
		RecipeImage recipeImage = new RecipeImage();
		recipeImage.setBlobKey(blobKey);
		Text retrievedImageString = recipeImage.getImage();
		assertEquals(testImageString.getValue(),retrievedImageString.getValue());
	}
	
}
