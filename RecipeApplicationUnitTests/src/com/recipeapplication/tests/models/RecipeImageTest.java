package com.recipeapplication.tests.models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
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
			blobKey = saveImageToBlobStore(testImageString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(blobKey);
		RecipeImage recipeImage = new RecipeImage();
		recipeImage.setBlobKey(blobKey);
		Text retrievedImageString = recipeImage.getImage();
		assertEquals(testImageString.getValue(),retrievedImageString.getValue());
	}
	
	private BlobKey saveImageToBlobStore(Text image) throws IOException{
		FileWriteChannel writeChannel = null;
		BlobKey blobKey = null;
		try{
			FileService fileService = FileServiceFactory.getFileService();
			AppEngineFile file = fileService.createNewBlobFile("application/octet-stream");
			writeChannel = fileService.openWriteChannel(file, true);
			PrintWriter out = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));
			out.println(image.getValue());
			out.close();
			writeChannel.closeFinally();
			blobKey = fileService.getBlobKey(file);
		} catch (Exception e){
			System.out.println("Error saving image: " + e.getLocalizedMessage());
		} 
		return blobKey;
	}
	
}
