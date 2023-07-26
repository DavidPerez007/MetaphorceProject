package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.LogInView;
import com.metaphorce.inventorymanager.views.UpdateUserView;
import com.metaphorce.inventorymanager.views.UserManagementView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class UserUpdateController implements ActionListener {
    private UserServiceImpl userService;
    private UpdateUserView updateUserView;
    private User selectedUser = null;
    private DefaultListModel<String> usersListModel;
    private ArrayList<User> usersList;

     /**
     * Creates a new UserUpdateController with the given parameters.
     * 
     * @param userService
     * @param updateUserView
     */
    public UserUpdateController(UserServiceImpl userService, UpdateUserView updateUserView) {
        this.userService = userService;
        this.updateUserView = updateUserView;
        initUpdateView();
    }

    /**
     * Handles user actions performed on the inventory view's buttons
     * 
     * @param e The ActionEvent triggered by user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.updateUserView.selectBtn) {
            int selectedIndex = this.updateUserView.usersList.getSelectedIndex();
            if (selectedIndex != -1) {
                selectedUser = usersList.get(selectedIndex);
                this.updateUserView.usernameField.setText(selectedUser.getName());
                this.updateUserView.passwordField.setText(selectedUser.getPassword());
                this.updateUserView.isAdminCheckbox.setSelected(selectedUser.isAdmin());
            }
        }

        if (e.getSource() == this.updateUserView.updateBtn) {
            System.out.print(selectedUser.getName());
            if (selectedUser != null) {
                String newName = this.updateUserView.usernameField.getText();
                String newPassword = this.updateUserView.passwordField.getText();
                boolean isAdmin = this.updateUserView.isAdminCheckbox.isSelected();
                userService.updateUser(selectedUser.getId(), newName, newPassword, isAdmin);
                this.updateUserView.succesfulLabel.setVisible(true);
            } else {
                this.updateUserView.errorLabel.setVisible(true);
            }
        }
        if(e.getSource() == this.updateUserView.backBtn){
            UserManagementView userManagementView = new UserManagementView();
            UserManagementController userController = new UserManagementController(this.userService, userManagementView);
            this.updateUserView.dispose();
            userManagementView.setVisible(true);
        }
    }

    /**
     * Initializes the update view's components and adds action listeners to the
     */
    public void initUpdateView() {
        this.usersListModel = new DefaultListModel<>();
        this.usersList = this.userService.getAllUsers();

        for (User currentUser : usersList) {
            usersListModel.addElement(currentUser.getName());
        }
        this.updateUserView.usersList.setModel(usersListModel);
        this.updateUserView.updateBtn.addActionListener(this);
        this.updateUserView.selectBtn.addActionListener(this);
        this.updateUserView.backBtn.addActionListener(this);
        this.updateUserView.errorLabel.setVisible(false);
        this.updateUserView.succesfulLabel.setVisible(false);
        this.updateUserView.setLocationRelativeTo(null);
    }
}
