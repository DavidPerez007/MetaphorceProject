package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.service.ingredient.InventoryServiceImpl;
import com.metaphorce.inventorymanager.service.user.UserService;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.InventoryView;
import com.metaphorce.inventorymanager.views.LogInView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class InventoryViewController implements ActionListener{
    private InventoryView inventoryView;
    private InventoryServiceImpl inventoryService;
    private DefaultComboBoxModel comboBoxModel;
    private ArrayList<Ingredient> ingredientList = new ArrayList();
    private DefaultTableModel tableModel;
    
    public InventoryViewController(InventoryServiceImpl inventoryService, InventoryView inventoryView){
        this.inventoryView = inventoryView;
        this.inventoryService = inventoryService;
        initView();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.inventoryView.requestBtn){
            Ingredient ingredient = this.inventoryService.getIngredient((String)this.inventoryView.ingredientComboBox.getSelectedItem());
            Integer quantity = (Integer)this.inventoryView.quantitySpinner.getValue();
            this.inventoryService.addQuantity(ingredient.getIngredientName(), quantity);
        }
        if(e.getSource() == this.inventoryView.sellBtn){
            Ingredient ingredient = this.inventoryService.getIngredient((String)this.inventoryView.ingredientComboBox.getSelectedItem());
            Integer quantity = (Integer)this.inventoryView.quantitySpinner.getValue();
            this.inventoryService.substractQuantity(ingredient.getIngredientName(), quantity);
        }
        if(e.getSource() == this.inventoryView.cancelBtn){
            this.inventoryView.ingredientComboBox.setSelectedItem(null);
            this.inventoryView.quantitySpinner.setValue(0);
        }
        if(e.getSource() == this.inventoryView.backBtn){
            UserServiceImpl userService = new UserServiceImpl();
            LogInView logInView = new LogInView();
            LogInController logInController = new LogInController(userService, logInView);
            this.inventoryView.dispose();
            logInView.setVisible(true);
        }
    
    }
    
    public void initView(){
        this.comboBoxModel = new DefaultComboBoxModel<>();
        this.ingredientList = this.inventoryService.getAllIngredients();

        for (Ingredient currentIngredient : ingredientList) {
            comboBoxModel.addElement(currentIngredient.getIngredientName());
        }
        String[] columnNames = {"ingredient", "quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Ingredient ingredient : ingredientList) {
            Object[] rowData = {ingredient.getIngredientName(), ingredient.getIngredientQuantity()};
            tableModel.addRow(rowData);
        }
        this.inventoryView.ingredientTable.setModel(tableModel);
        this.inventoryView.ingredientComboBox.setModel(comboBoxModel);
        this.inventoryView.cancelBtn.addActionListener(this);
        this.inventoryView.doneBtn.addActionListener(this);
        this.inventoryView.requestBtn.addActionListener(this);
        this.inventoryView.sellBtn.addActionListener(this);
        this.inventoryView.backBtn.addActionListener(this);
        this.inventoryView.setLocationRelativeTo(null);
    }

    
}
