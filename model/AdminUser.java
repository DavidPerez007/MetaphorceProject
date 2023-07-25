package com.metaphorce.inventorymanager.model;

public class AdminUser extends AbstractUser {
    
    public AdminUser(String name, String password, boolean isAdmin) {
        super(name, password, isAdmin);
    }

    public AdminUser(int id, String name, String password, boolean isAdmin) {
        super(id, name, password, isAdmin);
    }
}
