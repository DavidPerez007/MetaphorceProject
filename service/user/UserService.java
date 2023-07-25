package com.metaphorce.inventorymanager.service.user;
import com.metaphorce.inventorymanager.model.User;
import java.util.ArrayList;

public interface UserService {
    public User createUser(String name, String password, boolean isAdmin);
    public User updateUser(int id, String name, String password, boolean isAdmin);
    public User deleteUser(int id);
    public User searchUserByID(int id);
    public ArrayList<User> getAllUsers();
}
