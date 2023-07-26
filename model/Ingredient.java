package com.metaphorce.inventorymanager.model;

public class Ingredient {
    private String ingredientName;
    private int quantity;
    
     /**
     * Creates a new Ingredient object with the provided name and quantity
     * status.
     *
     * @param ingredientName     The name of the ingredient.
     * @param quantity The quantity available on the database.
     */
    public Ingredient(String ingredientName, int quantity){
        setIngredientName(ingredientName);
        setQuantity(quantity);
    }
    

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
    
    public String getIngredientName() {
        return this.ingredientName;
    }
    
    public int getIngredientQuantity() {
        return this.quantity;
    }
}
