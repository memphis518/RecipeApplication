package com.recipewebservice.models;

import com.google.appengine.api.datastore.Entity;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Ingredient {

	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String id;
	@Persistent
	private String name;
	@Persistent
	private double amount;
	@Persistent
	private String unit;
	@Persistent
	private Recipe recipe;
	
	public Ingredient() {}
	
	public Ingredient(Entity entity){
		this.id     = (String) entity.getProperty("id");
		this.name   = (String) entity.getProperty("name");
		this.amount = (Double) entity.getProperty("amount");
		this.unit   = (String) entity.getProperty("unit");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    
	    Ingredient other = (Ingredient) obj;
	    
	    if( id != other.getId() && 
	    	!id.equals(other.getId())){
	    	return false;
	    }
	    if( name != other.getName() &&
	    	!name.equals(other.getName())){
	    	return false;
	    }
	    if( amount != other.getAmount() ){
		    	return false;
		}
	    if( unit != other.getUnit() &&
		    	!unit.equals(other.getUnit())){
		    	return false;
		}
	    
	    return true;
	}
	
}
