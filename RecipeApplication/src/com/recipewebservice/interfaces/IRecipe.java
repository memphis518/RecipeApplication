package com.recipewebservice.interfaces;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.recipewebservice.models.Recipe;

@Path("/")
public interface IRecipe {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/recipe/{id}") 
	public Recipe getRecipe( @PathParam ("id") int id);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/recipe/list") 
	public ArrayList<Recipe> getRecipeList();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/recipe/create") 
	public boolean createRecipe(Recipe recipe);
	
}
