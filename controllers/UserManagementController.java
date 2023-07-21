package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.CreateUserView;
import com.metaphorce.inventorymanager.views.UpdateUserView;
import com.metaphorce.inventorymanager.views.UserManagementView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagementController implements ActionListener{
    private UserServiceImpl userService;
    private UserManagementView crudView;
    UserManagementController(UserServiceImpl userService, UserManagementView crudView) {
        this.userService = userService;
        this.crudView = crudView;
        this.crudView.createBtn.addActionListener(this);
        this.crudView.deleteBtn.addActionListener(this);
        this.crudView.updateBtn.addActionListener(this);
        this.crudView.findBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.crudView.createBtn){
            CreateUserView createView = new CreateUserView();
            createView.successLabel.setVisible(false);
            UserCRUDController createController = new UserCRUDController(userService, createView);
            createView.setVisible(true);
            
        }
        if(e.getSource() ==  this.crudView.updateBtn){
            UpdateUserView updateView = new UpdateUserView();
            UserCRUDController updateController = new UserCRUDController(userService, updateView);
            updateView.setVisible(true);
        }
    }
    
}
