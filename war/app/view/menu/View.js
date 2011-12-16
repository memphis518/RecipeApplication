Ext.define('RA.view.menu.View' ,{
	extend: 'Ext.tree.Panel',
    alias : 'widget.menuview',
    width: 200,
    height: 300,
    store: Ext.create('Ext.data.TreeStore', {
        root: {
            expanded: false,
            children: [
                { text: "Recipe Box", hideCollapseTool:true, collapsible: true, children: [
                    { text: "My Recipes", leaf: true }
                ] },
                { text: "Log Out", leaf: true }
            ]
        }
    }),
    rootVisible: false
});