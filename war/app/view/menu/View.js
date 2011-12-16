Ext.define('RA.view.menu.View' ,{
	extend: 'Ext.tree.Panel',
    alias : 'widget.menuview',
    width: 200,
    height: 300,
    useArrows: true,
    store: 'MenuItems',
    forceFit : true,
    columns : [{
        xtype : 'treecolumn',
        dataIndex : 'name'
    }],
    hideHeaders : true,
    rootVisible: false
    
});