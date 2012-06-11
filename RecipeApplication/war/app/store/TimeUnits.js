Ext.define('RA.store.TimeUnits', {
    extend: 'Ext.data.Store',
    model: 'RA.model.TimeUnit',
    data: [
    	 {unit: 'min'},
    	 {unit: 'hr'}
     ]
});