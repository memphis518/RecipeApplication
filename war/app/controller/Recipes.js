Ext.define('RA.controller.Recipes', {
    extend: 'Ext.app.Controller',
    views: [ 'recipe.Form', 'recipe.Panel', 'recipe.List', 'recipe.View' ],
    stores: ['Recipes', 'TimeUnits'],
    models: ['Recipe', 'TimeUnit'],
    refs: [
           {
               ref: 'recipePanel',
               selector: 'recipepanel'
           }
       ],
    init: function() {
    	Ext.History.add("Recipes:recipeList");
    	this.control({ 'recipeslist > dataview' : {
    						itemclick: function(dv, record, item, index, e, opts) { 
    								this.viewRecipe(index);
    						}
	    				},
	    				'recipeslist > panel > button' : {
	    					click: function(){
	    						this.createRecipeForm();
	    					}
	    				},
	    				'recipeform button[text=Cancel]' : {
	    					click: function(){
	    						this.recipeList();
	    					}
	    				}
    	})
    },
    createRecipeForm: function(){
    	var panel = this.getRecipePanel();
        panel.getLayout().setActiveItem(2);
    	Ext.History.add("Recipes:createRecipeForm");
    },
    recipeList: function(){
        var panel = this.getRecipePanel();
        panel.getLayout().setActiveItem(0);
    	Ext.History.add("Recipes:recipeList");
    },
    viewRecipe: function(index){ 	
    	var panel = this.getRecipePanel();
    	panel.getLayout().setActiveItem(1);
    	store = this.getRecipesStore();
    	record = store.getAt(index);
    	panel.getLayout().getActiveItem().update(record.data);
    	Ext.History.add("Recipes:viewRecipe:" + index);
    }
});