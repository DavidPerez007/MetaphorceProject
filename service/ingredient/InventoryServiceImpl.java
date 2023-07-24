package com.metaphorce.inventorymanager.service.ingredient;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.repository.ingredient.InventoryRepositoryImpl;
import java.util.ArrayList;

public class InventoryServiceImpl implements InventoryService{
    private InventoryRepositoryImpl ingredientRepository = new InventoryRepositoryImpl();
    
    @Override
    public void createNewIngredient(String ingredient, int quantity) {
        Ingredient newIngredient = new Ingredient(ingredient, quantity);
        this.ingredientRepository.createIngredient(newIngredient);
        System.out.println("Ingredient created succesfully");
    }

    @Override
    public Ingredient getIngredient(String ingredient) {
        Ingredient foundIngredient = this.ingredientRepository.getIngredientByName(ingredient);
        return foundIngredient;
    }

    @Override
    public void addQuantity(String ingredientName, int quantity) {
        this.ingredientRepository.addIngredient(ingredientName, quantity);
        System.out.println("Ingredient added: " + quantity + " elements");
    }

    @Override
    public void substractQuantity(String ingredientName, int quantity) {
         this.ingredientRepository.addIngredient(ingredientName, quantity);
        System.out.println("Ingredient reduced: " + quantity + " elements");
    }

    @Override
    public ArrayList<Ingredient> getAllIngredients() {
        ArrayList ingredientsList = this.ingredientRepository.getAllIngredients();
        System.out.println(ingredientsList.get(1).toString());
        return ingredientsList;
    }
    
}
