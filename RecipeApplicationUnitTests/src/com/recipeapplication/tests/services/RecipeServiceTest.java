package com.recipeapplication.tests.services;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.recipewebservice.models.Recipe;
import com.recipewebservice.services.RecipeService;
import com.recipewebservice.utils.PMF;

public class RecipeServiceTest {

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
	public void testGetRecipe() {
		Recipe recipeConstant = saveTestRecipe();
		RecipeService rs = new RecipeService();
		Recipe recipeTest = rs.getRecipe(recipeConstant.getId());
		assertEquals(recipeConstant,recipeTest);
	}

	@Test
	public void testGetRecipeList() {
		Recipe recipeConstant = saveTestRecipe();
		RecipeService rs = new RecipeService();
		ArrayList<Recipe> recipeTestList = rs.getRecipeList();
		assertEquals(1, recipeTestList.size());
		assertEquals(recipeConstant, recipeTestList.get(0));
	}

	@Test
	public void testCreateRecipe() {
		Recipe recipeConstant = new Recipe(recipeTestEntity);
		RecipeService rs = new RecipeService();
		rs.createRecipe(recipeConstant);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Recipe recipeTest = pm.getObjectById(Recipe.class, recipeConstant.getId());
		
		assertEquals(recipeConstant,recipeTest);
		
	}
	
	private Recipe saveTestRecipe(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Recipe recipe = new Recipe(recipeTestEntity);
		pm.makePersistent(recipe);
		pm.close();
		return recipe;
	}

}
