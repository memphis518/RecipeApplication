Ext.define('RA.store.Recipes', {
    extend: 'Ext.data.Store',
    model: 'RA.model.Recipe',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api : {
        	read: '/resources/recipe/list',
        	create: '/resources/recipe/create'
        },
        reader: {
            type: 'json',
    		root: 'recipe'
        }
    },
});