package com.techelevator.recipeserver.dao;

import com.techelevator.recipeserver.model.IdDto;
import com.techelevator.recipeserver.model.IdNameDto;
import com.techelevator.recipeserver.model.Ingredient;
import com.techelevator.recipeserver.model.Recipe;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class RecipeJdbcDao implements RecipeDao{
    private JdbcTemplate template;

    public RecipeJdbcDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }



    private Recipe mapRowToRecipe(SqlRowSet rowSet) {
        Recipe recipe = new Recipe();

        recipe.setRecipeId(rowSet.getInt("recipe_id"));
        recipe.setRecipeName(rowSet.getString("recipe_name"));
        recipe.setDescription(rowSet.getString("description"));
        recipe.setPrepTime(rowSet.getInt("prep_time"));
        recipe.setCookTime(rowSet.getInt("cook_time"));
        recipe.setTotalTime(rowSet.getInt("total_time"));
        recipe.setServings(rowSet.getInt("servings"));
        recipe.setCategoryId(rowSet.getInt("category_id"));

        return recipe;
    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipe;";

        SqlRowSet results = template.queryForRowSet(sql);

        while (results.next()) {
            Recipe recipe = mapRowToRecipe(results);
            recipes.add(recipe);
        }
        return recipes;
    }

    public List<Recipe> getRecipesByCategoryId(IdDto idDto){
       List<Recipe> recipes = new ArrayList<>();
       String sql = "SELECT * FROM recipe WHERE category_id = ?;";
       SqlRowSet results = template.queryForRowSet(sql, idDto.getId());
       while (results.next()){
          Recipe recipe = mapRowToRecipe(results);
          recipes.add(recipe);
       }return recipes;
    }

    @Override
    public Recipe getRecipe(int recipeId) {

       Recipe recipe = null;

        String sql = "SELECT * FROM recipe WHERE recipe_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, recipeId);

            if(results.next()) {
                recipe = mapRowToRecipe(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return recipe;
    }

    @Override
    public Recipe addRecipe(Recipe recipeToSave) {

        String sql = "INSERT INTO recipe (recipe_name,description,prep_time,cook_time, total_time, servings, category_id) VALUES (?,?,?,?,?,?,?) RETURNING recipe_id";

        int newRecipeId = -1;
        try {
            newRecipeId = template.queryForObject(sql, Integer.class,

                    recipeToSave.getRecipeName(),
                    recipeToSave.getDescription(),
                    recipeToSave.getPrepTime(),
                    recipeToSave.getCookTime(),
                    recipeToSave.getTotalTime(),
                    recipeToSave.getServings(),
                    recipeToSave.getCategoryId()
            );
        } catch(CannotGetJdbcConnectionException e) {

        } catch(DataIntegrityViolationException e) {

        }

        return getRecipe(newRecipeId);
    }

public void deleteRecipeById(IdDto idDto){
    String sql = "DELETE FROM recipe WHERE recipe_id = ?;";
    int rowsAffected = template.update(sql, idDto.getId());
    if (rowsAffected > 0) {
        System.out.println("Deleted");
    } else {
        System.out.println("Id was not found");
    }
}

    public List<Ingredient> getIngredientsByRecipeId(IdDto idDto){
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredients JOIN recipe_ingredients ON ingredients.ingredient_id = recipe_ingredients.ingredient_id" +
                " Join recipe ON recipe_ingredients.recipe_id = recipe.recipe_id WHERE  recipe.recipe_id = ?;";
        SqlRowSet results = template.queryForRowSet(sql, idDto.getId());
        while (results.next()){
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientId(results.getInt("ingredient_id"));
            ingredient.setIngredientName(results.getString("ingredient_name"));
            ingredient.setDescription(results.getString("description"));
            ingredients.add(ingredient);
        }return ingredients;
        }
    public List<IdNameDto> getRecipeNamesAndIds() {
        List<IdNameDto> idNameDtos = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_name FROM recipe;";

        SqlRowSet results = template.queryForRowSet(sql);

        while (results.next()) {
            IdNameDto idNameDto = new IdNameDto();
            idNameDto.setId(results.getInt("recipe_id"));
            idNameDto.setName(results.getString("recipe_name"));
            idNameDtos.add(idNameDto);
        }
        return idNameDtos;
    }
}
