package com.metaphorce.inventorymanager.model;

public class User extends AbstractUser{   
   private int id;
   private String name;
   private String password;
  
   /**
     * Creates a new User object with the provided name, password, and admin status.
     *
     * @param name     The name of the user.
     * @param password The password associated with the user.
     * @param isAdmin  A boolean value indicating if the user has admin
     *                 privileges or not.
     */
    public User(String name, String password, boolean isAdmin) {
        super(name, password, isAdmin);
    }
    
    /**
     * Creates a new User object with the provided ID, name, password, and admin
     * status.
     *
     * @param id       The unique identifier for the user.
     * @param name     The name of the user.
     * @param password The password associated with the user.
     * @param isAdmin  A boolean value that indicates if the user has admin
     *                 privileges or not.
     */
    public User(int id, String name, String password, boolean isAdmin) {
        super(id, name, password, isAdmin);
    }
}
