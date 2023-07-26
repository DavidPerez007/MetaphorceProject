package com.metaphorce.inventorymanager.service.inventory;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.repository.inventory.InventoryRepositoryImpl;
import java.util.ArrayList;

public class InventoryServiceImpl implements InventoryService{
    private InventoryRepositoryImpl inventoryRepository = new InventoryRepositoryImpl();
    
    /**
     * Creates a new ingredient in the inventory with the provided name and
     * quantity.
     *
     * @param ingredient The name of the ingredient to be created.
     * @param quantity   The initial quantity of the ingredient to be added.
     */
    @Override
    public void createNewIngredient(String ingredient, int quantity) {
        Ingredient newIngredient = new Ingredient(ingredient, quantity);
        this.inventoryRepository.createIngredient(newIngredient);
        System.out.println("Ingredient created succesfully");
    }

    /**
     * Retrieves an ingredient by its name from the inventory.
     *
     * @param ingredient The name of the ingredient to retrieve.
     * @return The Ingredient object associated with the given name, or null if not
     *         found.
     */
    @Override
    public Ingredient getIngredient(String ingredient) {
        Ingredient foundIngredient = this.inventoryRepository.getIngredientByName(ingredient);
        return foundIngredient;
    }

    /**
     * Adds the given quantity of the given ingredient to the inventory.
     *
     * @param ingredientName The name of the ingredient to add.
     * @param quantity   The quantity of the ingredient to add.
     */
    @Override
    public void addQuantity(String ingredientName, int quantity) {
        this.inventoryRepository.addIngredient(ingredientName, quantity);
        System.out.println("Ingredient added: " + quantity + " elements");
    }

     /**
     * Subtracts the given quantity of the given ingredient from the inventory.
     *
     * @param ingredientName The name of the ingredient to subtract.
     * @param quantity   The quantity of the ingredient to subtract.
     */
    @Override
    public void substractQuantity(String ingredientName, int quantity) throws Exception{
        try{
            this.inventoryRepository.substractIngredient(ingredientName, quantity);
            System.out.println("Ingredient reduced: " + quantity + " elements");
        }catch(Exception e){
            throw e;
        }
        
    }

    /**
     * Retrieves all ingredients from the inventory.
     *
     * @return An ArrayList of all ingredients in the inventory.
     */
    @Override
    public ArrayList<Ingredient> getAllIngredients() {
        ArrayList ingredientsList = this.inventoryRepository.getAllIngredients();
        System.out.println(ingredientsList.get(1).toString());
        return ingredientsList;
    }
    
}
