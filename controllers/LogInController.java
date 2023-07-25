package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.service.ingredient.InventoryServiceImpl;
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
    
    public LogInController(UserServiceImpl userService, LogInView logInView){
        this.userService = userService;
        this.logInView = logInView;
        initView();
    }
    
    @Override
    public void actionPerformed(ActionEvent click){
        if(click.getSource() == this.logInView.logInBtn){
            String username = this.logInView.usernameField.getText();
            String password = this.logInView.passwordField.getText(); //Convert to getPassword()
            Authenticator auth = new Authenticator(userService); 
            boolean isAuthenticated = auth.authenticate(username, password);
            if(isAuthenticated){    
                InventoryServiceImpl inventoryService = new InventoryServiceImpl();
                InventoryView mainView = new InventoryView();
                InventoryViewController inventoryViewController = new InventoryViewController(inventoryService, mainView);
                this.logInView.dispose();
                mainView.setVisible(true);
            }
        }
        if(click.getSource() == this.logInView.manageUsersBtn){
            UserManagementView crudView = new UserManagementView();
            UserManagementController crudController = new UserManagementController(userService, crudView);
            this.logInView.dispose();
            crudView.setVisible(true);
        }
        if(click.getSource() == this.logInView.genReportBtn){
            InventoryServiceImpl inventoryService = new InventoryServiceImpl();
            ReportGenerator reportGen = new ReportGenerator(inventoryService);
            reportGen.generateReport();
            
        }
    }
    
    public void initView(){
        this.logInView.manageUsersBtn.addActionListener(this);
        this.logInView.logInBtn.addActionListener(this);
        this.logInView.genReportBtn.addActionListener(this);
        this.logInView.setLocationRelativeTo(null);
    }
}
