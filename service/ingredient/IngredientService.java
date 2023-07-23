package com.metaphorce.inventorymanager.service.ingredient;

import com.metaphorce.inventorymanager.model.Ingredient;

public interface IngredientService {
    public void createNewIngredient(String ingredient, int quantity);
    public Ingredient getIngredient(String ingredient);
    public void addQuantity(int quantity);
    public void substractQuantity(int quantity);
}
