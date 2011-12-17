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
                	xtype: 'panel',
                	html:  'Recipe Application',
                	height: 100,
                	border: false
                },
                {
                	xtype: 'panel',
                	layout: 'hbox',
                	width:  '100%',
                	border: false,
                	items: [
								{
								    xtype: 'panel',
								    width: 200,
								    border: false,
								    items:[{xtype:'menuview'}]
								    
								},
								{
									xtype: 'panel',
								    width: '100%',
								    border: false,
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