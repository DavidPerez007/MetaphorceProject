package com.metaphorce.inventorymanager.model;

public abstract class AbstractUser {
    private int id;
    private String name;
    private String password;
    private boolean isAdmin;
    
    /**
     * Creates a new AbstractUser with the given parameters.
     * @param name 
     * @param password
     * @param isAdmin
     */
    public AbstractUser(String name, String password, boolean isAdmin) {
        this.setName(name);
        this.setPassword(password);
        this.setAdmin(isAdmin);
        
    }
    
    /**
     * Creates a new AbstractUser with the given parameters.
     * @param id
     * @param name
     * @param password
     * @param isAdmin
     */
    public AbstractUser(int id, String name, String password, boolean isAdmin) {
        this.id = id;
        this.setName(name);
        this.setPassword(password);
        this.setAdmin(isAdmin);
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
