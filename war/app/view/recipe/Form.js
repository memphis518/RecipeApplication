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
	            border: false,
			},
            {
		       xtype: 'textfield',
		       width: 600,
		       name: 'title',
		       fieldLabel: 'Title',
		       allowBlank: false
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
			    xtype: 'textfield',
			    width: 600,
			    name: 'servings',
			    fieldLabel: 'Servings',
			    allowBlank: false
			},
			{
			    xtype: 'textareafield',
			    width: 600,
			    height: 175,
			    name: 'ingredients',
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
	            text: 'Send'
	        },{
	            text: 'Cancel'
	        }]
});