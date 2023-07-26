package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.service.inventory.InventoryServiceImpl;
import com.metaphorce.inventorymanager.service.report.ReportGenerator;
import com.metaphorce.inventorymanager.service.security.Authenticator;
import java.awt.event.ActionListener;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.InventoryView;
import com.metaphorce.inventorymanager.views.LogInView;
import com.metaphorce.inventorymanager.views.UserManagementView;
import java.awt.event.ActionEvent;

public class LogInController implements ActionListener{
    private UserServiceImpl userService;
    private LogInView logInView;
    
    /**
     * Creates a new LogInController with the given parameters.
     * 
     * @param userService
     * @param logInView
     */
    public LogInController(UserServiceImpl userService, LogInView logInView){
        this.userService = userService;
        this.logInView = logInView;
        initView();
    }
    
    /**
     * Handles user actions performed on the inventory view's buttons and components.
     * @param click The ActionEvent triggered by user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent click){
        if(click.getSource() == this.logInView.logInBtn){
            String username = this.logInView.usernameField.getText();
            String password = this.logInView.passwordField.getText(); //Convert to getPassword()
            User user = this.userService.searchUserByName(username);
            Authenticator auth = new Authenticator(userService); 
            boolean isAuthenticated = auth.authenticate(username, password);
            if(isAuthenticated){    
                InventoryServiceImpl inventoryService = new InventoryServiceImpl();
                InventoryView mainView = new InventoryView();
                InventoryViewController inventoryViewController = new InventoryViewController(inventoryService, mainView, user);
                this.logInView.dispose();
                mainView.setVisible(true);
            }else{
                this.logInView.alertLabel.setVisible(true);
            }
        }
        
    }
    
    /**
     * Initializes the view and adds action listeners to its components.
     */
    public void initView(){
        this.logInView.logInBtn.addActionListener(this);
        this.logInView.alertLabel.setVisible(false);
        this.logInView.setLocationRelativeTo(null);
    }
}
