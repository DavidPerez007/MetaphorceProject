package com.metaphorce.inventorymanager.repository.user;

import com.metaphorce.inventorymanager.model.User;
import java.util.ArrayList;

public interface UserRepository {
    public User getUserByID(int id);
    public User getUserByName(String name);
    public ArrayList getAllUsers();
    public User saveUser(User user);
    public User deleteUser(int id);
    public User updateUser(int id, User user);
    
}
