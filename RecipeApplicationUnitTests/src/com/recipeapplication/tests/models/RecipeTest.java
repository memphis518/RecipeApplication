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
import com.recipewebservice.models.Ingredient;
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
	private ArrayList<Ingredient> ingredients;
	
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
				
		images = new ArrayList<RecipeImage>();
		
		FileReader fr = new FileReader();
		Text testImageString = new Text(fr.readImageFile("/resources/hamburger.jpeg"));
		RecipeImage recipeImage1 = new RecipeImage();
		recipeImage1.setImage(testImageString);
		images.add(recipeImage1);
		
		FileReader fr2 = new FileReader();
		Text testImageString2 = new Text(fr2.readImageFile("/resources/steak.jpeg"));
		RecipeImage recipeImage2 = new RecipeImage();
		recipeImage2.setImage(testImageString2);
		images.add(recipeImage2);
				
		ingredients = new ArrayList<Ingredient>();
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("Pork Chops");
		ingredient.setAmount(1.5);
		ingredient.setUnit("lbs");
		ingredients.add(ingredient);
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setName("Rosemary");
		ingredient2.setAmount(1);
		ingredient2.setUnit("twig");
		ingredients.add(ingredient2);
		
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

	@Test
	public void testSets(){
		Recipe recipe = new Recipe();
		recipe.setTitle((String) recipeTestEntity.getProperty("title"));
		recipe.setDirections((String) recipeTestEntity.getProperty("directions"));
		recipe.setPreptime((String) recipeTestEntity.getProperty("preptime"));
		recipe.setPreptimeunit((String) recipeTestEntity.getProperty("preptimeunit"));
		recipe.setCookingtime((String) recipeTestEntity.getProperty("cookingtime"));
		recipe.setCookingtimeunit((String) recipeTestEntity.getProperty("cookingtimeunit"));
		recipe.setServings((String) recipeTestEntity.getProperty("servings"));
		Recipe recipeControl = new Recipe(recipeTestEntity);
		
		assertEquals(recipeControl, recipe);
		
	}
	
	@Test
	public void testImages(){
		Recipe recipe = new Recipe();
		recipe.setRecipeImages(images);
		assertEquals(images, recipe.getRecipeImages());
		
		recipe.clearRecipeImages();
		assertEquals(0, recipe.getRecipeImages().size());
		
		recipe.addRecipeImage(images.get(0));
		recipe.addRecipeImage(images.get(1));
		assertEquals(images, recipe.getRecipeImages());
		
		recipe.removeRecipeImage(1);
		assertEquals(1, recipe.getRecipeImages().size());
		assertEquals(images.get(0), recipe.getRecipeImages().get(0));	
	}
	
	@Test
	public void testIngredients(){
		Recipe recipe = new Recipe();
		recipe.setIngredients(ingredients);
		assertEquals(ingredients, recipe.getIngredients());
		
		recipe.clearIngredients();
		assertEquals(0, recipe.getIngredients().size());
		
		recipe.addIngredient(ingredients.get(0));
		recipe.addIngredient(ingredients.get(1));
		assertEquals(ingredients, recipe.getIngredients());
		
		recipe.removeIngredient(1);
		assertEquals(1, recipe.getIngredients().size());
		assertEquals(ingredients.get(0), recipe.getIngredients().get(0));	
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
		
		Query q2 = new Query("RecipeImage");		
		pq = datastore.prepare(q2);
		assertEquals(0, pq.countEntities(withLimit(10)));
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Recipe recipe = new Recipe(recipeTestEntity);
		recipe.setRecipeImages(images);
		recipe.setIngredients(ingredients);
		pm.makePersistent(recipe);
		pm.close();
		
		PreparedQuery pq2 = datastore.prepare(q);
		assertEquals(1, pq2.countEntities(withLimit(10)));
				
		pq2 = datastore.prepare(q2);
		assertEquals(2, pq.countEntities(withLimit(10)));
		
	}
	
}
