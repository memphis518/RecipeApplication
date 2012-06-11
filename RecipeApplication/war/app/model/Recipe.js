Ext.define('RA.model.Recipe', {
    extend: 'Ext.data.Model',
    fields: ['title', 'description', 'servings', 'preptime', 'preptimeunit', 'cookingtime', 'cookingtimeunit', 'ingredientText', 'directions', 'image', 'imageURL']
});