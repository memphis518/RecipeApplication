Ext.define('RA.controller.Menu', {
    extend: 'Ext.app.Controller',
    views: [ 'menu.View' ],
    init: function() {
		    this.control({ 'menuview > menuitem' : {
					    	click: function(item) { 
					    		switch(item.itemId)
					    		{
						    		case 'recipebox':
						    		  this.recipeBoxClicked(item);
						    		  break;
						    		case 'recipebox-view':
						    		  this.recipeBoxViewClicked(item);
						    		  break;
						    		case 'recipebox-create':
						    		  this.recipeBoxCreateClicked(item);
							    	  break;
						    		case 'logout':
						    		  //execute logout code
						    		  break;
						    		default:
						    		  break;
					    		}
					    	}
					     }
					    })
    },
    recipeBoxClicked: function(parentMenuItem){
    	var rbView = parentMenuItem.ownerCt.getComponent('recipebox-view');
    	var rbCreate = parentMenuItem.ownerCt.getComponent('recipebox-create');
    	if(rbView.isHidden()  && rbCreate.isHidden){
    		rbView.show();
    		rbCreate.show();
    		rController = this.getController('Recipes');
    		rController.recipeList();
    	}else{
    		rbView.hide();
    		rbCreate.hide();
    	}
    },
    recipeBoxViewClicked: function(parentMenuItem){
    	rController = this.getController('Recipes');
    	rController.recipeList();
    },
    recipeBoxCreateClicked: function(parentMenuItem){
    	rController = this.getController('Recipes');
    	rController.createRecipeForm();
    }
    
});