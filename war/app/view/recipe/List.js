Ext.define('RA.view.recipe.List' ,{
	extend: 'Ext.panel.Panel',
    alias : 'widget.recipeslist',
    border: false,
    items: [
            {
            	xtype: 'panel',
            	border: false,
            	items: [
            	        {
            	        	xtype: 'button',
            	        	text:  'Create Recipe' 
            	        }
            	       ]
            },           
            {
            	xtype: 'dataview',
            	store: 'Recipes',
                tpl: [
                	    '<tpl for=".">',
                        '<div class="recipeThumb">',
                          '<img height="150" width="150" src="{src}" />',
                          '<br/><span style="font-weight: bold; text-align:center;">{title}</span>',
                        '</div>',
                        '</tpl>'
                     ],
                itemSelector: 'div.recipeThumb',
                overItemCls: 'x-item-over',
                trackOver: true,
                emptyText: 'No recipes created'
            }
           ]
});