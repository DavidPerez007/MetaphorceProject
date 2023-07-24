package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.service.ingredient.InventoryServiceImpl;
import com.metaphorce.inventorymanager.views.InventoryView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class InventoryViewController implements ActionListener{
    private InventoryView inventoryView;
    private InventoryServiceImpl inventoryService;
    private DefaultComboBoxModel comboBoxModel;
    private ArrayList<Ingredient> ingredientList = new ArrayList();
    
    public InventoryViewController(InventoryServiceImpl inventoryService, InventoryView inventoryView){
        this.inventoryView = inventoryView;
        this.inventoryService = inventoryService;
        initView();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.inventoryView.requestBtn){
            Ingredient ingredient = (Ingredient)this.inventoryView.ingredientComboBox.getSelectedItem();
            Integer quantity = (Integer)this.inventoryView.quantitySpinner.getValue();
            this.inventoryService.addQuantity(ingredient.getIngredientName(), quantity);
        }
        if(e.getSource() == this.inventoryView.sellBtn){
            Ingredient ingredient = (Ingredient)this.inventoryView.ingredientComboBox.getSelectedItem();
            Integer quantity = (Integer)this.inventoryView.quantitySpinner.getValue();
            this.inventoryService.substractQuantity(ingredient.getIngredientName(), quantity);
        }
        if(e.getSource() == this.inventoryView.cancelBtn){
            this.inventoryView.ingredientComboBox.setSelectedItem(null);
            this.inventoryView.quantitySpinner.setValue(0);
        }
    }
    
    public void initView(){
        this.comboBoxModel = new DefaultComboBoxModel<>();
        this.ingredientList = this.inventoryService.getAllIngredients();

        for (Ingredient currentIngredient : ingredientList) {
            comboBoxModel.addElement(currentIngredient.getIngredientName());
        }
        this.inventoryView.cancelBtn.addActionListener(this);
        this.inventoryView.doneBtn.addActionListener(this);
        this.inventoryView.requestBtn.addActionListener(this);
        this.inventoryView.sellBtn.addActionListener(this);
    }

    
}
