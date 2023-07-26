package com.metaphorce.inventorymanager.service.user;
import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.repository.user.UserRepositoryImpl;
import java.util.ArrayList;

public class UserServiceImpl implements UserService{
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    
    /**
     * Creates a new user with the provided details and saves it to the repository.
     *
     * @param name     The name of the user to be created.
     * @param password The password of the user.
     * @param isAdmin  A boolean indicating whether the user has admin privileges.
     * @return The User object representing the newly created user.
     */
    @Override
    public User createUser(String name, String password, boolean isAdmin) {
        User user = new User(name, password, isAdmin);
        userRepository.saveUser(user);
        System.out.println("User saved");
        return user;
    }

    /**
     * Updates an existing user with the provided details and saves it to the
     * repository.
     *
     * @param id       The unique ID of the user to be updated.
     * @param name     The new name of the user.
     * @param password The new password of the user.
     * @param isAdmin  A boolean indicating whether the user should have admin
     *                 privileges.
     * @return The User object representing the updated user, or null if the user
     *         was not found.
     */
    @Override
    public User updateUser(int id, String name, String password, boolean isAdmin) {
        User updatedUser = new User(name, password, isAdmin);
        userRepository.updateUser(id, updatedUser);
        return updatedUser;
    }

    /**
     * Deletes a user with the specified ID from the repository.
     *
     * @param id The unique ID of the user to be deleted.
     * @return The User object representing the deleted user, or null if the user
     *         was not found.
     */
    @Override
    public User deleteUser(int id) {
        User userToDelete = userRepository.deleteUser(id);
        userRepository.deleteUser(id);
        return userToDelete;
    }

    /**
     * Searches for a user based on their unique ID in the repository.
     *
     * @param id The unique ID of the user to search for.
     * @return The User object representing the found user, or null if the user was
     *         not found.
     */
    @Override
    public User searchUserByID(int id) {
        User foundUser = userRepository.getUserByID(id);
        return foundUser;
    }

    
    /**
     * Searches for a user based on their name in the repository.
     *
     * @param name The name of the user to search for.
     * @return The User object representing the found user, or null if the user was
     *         not found.
     */
    public User searchUserByName(String name) {
        User user = userRepository.getUserByName(name);
        return user;
    }

    /**
     * Retrieves a list of all users stored in the repository.
     *
     * @return An ArrayList containing User objects representing all users in the
     *         system.
     */
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList users = this.userRepository.getAllUsers();
        return users;
    }
    
    
    
}
