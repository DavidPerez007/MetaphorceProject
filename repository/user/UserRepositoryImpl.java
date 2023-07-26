package com.metaphorce.inventorymanager.repository.user;

import com.metaphorce.inventorymanager.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private String dbUrl = "jdbc:postgresql://localhost:5432/db_gorditasSA";
    private String dbUser = "postgres";
    private String dbPassword = "admin";

    /**
     * Retrieves a user from the database by their unique ID.
     *
     * @param id The unique ID of the user to retrieve.
     * @return The User object associated with the given ID, or null if not found.
     */
    @Override
    public User getUserByID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String query = "SELECT * FROM users WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("is_admin");
                user = new User(name, password, isAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

     /**
     * Retrieves a user from the database by their name.
     *
     * @param name The name of the user to retrieve.
     * @return The User object associated with the given name, or null if not found.
     */
    @Override
    public User getUserByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String query = "SELECT * FROM users WHERE name = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, name);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("is_admin");

                user = new User(name, password, isAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y liberar los recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

     /**
     * Retrieves a list of all users from the database.
     *
     * @return An ArrayList containing all the User objects stored in the database.
     */
    @Override
    public ArrayList getAllUsers() {
        String query = "SELECT * FROM users";
        ResultSet result = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement statement = connection.prepareStatement(query);

            result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String password = result.getString("password");
                boolean isAdmin = result.getBoolean("is_admin");
                User user = new User(id, name, password, isAdmin);
                users.add(user);
        }

            result.close();
            statement.close();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

     /**
     * Creates a new user in the database.
     *
     * @param user The user to create.
     * @return The User object that was created.
     */
    @Override
    public User saveUser(User user) {
        String query = "INSERT INTO users (name, password, is_admin) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isAdmin());
            

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("User saved successfully.");
            } else {
                System.out.println("Failed to save user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Deletes a user from the database.
     *
     * @param id The id of the user to delete.
     * @return The User object that was deleted.
     */
    @Override
    public User deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        User userToDelete = null;
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            userToDelete = getUserByID(id);

            if (rowsAffected == 1) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Failed to delete user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userToDelete;
    }

     /**
     * Updates a user in the database.
     * @param id the id of the user to update
     * @param user The information of user to update.
     * @return The User object that was updated.
     */
    @Override
    public User updateUser(int id, User user
    ) {
        User userToUpdate = getUserByID(id);

        if (userToUpdate != null) {
            String query = "UPDATE users SET name = ?, password = ?, is_admin = ? WHERE id = ?";

            try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.setBoolean(3, (boolean)user.isAdmin());
                statement.setInt(4, id);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    userToUpdate.setName(user.getName());
                    userToUpdate.setPassword(user.getPassword());
                    return userToUpdate;
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar el usuario: " + e.getMessage());
            }
        }

        throw new IllegalArgumentException("No se pudo actualizar el usuario con el ID proporcionado");
    }
}
