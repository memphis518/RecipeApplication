package com.recipeapplication.tests.models;

import java.util.ArrayList;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Text;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.recipeapplication.tests.utils.FileReader;
import com.recipewebservice.models.Recipe;
import com.recipewebservice.models.RecipeImage;
import com.recipewebservice.utils.PMF;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecipeTest {

	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig(),
	        						   new LocalBlobstoreServiceTestConfig());
	private Entity recipeTestEntity;
	private ArrayList<RecipeImage> images;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		recipeTestEntity = new Entity("Recipe");
		recipeTestEntity.setProperty("title", "Test Recipe");
		recipeTestEntity.setProperty("directions", "These are the directions");
		recipeTestEntity.setProperty("preptime", "45");
		recipeTestEntity.setProperty("preptimeunit", "minutes");
		recipeTestEntity.setProperty("cookingtime", "1");
		recipeTestEntity.setProperty("cookingtimeunit", "hour");
		recipeTestEntity.setProperty("servings", "4");
		
		FileReader fr = new FileReader();
		Text testImageString = new Text(fr.readImageFile("/resources/hamburger.jpeg"));
		images = new ArrayList<RecipeImage>();
		
		RecipeImage recipeImage1 = new RecipeImage();
		recipeImage1.setImage(testImageString);
		images.add(recipeImage1);
		
		RecipeImage recipeImage2 = new RecipeImage();
		recipeImage2.setImage(testImageString);
		images.add(recipeImage2);
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testGets() {
		Recipe recipe = new Recipe(recipeTestEntity);
		recipe.setRecipeImages(images);
		
		assertEquals(recipe.getTitle(), recipeTestEntity.getProperty("title"));
		assertEquals(recipe.getDirections(), recipeTestEntity.getProperty("directions"));
		assertEquals(recipe.getPreptime(), recipeTestEntity.getProperty("preptime"));
		assertEquals(recipe.getPreptimeunit(), recipeTestEntity.getProperty("preptimeunit"));
		assertEquals(recipe.getCookingtime(), recipeTestEntity.getProperty("cookingtime"));
		assertEquals(recipe.getCookingtimeunit(), recipeTestEntity.getProperty("cookingtimeunit"));
		assertEquals(recipe.getServings(), recipeTestEntity.getProperty("servings"));
		assertEquals(recipe.getRecipeImages(), images);
	}

	@Test
	public void testSets(){
		Recipe recipe = new Recipe();
		recipe.setTitle((String) recipeTestEntity.getProperty("title"));
		recipe.setDirections((String) recipeTestEntity.getProperty("title"));
		recipe.setPreptime((String) recipeTestEntity.getProperty("title"));
		recipe.setPreptimeunit((String) recipeTestEntity.getProperty("title"));
		recipe.setCookingtime((String) recipeTestEntity.getProperty("title"));
		recipe.setCookingtimeunit((String) recipeTestEntity.getProperty("title"));
		recipe.setServings((String) recipeTestEntity.getProperty("title"));
		
		Recipe recipeControl = new Recipe(recipeTestEntity);
		
		assertEquals(recipeControl, recipe);
		
	}
	
	@Test
    public void testSave1() {
        doSave();
    }

    @Test
    public void testSave2() {
        doSave();
    }
	
	private void doSave(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Recipe");		
		PreparedQuery pq = datastore.prepare(q);
		assertEquals(0, pq.countEntities(withLimit(10)));
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Recipe recipe = new Recipe(recipeTestEntity);
		pm.makePersistent(recipe);
		pm.close();
		
		PreparedQuery pq2 = datastore.prepare(q);
		assertEquals(1, pq2.countEntities(withLimit(10)));
		
	}
	
}
