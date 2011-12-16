Ext.define('RA.store.MenuItems', {
    extend: 'Ext.data.TreeStore',
    model: 'RA.model.MenuItem',
    root : {
        children : [{
            name : 'parent',
            detail : 'this is description for the parent',
            expanded : true,
            children : [{
                name : 'child 1',
                detail : 'this is description for child',
                leaf : true
            },{
                name : 'child 2',
                detail : 'this is description for child',
                leaf : true
            }]
        }]
    }
});