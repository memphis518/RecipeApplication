Ext.define('RA.store.Recipes', {
    extend: 'Ext.data.Store',
    model: 'RA.model.Recipe',
    proxy: {
        type: 'ajax',
        url : '/recipe/list',
        reader: {
            type: 'json'
        }
    },
});