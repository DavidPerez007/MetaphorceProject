package com.metaphorce.inventorymanager.repository.ingredient;

import com.metaphorce.inventorymanager.model.Ingredient;
import java.util.ArrayList;

public interface InventoryRepository {
    public Ingredient getIngredientByName(String name);
    public void createIngredient(Ingredient ingredient);
    public void addIngredient(String ingredientName, int quantity);
    public void substractIngredient(String ingredientName, int quantity) throws Exception;
    public ArrayList getAllIngredients();
}
