package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.CreateUserView;
import com.metaphorce.inventorymanager.views.DeleteUserView;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.crudView.createBtn){
            CreateUserView createView = new CreateUserView();
            UserCreateController createController = new UserCreateController(userService, createView);
            createView.setVisible(true);
        }
        if(e.getSource() ==  this.crudView.updateBtn){
            UpdateUserView updateView = new UpdateUserView();
            UserUpdateController updateController = new UserUpdateController(userService, updateView);
            updateView.setVisible(true);
        }
        if(e.getSource() ==  this.crudView.deleteBtn){
            DeleteUserView deleteView = new DeleteUserView();
            UserDeleteController deleteController = new UserDeleteController(userService, deleteView);
            deleteView.setVisible(true);
        }
    }
    
}
