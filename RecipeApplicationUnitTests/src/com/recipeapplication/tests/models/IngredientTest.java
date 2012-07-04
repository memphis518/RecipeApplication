package com.recipeapplication.tests.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.recipewebservice.models.Ingredient;

public class IngredientTest {

	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	private Entity ingredientEntity;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		ingredientEntity = new Entity("Ingredient");
		ingredientEntity.setProperty("name", "Pork Chops");
		ingredientEntity.setProperty("amount", 1.5);
		ingredientEntity.setProperty("unit", "lbs");
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testGets() {
		Ingredient ingredient = new Ingredient(ingredientEntity);
		assertEquals(ingredient.getName(), ingredientEntity.getProperty("name"));
		assertEquals((Double) ingredient.getAmount(), (Double) ingredientEntity.getProperty("amount"));
		assertEquals(ingredient.getUnit(), ingredientEntity.getProperty("unit"));
		
	}
	
	@Test
	public void testSets(){
		Ingredient ingredient = new Ingredient();
		ingredient.setName((String) ingredientEntity.getProperty("name"));
		ingredient.setAmount((Double) ingredientEntity.getProperty("amount"));
		ingredient.setUnit((String) ingredientEntity.getProperty("unit"));
	
		Ingredient ingredientControl = new Ingredient(ingredientEntity);
		
		assertEquals(ingredientControl, ingredient);
	}
	
}
