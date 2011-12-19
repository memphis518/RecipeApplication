package com.recipewebservice.contracts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import com.recipewebservice.models.Ingredient;
import com.recipewebservice.models.Recipe;

@XmlRootElement(name = "recipe")
public class RecipeContract {
	private Recipe entity = null;
	
	public RecipeContract(){
		entity = new Recipe();
	}
	
	public RecipeContract(Recipe recipe){
		this.entity = recipe;
	}
	
	@XmlElement
	public String getId(){
		return entity.getId();
	}
	
	@XmlElement
	public String getTitle(){
		return entity.getTitle();
	}
	
	@XmlElement
	public String getDirections(){
		return entity.getDirections();
	}
	
	@XmlElement
	public String getDescription(){
		return entity.getDescription();
	}

	@XmlElement
	public String getPreptime(){
		return entity.getPreptime();
	}
	
	@XmlElement
	public String getCookingTime(){
		return entity.getCookingtime();
	}
	
	@XmlElement
	public String getServings(){
		return entity.getServings();
	}
	
	@XmlElement
	public List<Ingredient> getIngredients(){
		return entity.getIngredients();
	}
	
	public Recipe getRecipe(){
		return entity;
	}
	
	public void setTitle(String title){
		entity.setTitle(title);
	}
	
	public void setDirections(String directions){
		entity.setDirections(directions);
	}
	
	public void setDescription(String description){
		entity.setDescription(description);
	}
	
	public void setPreptime(String preptime){
		entity.setPreptime(preptime);
	}
	
	public void setCooking(String cookingtime){
		entity.setPreptime(cookingtime);
	}
	
	public void setServings(String servings){
		entity.setServings(servings);
	}
	
	public void setIngredients(List<Ingredient> ingredients){
		entity.setIngredients(ingredients);
	}
}