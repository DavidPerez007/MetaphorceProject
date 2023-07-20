package com.metaphorce.inventorymanager.service.user;
import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.repository.user.UserRepositoryImpl;
import com.metaphorce.inventorymanager.service.security.Authenticator;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class UserServiceImpl implements UserService{
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    
    
    @Override
    public User createUser(String name, String password) {
        User user = new User(name, password);
        userRepository.saveUser(user);
        System.out.println("User saved");
        return user;
    }

    @Override
    public User updateUser(int id, String name, String password) {
        User updatedUser = new User(name, password);
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
    public DefaultListModel<User> getAllUsers() {
        DefaultListModel users = new DefaultListModel();
        try{
            ResultSet resultSet = userRepository.getAllUsers();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                User user = new User(id, name, password);
                users.addElement(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return users;
    }
    
    
    
}
