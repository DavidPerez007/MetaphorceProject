package com.metaphorce.inventorymanager.service.inventory;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.repository.inventory.InventoryRepositoryImpl;
import java.util.ArrayList;

public class InventoryServiceImpl implements InventoryService{
    private InventoryRepositoryImpl inventoryRepository = new InventoryRepositoryImpl();
    
    @Override
    public void createNewIngredient(String ingredient, int quantity) {
        Ingredient newIngredient = new Ingredient(ingredient, quantity);
        this.inventoryRepository.createIngredient(newIngredient);
        System.out.println("Ingredient created succesfully");
    }

    @Override
    public Ingredient getIngredient(String ingredient) {
        Ingredient foundIngredient = this.inventoryRepository.getIngredientByName(ingredient);
        return foundIngredient;
    }

    @Override
    public void addQuantity(String ingredientName, int quantity) {
        this.inventoryRepository.addIngredient(ingredientName, quantity);
        System.out.println("Ingredient added: " + quantity + " elements");
    }

    @Override
    public void substractQuantity(String ingredientName, int quantity) throws Exception{
        try{
            this.inventoryRepository.substractIngredient(ingredientName, quantity);
            System.out.println("Ingredient reduced: " + quantity + " elements");
        }catch(Exception e){
            throw e;
        }
        
    }

    @Override
    public ArrayList<Ingredient> getAllIngredients() {
        ArrayList ingredientsList = this.inventoryRepository.getAllIngredients();
        System.out.println(ingredientsList.get(1).toString());
        return ingredientsList;
    }
    
}
