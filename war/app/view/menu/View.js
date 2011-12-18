Ext.define('RA.view.menu.View' ,{
	extend: 'Ext.menu.Menu',
    alias : 'widget.menuview',
    width: 200,
    floating: false,
    showSeparator: false,
    minWidth: 195,
    bodyCls: 'menuBase',
    border: false,
    items: [
            {
            	text: 'Recipe Box',
            	itemId: 'recipebox'
            },
            {
            	text: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;View Recipes',
            	hidden: true,
            	itemId: 'recipebox-view'
            },
            {   
            	text: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Create Recipe',
            	hidden: true,
            	itemId: 'recipebox-create'
            },
            {
            	text: 'Logout',
            	itemId: 'logout'	
            }
           ]
});