Ext.define('RA.view.recipe.Form' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.recipeform',
    border: false,
    items: [
			{
				xtype: 'header',
				html:  '<h1> Create Recipe </h1>',
				width: 600,
				height: 45,
	            border: false
			},
            {
		       xtype: 'textfield',
		       width: 600,
		       name: 'title',
		       fieldLabel: 'Title',
		       allowBlank: false
		    },		    
		    {
	            xtype: 'container',
	            anchor: '75%',
	            layout:'column',
	            items:[{
	                xtype: 'container',
	                columnWidth:.5,
	                layout: 'anchor',
	                items: [{
			    		    	xtype: 'numberfield',
			    		    	name: 'cookingtime',
			    		    	fieldLabel: 'Cooking Time',
			    		    	allowBlank: false,
			    		    	maxValue: 999,
			    		        minValue: 0
	    		    		}, {
	    				    	xtype: 'numberfield',
	    				    	name: 'preptime',
	    				    	fieldLabel: 'Prep Time',
	    				    	allowBlank: false,
	    				    	maxValue: 999,
	    				        minValue: 0
	    				    }]
	            	},
	            	{
		                xtype: 'container',
		                columnWidth:.5,
		                layout: 'anchor',
		                items: [{
		    		    	xtype: 'combobox',
		    		    	name: 'cookingtimeunit',
		    		    	store: 'TimeUnits',
		    		    	value: 'min',
		    		    	queryMode: 'local',
		    		        displayField: 'unit',
		    		        valueField: 'unit',
		    		    	allowBlank: false
		    		    },
		    		    {
		    		    	xtype: 'combobox',
		    		    	name: 'preptimeunit',
		    		    	store: 'TimeUnits',
		    		    	value: 'min',
		    		    	queryMode: 'local',
		    		        displayField: 'unit',
		    		        valueField: 'unit',
		    		    	allowBlank: false
		    		    }]
	            	},
	        ]},
		    {
			    xtype: 'numberfield',
			    name: 'servings',
			    fieldLabel: 'Servings',
			    allowBlank: false,
			    maxValue: 999,
		        minValue: 0
			},
		    {
			    xtype: 'textareafield',
			    width: 600,
			    height: 175,
			    name: 'description',
			    fieldLabel: 'Description',
			    allowBlank: false
			},
			{
			    xtype: 'textareafield',
			    width: 600,
			    height: 175,
			    name: 'ingredientText',
			    fieldLabel: 'Ingredients',
			    allowBlank: false
			},
			{
			    xtype: 'textareafield',
			    width: 600,
			    height: 175,
			    name: 'directions',
			    fieldLabel: 'Directions',
			    allowBlank: false
			}
		  ],
	buttons: [{
	            text: 'Save'
	        },{
	            text: 'Cancel'
	        }]
});