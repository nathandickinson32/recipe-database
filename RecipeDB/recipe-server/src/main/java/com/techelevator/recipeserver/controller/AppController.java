package com.techelevator.recipeserver.controller;

import com.techelevator.recipeserver.dao.RecipeDao;
import com.techelevator.recipeserver.model.IdDto;
import com.techelevator.recipeserver.model.IdNameDto;
import com.techelevator.recipeserver.model.Ingredient;
import com.techelevator.recipeserver.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(path="get-recipes", method = RequestMethod.GET)
    public List<Recipe> getRecipes(){
        return recipeDao.getRecipes();
    }

    @RequestMapping(path="get-recipes-by-category-id", method = RequestMethod.POST)
    public List<Recipe> getRecipesByCategoryId(@RequestBody IdDto idDto) {
        return recipeDao.getRecipesByCategoryId(idDto);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/add-recipe", method = RequestMethod.POST)
    public Recipe addRecipe(@RequestBody Recipe recipe){
    return recipeDao.addRecipe(recipe);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/delete-recipe/", method = RequestMethod.DELETE)
    public void delete(@RequestBody IdDto idDto) {
        recipeDao.deleteRecipeById(idDto);
    }

    @RequestMapping(path="/get-ingredients-by-recipe-id", method = RequestMethod.GET)
    public List<Ingredient> getIngredientsByRecipeId(@RequestBody IdDto idDto){
        return recipeDao.getIngredientsByRecipeId(idDto);
    }

    @RequestMapping(path = "/get-recipe-names-and-ids", method = RequestMethod.GET)
    public List<IdNameDto>getRecipeNamesAndIds(){
        return recipeDao.getRecipeNamesAndIds();
    }
}
