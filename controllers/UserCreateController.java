package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.CreateUserView;
import com.metaphorce.inventorymanager.views.UpdateUserView;
import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.views.LogInView;
import com.metaphorce.inventorymanager.views.UserManagementView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

public class UserCreateController implements ActionListener{
    
    private UserServiceImpl userService;
    private CreateUserView createUserView;
    
    public UserCreateController(UserServiceImpl userService, CreateUserView createUserView) {
        this.createUserView = createUserView;
        this.userService = userService;
        initCreateView();
    }
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.createUserView.createBtn){
            String username = this.createUserView.usernameField.getText();
            String password = this.createUserView.passwordField.getText();
            boolean isAdmin = this.createUserView.isAdminCheckbox.isSelected();
            try{
                userService.createUser(username, password, isAdmin);
                this.createUserView.successLabel.setVisible(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        if(e.getSource() == this.createUserView.backBtn){
            UserManagementView userManagementView = new UserManagementView();
            UserManagementController userController = new UserManagementController(this.userService, userManagementView);
            userManagementView.setVisible(true);
            this.createUserView.dispose();
        }
    }
    
    public void initCreateView(){
        this.createUserView.successLabel.setVisible(false);
        this.createUserView.createBtn.addActionListener(this);
        this.createUserView.backBtn.addActionListener(this);
        this.createUserView.setLocationRelativeTo(null);
    }
    
    
    
}
