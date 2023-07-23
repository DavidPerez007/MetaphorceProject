package com.metaphorce.inventorymanager.repository.ingredient;

import com.metaphorce.inventorymanager.model.Ingredient;

public interface IngredientRepository {
    public Ingredient getIngredientByName(String name);
    public void addIngredient(String ingredientName, int quantity);
    public void substractIngredient(String ingredientName, int quantity);
}
