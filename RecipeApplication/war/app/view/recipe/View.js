Ext.define('RA.view.recipe.View' ,{
	extend: 'Ext.panel.Panel',
    alias : 'widget.recipeview',
    border: false,
    tpl: new Ext.XTemplate(['<div class="recipeThumb">',
          '<img height="150" width="150" src="{src}" />',
          '</div>',
          '<br/>',
          '<div class="thumbTitle">{title}</div>',
          '<div class="recipeViewDescription">{description}</div>',
          '<div class="x-clear"></div>',
          '<span class="recipeViewLabel">Servings:</span><span>{servings}</span>',
          '<div class="x-clear"></div>',
          '<span class="recipeViewLabel">Cooking Time:</span><span>{cookingtime} {cookingtimeunit}</span>',
          '<div class="x-clear"></div>',
          '<span class="recipeViewLabel">Preparation Time:</span><span>	{preptime} {preptimeunit}</span>',
          '<div class="recipeViewLabel">Ingredients:</div>',
          '<div class="recipeViewValue">{ingredients}</div>',
          '<div class="recipeViewLabel">Directions:</div>',
          '<div class="recipeViewValue">{directions}</div>'
          ])
});