package com.recipewebservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.jdo.Query;
import javax.jdo.PersistenceManager;

import com.recipewebservice.interfaces.IRecipe;
import com.recipewebservice.models.Recipe;
import com.recipewebservice.utils.PMF;

@Path("/")
public class RecipeService implements IRecipe{

	private static final Logger log = Logger.getLogger(RecipeService.class.getName());
	
	public Recipe getRecipe(String id){
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 Recipe recipe = pm.getObjectById(Recipe.class, id);
		 return recipe;
	} 
	 
	public List<Recipe> getRecipeList(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(Recipe.class);		
		List<Recipe> recipes = (List<Recipe>) q.execute();	
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
	
}
