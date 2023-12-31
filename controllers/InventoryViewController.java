package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.service.inventory.InventoryServiceImpl;
import com.metaphorce.inventorymanager.service.report.ReportGenerator;
import com.metaphorce.inventorymanager.service.user.UserService;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.InventoryView;
import com.metaphorce.inventorymanager.views.LogInView;
import com.metaphorce.inventorymanager.views.UserManagementView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class InventoryViewController implements ActionListener{
    private InventoryView inventoryView;
    private InventoryServiceImpl inventoryService;
    private User user;
    private DefaultComboBoxModel comboBoxModel;
    private ArrayList<Ingredient> ingredientList = new ArrayList();
    private DefaultTableModel tableModel;
    
     /**
     * Creates a new InventoryViewController with the given parameters.
     *
     * @param inventoryService The service for inventory-related operations.
     * @param inventoryView    The view for inventory management GUI.
     * @param user             The currently logged-in user.
     */
    public InventoryViewController(InventoryServiceImpl inventoryService, InventoryView inventoryView, User user){
        this.inventoryView = inventoryView;
        this.inventoryService = inventoryService;
        this.user = user;
        initView();
    }
    
    /**
     * Handles user actions performed on the inventory view's buttons and
     * components.
     *
     * @param e The ActionEvent triggered by user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.inventoryView.requestBtn){
            Ingredient ingredient = this.inventoryService.getIngredient((String)this.inventoryView.ingredientComboBox.getSelectedItem());
            Integer quantity = (Integer)this.inventoryView.quantitySpinner.getValue();
            this.inventoryService.addQuantity(ingredient.getIngredientName(), quantity);
            this.inventoryView.alertLabel.setText("Requested succesfully");
            this.inventoryView.alertLabel.setVisible(true);
        }
        if(e.getSource() == this.inventoryView.sellBtn){
            Ingredient ingredient = this.inventoryService.getIngredient((String)this.inventoryView.ingredientComboBox.getSelectedItem());
            Integer quantity = (Integer)this.inventoryView.quantitySpinner.getValue();
            try{
                this.inventoryService.substractQuantity(ingredient.getIngredientName(), quantity);
                this.inventoryView.alertLabel.setText("Sold succesfully");
                this.inventoryView.alertLabel.setVisible(true);
            }catch(Exception ex){
                this.inventoryView.quantitySpinner.setValue(ingredient.getIngredientQuantity());
                this.inventoryView.alertLabel.setText("Not enough to sell");
                this.inventoryView.alertLabel.setVisible(true);
            }
            
        }
        if(e.getSource() == this.inventoryView.cancelBtn){
            this.inventoryView.ingredientComboBox.setSelectedItem(null);
            this.inventoryView.quantitySpinner.setValue(0);
        }
        if(e.getSource() == this.inventoryView.manageUsersBtn){
            UserServiceImpl userService = new UserServiceImpl();
            UserManagementView crudView = new UserManagementView();
            UserManagementController crudController = new UserManagementController(userService, crudView);
            this.inventoryView.dispose();
            crudView.setVisible(true);
        }
        if(e.getSource() == this.inventoryView.genReportBtn){
            InventoryServiceImpl inventoryService = new InventoryServiceImpl();
            ReportGenerator reportGen = new ReportGenerator(inventoryService);
            reportGen.generateReport();
            
        }
        if(e.getSource() == this.inventoryView.doneBtn){
            this.inventoryView.alertLabel.setVisible(false);
        }
        
        if(e.getSource() == this.inventoryView.backBtn){
            UserServiceImpl userService = new UserServiceImpl();
            LogInView logInView = new LogInView();
            LogInController logInController = new LogInController(userService, logInView);
            this.inventoryView.dispose();
            logInView.setVisible(true);
        }
    
    }
    
    /**
     * Initializes the inventory view's components and adds action listeners to the buttons.
     */
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
        this.inventoryView.genReportBtn.addActionListener(this);
        this.inventoryView.manageUsersBtn.setVisible(false);
        this.inventoryView.alertLabel.setVisible(false);
        this.inventoryView.setLocationRelativeTo(null);
        if(this.user.isAdmin() == true){
            this.inventoryView.manageUsersBtn.addActionListener(this);
            this.inventoryView.manageUsersBtn.setVisible(true);
        }
    }

    
}
