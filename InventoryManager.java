package com.metaphorce.inventorymanager;

import com.metaphorce.inventorymanager.controllers.LogInController;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;
import com.metaphorce.inventorymanager.views.LogInView;

public class InventoryManager {

    public static void main(String[] args) {
        LogInView logInView = new LogInView();
        UserServiceImpl userService = new UserServiceImpl();
        LogInController controller = new LogInController(userService, logInView);
        logInView.setVisible(true);
    }
}
