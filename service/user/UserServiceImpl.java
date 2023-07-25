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

    

    public User searchUserByName(String name) {
        User user = userRepository.getUserByName(name);
        return user;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList users = this.userRepository.getAllUsers();
        return users;
    }
    
    
    
}
