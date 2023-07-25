package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.DeleteUserView;
import com.metaphorce.inventorymanager.views.LogInView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class UserDeleteController implements ActionListener{
    private UserServiceImpl userService;
    private DeleteUserView deleteUserView;
    private User selectedUser;
    private DefaultListModel<String> usersListModel;
    private ArrayList<User> usersList;
    
    public UserDeleteController(UserServiceImpl userService, DeleteUserView deleteUserView) {
        this.userService = userService;
        this.deleteUserView = deleteUserView;
        initDeleteView();
    }
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.deleteUserView.selectUserBtn) {
            int selectedIndex = this.deleteUserView.usersList.getSelectedIndex();
            if (selectedIndex != -1) {
                selectedUser = usersList.get(selectedIndex);
                this.deleteUserView.userToDeleteLabel.setText(selectedUser.getName());
                this.deleteUserView.warningLabel.setVisible(true);
                this.deleteUserView.userToDeleteLabel.setVisible(true);
            }
        }

        if (e.getSource() == this.deleteUserView.deleteUserBtn) {
            if (selectedUser != null) {
                userService.deleteUser(selectedUser.getId());
                this.deleteUserView.succesfulLabel.setVisible(true);
            } else {
                this.deleteUserView.errorLabel.setVisible(true);
            }
        }
        if(e.getSource() == this.deleteUserView.backBtn){
            LogInView logInView = new LogInView();
            LogInController logInController = new LogInController(this.userService, logInView);
            this.deleteUserView.dispose();
            logInView.setVisible(true);
        }
    }
    
    public void initDeleteView() {
        this.usersListModel = new DefaultListModel<>();
        this.usersList = this.userService.getAllUsers();

        for (User currentUser : usersList) {
            usersListModel.addElement(currentUser.getName());
        }
        this.deleteUserView.usersList.setModel(usersListModel);
        this.deleteUserView.deleteUserBtn.addActionListener(this);
        this.deleteUserView.selectUserBtn.addActionListener(this);
        this.deleteUserView.backBtn.addActionListener(this);
        this.deleteUserView.succesfulLabel.setVisible(false);
        this.deleteUserView.warningLabel.setVisible(false);
        this.deleteUserView.errorLabel.setVisible(false);
        this.deleteUserView.setLocationRelativeTo(null);
    }
    
    
    

}
