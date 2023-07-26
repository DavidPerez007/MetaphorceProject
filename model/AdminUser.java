package com.metaphorce.inventorymanager.model;

public class AdminUser extends AbstractUser {
    
    /**
     * Creates a new AdminUser object with the provided name, password, and admin
     * status.
     *
     * @param name     The name of the admin user.
     * @param password The password associated with the admin user.
     * @param isAdmin  A boolean value indicating whether the user is an admin or
     *                 not.
     */
    public AdminUser(String name, String password, boolean isAdmin) {
        super(name, password, isAdmin);
    }

    /**
     * Creates a new AdminUser object with the provided id, name, password, and
     * admin status.
     *
     * @param id       The id of the admin user.
     * @param name     The name of the admin user.
     * @param password The password associated with the admin user.
     * @param isAdmin  A boolean value indicating whether the user is an admin or
     *                 not.
     */
    public AdminUser(int id, String name, String password, boolean isAdmin) {
        super(id, name, password, isAdmin);
    }
}
