Ext.define('RA.store.Recipes', {
    extend: 'Ext.data.Store',
    model: 'RA.model.Recipe',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url : '/resources/recipe/list',
        reader: {
            type: 'json',
    		root: 'recipe'
        }
    },
});