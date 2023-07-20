package com.metaphorce.inventorymanager.service.user;
import com.metaphorce.inventorymanager.model.User;
import javax.swing.DefaultListModel;

public interface UserService {
    public User createUser(String name, String password);
    public User updateUser(int id, String name, String password);
    public User deleteUser(int id);
    public User searchUserByID(int id);
    public DefaultListModel<User> getAllUsers();
    public User authUser(String name, String password);
}
