package com.techelevator.recipeserver.dao;

import com.techelevator.recipeserver.model.IdDto;
import com.techelevator.recipeserver.model.IdNameDto;
import com.techelevator.recipeserver.model.Ingredient;
import com.techelevator.recipeserver.model.Recipe;

import java.util.List;

public interface RecipeDao {

    public List<Recipe> getRecipes();
    public List<Recipe> getRecipesByCategoryId(IdDto idDto);
    public Recipe getRecipe(int recipeId);
    public Recipe addRecipe(Recipe recipe);
    public void deleteRecipeById(IdDto idDto);
    public List<Ingredient> getIngredientsByRecipeId(IdDto idDto);
    public List<IdNameDto> getRecipeNamesAndIds();
}
