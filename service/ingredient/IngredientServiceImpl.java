package com.metaphorce.inventorymanager.service.ingredient;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.repository.ingredient.IngredientRepositoryImpl;

public class IngredientServiceImpl implements IngredientService{
    private IngredientRepositoryImpl ingredientRepository;
    
    @Override
    public void createNewIngredient(String ingredient, int quantity) {
        Ingredient newIngredient = new Ingredient();
    }

    @Override
    public Ingredient getIngredient(String ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addQuantity(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void substractQuantity(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
