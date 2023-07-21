package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.CreateUserView;
import com.metaphorce.inventorymanager.views.UpdateUserView;
import com.metaphorce.inventorymanager.model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

public class UserCRUDController implements ActionListener{
    
    private UserServiceImpl userService;
    private CreateUserView createUserView;
    private UpdateUserView updateUserView;
    
    public UserCRUDController(UserServiceImpl userService, CreateUserView createUserView) {
        this.userService = userService;
        this.createUserView = createUserView;
        initCreateView();
    }
    
    public UserCRUDController(UserServiceImpl userService, UpdateUserView updateUserView) {
        this.userService = userService;
        this.updateUserView = updateUserView;
        initUpdateView();
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
            User selectedUser = (User)this.updateUserView.usersList.getSelectedValue();
            System.out.println("User to edit:" + selectedUser.getName());
            this.updateUserView.usernameField.setText(selectedUser.getName());
            this.updateUserView.passwordField.setText(selectedUser.getPassword());
            
            String username = this.updateUserView.usernameField.getText();
            String password = this.updateUserView.passwordField.getText();
            try{
                userService.updateUser(0, username, password);
            }catch(Exception ex){
                ex.printStackTrace();
            }   
        }
    }
    
    public void initCreateView(){
        this.createUserView.successLabel.setVisible(false);
        this.createUserView.createBtn.addActionListener(this);
    }
    public void initUpdateView(){
        //What Ill do is to change what JList at UpdateUserView receives, so that instead of receiving a User itll receive a String of the name and password
        DefaultListModel users = userService.getAllUsers();
        for(int i = 0; i < users.getSize(); i++){
            users.getElementAt(i);
        }
        this.updateUserView.usersList.setModel(users);
        this.updateUserView.updateBtn.addActionListener(this);
    }
    
    
}
