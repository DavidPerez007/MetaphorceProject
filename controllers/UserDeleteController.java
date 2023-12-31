package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.DeleteUserView;
import com.metaphorce.inventorymanager.views.LogInView;
import com.metaphorce.inventorymanager.views.UserManagementView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class UserDeleteController implements ActionListener {

    private UserServiceImpl userService;
    private DeleteUserView deleteUserView;
    private User selectedUser;
    private DefaultListModel<String> usersListModel;
    private ArrayList<User> usersList;

    /**
     * Creates a new UserDeleteController with the given parameters.
     *
     * @param userService
     * @param deleteUserView
     */
    public UserDeleteController(UserServiceImpl userService, DeleteUserView deleteUserView) {
        this.userService = userService;
        this.deleteUserView = deleteUserView;
        initDeleteView();
    }

    /**
     * Handles user actions performed on the inventory view's buttons
     *
     * @param e The ActionEvent triggered by user interaction.
     */
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
        if (e.getSource() == this.deleteUserView.backBtn) {
            UserManagementView userManagementView = new UserManagementView();
            UserManagementController userController = new UserManagementController(this.userService, userManagementView);
            this.deleteUserView.dispose();
            userManagementView.setVisible(true);
        }
    }

    /**
     * initializes the view and adds action listeners to its components.
     */
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
        this.deleteUserView.userToDeleteLabel.setVisible(false);
        this.deleteUserView.setLocationRelativeTo(null);
    }

}
