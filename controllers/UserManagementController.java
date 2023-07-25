package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.CreateUserView;
import com.metaphorce.inventorymanager.views.DeleteUserView;
import com.metaphorce.inventorymanager.views.LogInView;
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
        initView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.crudView.createBtn){
            CreateUserView createView = new CreateUserView();
            UserCreateController createController = new UserCreateController(userService, createView);
            this.crudView.dispose();
            createView.setVisible(true);
        }
        if(e.getSource() ==  this.crudView.updateBtn){
            UpdateUserView updateView = new UpdateUserView();
            UserUpdateController updateController = new UserUpdateController(userService, updateView);
            this.crudView.dispose();
            updateView.setVisible(true);
        }
        if(e.getSource() ==  this.crudView.deleteBtn){
            DeleteUserView deleteView = new DeleteUserView();
            UserDeleteController deleteController = new UserDeleteController(userService, deleteView);
            this.crudView.dispose();
            deleteView.setVisible(true);
        }
        if(e.getSource() == this.crudView.backBtn){
            LogInView logInView = new LogInView();
            LogInController logInController = new LogInController(this.userService, logInView);
            this.crudView.dispose();
            logInView.setVisible(true);

        }
    }
    
    public void initView(){
        this.crudView.setLocationRelativeTo(null);
        this.crudView.createBtn.addActionListener(this);
        this.crudView.deleteBtn.addActionListener(this);
        this.crudView.updateBtn.addActionListener(this);
        this.crudView.backBtn.addActionListener(this);
    }
    
}
