package com.recipewebservice.models;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Entity;

import com.recipewebservice.models.RecipeImage;

@PersistenceCapable
public class Recipe {
	
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String id;
	@Persistent
	private String title;
	@Persistent
	private String directions;
	@Persistent
	private String description;
	@Persistent
	private String preptime;
	@Persistent
	private String preptimeunit;
	@Persistent
	private String cookingtime;
	@Persistent
	private String cookingtimeunit;
	@Persistent
	private String servings;
	@Persistent(mappedBy = "recipe")
	private List<RecipeImage> recipeImages;
	@Persistent(mappedBy = "recipe")
	private List<Ingredient> ingredients;

	public Recipe() {}
	
	public Recipe(Entity entity) {
		this.id              = (String) entity.getProperty("id");
		this.cookingtime     = (String) entity.getProperty("cookingtime");
		this.cookingtimeunit = (String) entity.getProperty("cookingtimeunit");
		this.description     = (String) entity.getProperty("description");
		this.directions      = (String) entity.getProperty("directions");
		this.preptime        = (String) entity.getProperty("preptime");
		this.preptimeunit    = (String) entity.getProperty("preptimeunit");
		this.servings        = (String) entity.getProperty("servings");
		this.title           = (String) entity.getProperty("title");
	}
	
	//Persistent Functions
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreptime() {
		return preptime;
	}
	public void setPreptime(String preptime) {
		this.preptime = preptime;
	}
	public String getPreptimeunit() {
		return preptimeunit;
	}
	public void setPreptimeunit(String preptimeunit) {
		this.preptimeunit = preptimeunit;
	}
	public String getCookingtime() {
		return cookingtime;
	}
	public void setCookingtime(String cookingtime) {
		this.cookingtime = cookingtime;
	}
	public String getCookingtimeunit() {
		return cookingtimeunit;
	}
	public void setCookingtimeunit(String cookingtimeunit) {
		this.cookingtimeunit = cookingtimeunit;
	}
	public String getServings() {
		return servings;
	}
	public void setServings(String servings) {
		this.servings = servings;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public void addIngredient(Ingredient ingredient){
		this.ingredients.add(ingredient);
	}
	public void removeIngredient(int index){
		this.ingredients.remove(index);
	}
	public void clearIngredients(){
		this.ingredients = new ArrayList<Ingredient>();
	}
	public List<RecipeImage> getRecipeImages() {
		return recipeImages;
	}
	public void setRecipeImages(List<RecipeImage> recipeImages) {
		this.recipeImages = recipeImages;
	}
	public void addRecipeImage(RecipeImage recipeImage){
		this.recipeImages.add(recipeImage);
	}
	public void removeRecipeImage(int index){
		this.recipeImages.remove(index);
	}
	public void clearRecipeImages(){
		this.recipeImages = new ArrayList<RecipeImage>();
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    
	    Recipe other = (Recipe) obj;
	    
	    if( id != other.getId() && 
	    	!id.equals(other.getId())){
	    	return false;
	    }
	    if( title != other.getTitle() &&
	    	!title.equals(other.getTitle())){
	    	return false;
	    }
	    if( directions != other.getDirections() &&
	    	!directions.equals(other.getDirections())){
	    	return false;
	    }
	    if( description != other.getDescription() &&
	    	!description.equals(other.getDescription())){
	    	return false;
	    }
	    if( preptime != other.getPreptime() &&
	    	!preptime.equals(other.getPreptime())){
	    	return false;
	    }
	    if( preptimeunit != other.getPreptimeunit() &&
	    	!preptimeunit.equals(other.getPreptimeunit())){
	    	return false;
	    }
	    if( cookingtime != other.getCookingtime() &&
	    	!cookingtime.equals(other.getCookingtime())){
	    	return false;
	    }
	    if( cookingtimeunit != other.getCookingtimeunit() &&
	    	!cookingtimeunit.equals(other.getCookingtimeunit())){
	    	return false;
	    }
	    if( servings != other.getServings() &&
	    	!servings.equals(other.getServings())){
	    	return false;
	    }
	    if( recipeImages != other.getRecipeImages() &&
	    	!recipeImages.equals(other.getRecipeImages())){
	    	return false;
	    }
	    if( ingredients != other.getIngredients() &&
	    	!ingredients.equals(other.getIngredients())){
	    	return false;
	    }
	    return true;
	}
	
}
