package com.recipewebservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Path;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import com.recipewebservice.interfaces.IRecipe;
import com.recipewebservice.models.Ingredient;
import com.recipewebservice.models.Recipe;
import com.recipewebservice.utils.PMF;

@Path("/")
public class RecipeService implements IRecipe{

	private static final Logger log = Logger.getLogger(RecipeService.class.getName());
	
	public Recipe getRecipe(int id){
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 Recipe recipe = pm.getObjectById(Recipe.class, id);
		 return recipe;
	} 
	 
	public ArrayList<Recipe> getRecipeList(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query("Recipe");		
		PreparedQuery pq = datastore.prepare(q);
		
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		for (Entity result : pq.asIterable()) {
			recipes.add(new Recipe(result));
		}		
		return recipes;
	}
	
	public boolean createRecipe(Recipe recipe){
		boolean success = true;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
	        pm.makePersistent(recipe);
	    }catch(Exception e){	 
	    	log.severe("Error saving recipe with error : " + e.getLocalizedMessage());
	    	success = false;
	    }finally{
	        pm.close();
	    }
		
		return success;
	}
	
	private List<Ingredient> parseIngredientText(String ingredientText){
		List <Ingredient> ingredients = new ArrayList<Ingredient>();
		
		String[] tokens = ingredientText.split("[ ]+");
		
		return ingredients;
	}
		
}
