Ext.define('RA.view.recipe.List' ,{
	extend: 'Ext.panel.Panel',
    alias : 'widget.recipeslist',
    border: false,
    items: [     
            {
            	xtype: 'dataview',
            	store: 'Recipes',
                tpl: [
                	    '<tpl for=".">',
                        '<div class="recipeThumb">',
                          '<img height="150" width="150" src="{src}" />',
                          '<br/><span class="thumbTitle" style="text-align:center;">{title}</span>',
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