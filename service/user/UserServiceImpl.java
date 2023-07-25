package com.metaphorce.inventorymanager.service.user;
import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.repository.user.UserRepositoryImpl;
import java.util.ArrayList;

public class UserServiceImpl implements UserService{
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    
    
    @Override
    public User createUser(String name, String password, boolean isAdmin) {
        User user = new User(name, password, isAdmin);
        userRepository.saveUser(user);
        System.out.println("User saved");
        return user;
    }

    @Override
    public User updateUser(int id, String name, String password, boolean isAdmin) {
        User updatedUser = new User(name, password, isAdmin);
        userRepository.updateUser(id, updatedUser);
        return updatedUser;
    }

    @Override
    public User deleteUser(int id) {
        User userToDelete = userRepository.deleteUser(id);
        userRepository.deleteUser(id);
        return userToDelete;
    }

    @Override
    public User searchUserByID(int id) {
        User foundUser = userRepository.getUserByID(id);
        return foundUser;
    }

    @Override
    public User authUser(String name, String password) {
        User user = userRepository.getUserByName(name);
        return user;
    }

    public User searchUserByName(String selectedValue) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList users = this.userRepository.getAllUsers();
        return users;
    }
    
    
    
}
