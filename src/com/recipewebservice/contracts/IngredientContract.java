package com.recipewebservice.contracts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.recipewebservice.models.Ingredient;

@XmlRootElement(name = "recipe")
public class IngredientContract {
	private Ingredient entity = null;
	
	public IngredientContract(){
		entity = new Ingredient();
	}
	
	public IngredientContract(Ingredient ingredient){
		this.entity = ingredient;
	}
	
	@XmlElement
	public String getId(){
		return entity.getId();
	}
	
	@XmlElement
	public String getName(){
		return entity.getName();
	}
	
	@XmlElement
	public float getAmount(){
		return entity.getAmount();
	}
	
	@XmlElement
	public String getUnit(){
		return entity.getUnit();
	}
	
	@XmlElement
	public String getIngredientText(){
		return entity.getIngredientText();
	}
	
	public Ingredient getIngredient(){
		return entity;
	}
	
	public void setName(String name){
		entity.setName(name);
	}
	
	public void setAmount(float amount){
		entity.setAmount(amount);
	}
	
	public void setUnit(String unit){
		entity.setUnit(unit);
	}
	
	public void setIngredientText(String text){
		entity.setIngredientText(text);
	}
}
