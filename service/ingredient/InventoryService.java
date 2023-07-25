package com.metaphorce.inventorymanager.service.ingredient;

import com.metaphorce.inventorymanager.model.Ingredient;
import java.util.ArrayList;

public interface InventoryService {
    public void createNewIngredient(String ingredient, int quantity);
    public Ingredient getIngredient(String ingredient);
    public void addQuantity(String ingredientName, int quantity);
    public void substractQuantity(String ingredientName, int quantity) throws Exception;
    public ArrayList<Ingredient> getAllIngredients();
}
