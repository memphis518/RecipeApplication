Ext.define('RA.view.menu.View' ,{
	extend: 'Ext.menu.Menu',
    alias : 'widget.menuview',
    width: 150,
    height: 300,
    floating: false,
    showSeparator: false,
    minWidth: 145,
    bodyCls: 'menuParent',
    items: [
            {
            	text: 'Recipe Box',
            	cls:  'menuParent',
            },
            {   
            	text: 'Create Recipe'
            },
            {
            	text: 'View Recipes'
            }
           ]
});