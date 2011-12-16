package com.recipewebservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.recipewebservice.converter.RecipeConverter;
import com.recipewebservice.model.Ingredient;
import com.recipewebservice.model.Recipe;
import com.recipewebservice.utils.PMF;

@Path("/")
public class RecipeService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/recipe/{id}") 
	public RecipeConverter getRecipe( @PathParam ("id") int id){
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		
		 Recipe recipe = pm.getObjectById(Recipe.class, id);
		 
		 RecipeConverter converter = new RecipeConverter(recipe);
		 return converter;
	} 
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/recipe/create") 
	public boolean createRecipe(RecipeConverter recipeConverter){
		boolean success = true;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
	        pm.makePersistent(recipeConverter.getRecipe());
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
