package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.CreateUserView;
import com.metaphorce.inventorymanager.views.UpdateUserView;
import com.metaphorce.inventorymanager.model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

public class UserCreateController implements ActionListener{
    
    private UserServiceImpl userService;
    private CreateUserView createUserView;
    
    public UserCreateController(UserServiceImpl userService, CreateUserView createUserView) {
        this.createUserView = createUserView;
        initCreateView();
    }
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.createUserView.createBtn){
            String username = this.createUserView.usernameField.getText();
            String password = this.createUserView.passwordField.getText();
            try{
                userService.createUser(username, password);
                this.createUserView.successLabel.setVisible(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void initCreateView(){
        this.createUserView.successLabel.setVisible(false);
        this.createUserView.createBtn.addActionListener(this);
    }
    
    
    
}
