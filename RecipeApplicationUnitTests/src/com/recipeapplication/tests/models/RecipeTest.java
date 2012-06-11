package com.recipeapplication.tests.models;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.recipewebservice.models.Recipe;
import com.recipewebservice.utils.PMF;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecipeTest {

	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private Entity recipeTestEntity;
	
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
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testGets() {
		Recipe recipe = new Recipe(recipeTestEntity);
		assertEquals(recipe.getTitle(), recipeTestEntity.getProperty("title"));
		assertEquals(recipe.getDirections(), recipeTestEntity.getProperty("directions"));
		assertEquals(recipe.getPreptime(), recipeTestEntity.getProperty("preptime"));
		assertEquals(recipe.getPreptimeunit(), recipeTestEntity.getProperty("preptimeunit"));
		assertEquals(recipe.getCookingtime(), recipeTestEntity.getProperty("cookingtime"));
		assertEquals(recipe.getCookingtimeunit(), recipeTestEntity.getProperty("cookingtimeunit"));
		assertEquals(recipe.getServings(), recipeTestEntity.getProperty("servings"));
	}

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
	
	@Test
    public void testSave1() {
        doSave();
    }

    @Test
    public void testSave2() {
        doSave();
    }
	
}
