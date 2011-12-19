package com.recipewebservice.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import com.recipewebservice.contracts.RecipeContract;
import com.recipewebservice.interfaces.IRecipe;
import com.recipewebservice.models.Ingredient;
import com.recipewebservice.models.Recipe;
import com.recipewebservice.utils.PMF;

@Path("/")
public class RecipeService implements IRecipe{

	public RecipeContract getRecipe(int id){
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		
		 Recipe recipe = pm.getObjectById(Recipe.class, id);
		 
		 RecipeContract contract = new RecipeContract(recipe);
		 return contract;
	} 
	 
	public ArrayList<RecipeContract> getRecipeList(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Recipe");
		PreparedQuery pq = datastore.prepare(q);
		ArrayList<RecipeContract> contracts = new ArrayList<RecipeContract>();
		for (Entity result : pq.asIterable()) {
			Recipe recipe = new Recipe();
			recipe.setId((String) result.getProperty("id"));
			RecipeContract contract = new RecipeContract(recipe);
			contracts.add(contract);
		}
		return contracts;
	}
	
	public boolean createRecipe(RecipeContract recipeContract){
		boolean success = true;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
	        pm.makePersistent(recipeContract.getRecipe());
	    }catch(Exception e){ 
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
