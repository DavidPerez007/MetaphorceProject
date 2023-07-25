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

    @Override
    public User updateUser(int id, User user
    ) {
        User userToUpdate = getUserByID(id);

        if (userToUpdate != null) {
            String query = "UPDATE users SET name = ?, password = ? WHERE id = ?";

            try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.setInt(3, id);

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
