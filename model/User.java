package com.metaphorce.inventorymanager.model;

public class User extends AbstractUser{   
   private int id;
   private String name;
   private String password;
  
    public User(String name, String password, boolean isAdmin) {
        super(name, password, isAdmin);
    }
    
    public User(int id, String name, String password, boolean isAdmin) {
        super(id, name, password, isAdmin);
    }
}
