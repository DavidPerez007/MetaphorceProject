package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.CreateUserView;
import com.metaphorce.inventorymanager.views.UpdateUserView;
import com.metaphorce.inventorymanager.model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCRUDController implements ActionListener{
    
    private UserServiceImpl userService;
    private CreateUserView createUserView;
    private UpdateUserView updateUserView;
    
    public UserCRUDController(UserServiceImpl userService, CreateUserView createUserView) {
        this.userService = userService;
        this.createUserView = createUserView;
        initView();
    }
    
    public UserCRUDController(UserServiceImpl userService, UpdateUserView updateUserView) {
        this.userService = userService;
        this.updateUserView = updateUserView;
        initView();
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
        if(e.getSource() == this.updateUserView.updateBtn){
           //alavberga nose hacer esto
           //User selectedUser = userService.searchUserByName(this.updateUserView.usersList.getSelectedValue());
            String username = this.updateUserView.usernameField.getText();
            String password = this.updateUserView.passwordField.getText();
            try{
                userService.updateUser(0, username, password);
                this.createUserView.successLabel.setVisible(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }   
        }
    }
    
    public void initView(){
        this.createUserView.successLabel.setVisible(false);
        this.createUserView.createBtn.addActionListener(this);
    }
    
}
