package com.metaphorce.inventorymanager.model;

public class Ingredient {
    private String ingredientName;
    private int quantity;
    
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
