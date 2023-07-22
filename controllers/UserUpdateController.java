package com.metaphorce.inventorymanager.controllers;

import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.UpdateUserView;
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

    public UserUpdateController(UserServiceImpl userService, UpdateUserView updateUserView) {
        this.userService = userService;
        this.updateUserView = updateUserView;
        initUpdateView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.updateUserView.selectBtn) {
            int selectedIndex = this.updateUserView.usersList.getSelectedIndex();
            if (selectedIndex != -1) {
                selectedUser = usersList.get(selectedIndex);
                this.updateUserView.usernameField.setText(selectedUser.getName());
                this.updateUserView.passwordField.setText(selectedUser.getPassword());
            }
        }

        if (e.getSource() == this.updateUserView.updateBtn) {
            System.out.print(selectedUser.getName());
            if (selectedUser != null) {
                String newName = this.updateUserView.usernameField.getText();
                String newPassword = this.updateUserView.passwordField.getText();
                userService.updateUser(selectedUser.getId(), newName, newPassword);
                System.out.println("User updated successfully");
            } else {
                this.updateUserView.errorLabel.setVisible(true);
            }
        }
    }

    public void initUpdateView() {
        this.usersListModel = new DefaultListModel<>();
        this.usersList = this.userService.getAllUsers();

        for (User currentUser : usersList) {
            usersListModel.addElement(currentUser.getName());
        }
        this.updateUserView.usersList.setModel(usersListModel);
        this.updateUserView.updateBtn.addActionListener(this);
        this.updateUserView.selectBtn.addActionListener(this);
        this.updateUserView.errorLabel.setVisible(false);
    }
}