package com.techelevator.recipeserver.model;

public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String description;

    public Ingredient(int ingredientId, String ingredientName, String description) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.description = description;
    }

    public Ingredient() {

    }


    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
