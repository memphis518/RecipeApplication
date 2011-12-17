Ext.define('RA.view.recipe.Panel' ,{
    extend: 'Ext.panel.Panel',
    alias : 'widget.recipepanel',
    bodyPadding: 50,
    width: 800,
    layout: 'card',
    border: false,
    items: [
            {
			    xtype: 'recipeslist'
			},
			{
			    xtype: 'recipeview'
			},
			{
			    xtype: 'recipeform'
			}
			
    	   ]
});