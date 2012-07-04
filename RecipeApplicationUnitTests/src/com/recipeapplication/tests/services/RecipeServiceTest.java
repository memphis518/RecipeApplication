package com.recipeapplication.tests.services;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.recipeapplication.tests.utils.FileReader;
import com.recipewebservice.models.Ingredient;
import com.recipewebservice.models.Recipe;
import com.recipewebservice.models.RecipeImage;
import com.recipewebservice.services.RecipeService;
import com.recipewebservice.utils.PMF;

public class RecipeServiceTest {

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
		List<Recipe> recipeTestList = rs.getRecipeList();
		assertEquals(1, recipeTestList.size());
		assertEquals(recipeConstant, recipeTestList.get(0));
	}

	@Test
	public void testCreateRecipe() {
		Recipe recipeConstant = new Recipe(recipeTestEntity);
		recipeConstant.setRecipeImages(images);
		recipeConstant.setIngredients(ingredients);
		RecipeService rs = new RecipeService();
		rs.createRecipe(recipeConstant);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Recipe recipeTest = pm.getObjectById(Recipe.class, recipeConstant.getId());
		
		assertEquals(recipeConstant,recipeTest);
		
	}
	
	private Recipe saveTestRecipe(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Recipe recipe = new Recipe(recipeTestEntity);
		recipe.setRecipeImages(images);
		recipe.setIngredients(ingredients);
		pm.makePersistent(recipe);
		pm.close();
		return recipe;
	}

}
