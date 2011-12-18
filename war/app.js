Ext.require([ 'Ext.util.History']); 

Ext.application({
    name: 'RA',

    appFolder: 'app',

    controllers: [
                  'Recipes',
                  'Menu'
                 ],
    handleHistoryChange: function(token) {
         var parts = token.split(":");
         var controller = this.getController(parts[0]);
         controller[parts[1]](parts[2]);
    },
    launch: function() {
        Ext.create('Ext.container.Viewport', {
        	layout: {
                type: 'anchor',
                align: 'left'
            },
            border: false,
            autoScroll: true,
            height: '100%',
            items: [
                {
                	html:  'Recipe Application',
                	height: 100,
                	border: false
                },
                {
                	layout: 'hbox',
                	width:  '100%',
                	border: false,
                	items: [
								{
								    width: 200,
								    border: false,
								    items:[{xtype:'menuview'}]
								    
								},
								{
								    width: '100%',
								    border: false,
								    style: {'border-left':'1px solid CCCCCC'},
								    items: [
								            	{
								            	  xtype: 'recipepanel'
								            	}
								            ]
								}
                	        ]
                }	                
            ]
        });
        
        
        Ext.History.init();
        Ext.History.on('change', this.handleHistoryChange, this);
    }
});