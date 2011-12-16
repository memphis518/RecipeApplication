Ext.define('RA.controller.Menu', {
    extend: 'Ext.app.Controller',
    stores: ['MenuItems'],
    models: ['MenuItem'],
    views: [ 'menu.View' ]
});