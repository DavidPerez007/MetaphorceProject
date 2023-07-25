package com.metaphorce.inventorymanager.repository.ingredient;

import com.metaphorce.inventorymanager.model.Ingredient;
import com.metaphorce.inventorymanager.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryRepositoryImpl implements InventoryRepository {

    private final String dbUrl = "jdbc:postgresql://localhost:5432/db_gorditasSA";
    private final String dbUser = "postgres";
    private final String dbPassword = "admin";

    @Override
    public Ingredient getIngredientByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Ingredient ingredient = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String query = "SELECT * FROM ingredients WHERE ingredient = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, name);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String ingredientName = resultSet.getString("ingredient");
                int quantity = resultSet.getInt("quantity");

                ingredient = new Ingredient(ingredientName, quantity);
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

        return ingredient;
    }

    @Override
    public void addIngredient(String ingredientName, int quantity) {
        String query = "UPDATE ingredients SET quantity = ? WHERE ingredient = ?";
        Ingredient ingr = this.getIngredientByName(ingredientName);
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(2, ingredientName);
            statement.setInt(1, ingr.getIngredientQuantity() + quantity);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Ingredient saved successfully.");
            } else {
                System.out.println("Failed to save ingredient.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void substractIngredient(String ingredientName, int quantity) throws Exception{
        String query = "UPDATE ingredients SET quantity = ? WHERE ingredient = ?";
        Ingredient ingr = this.getIngredientByName(ingredientName);
        if (ingr.getIngredientQuantity() >= quantity) {
            try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(2, ingredientName);
                statement.setInt(1, ingr.getIngredientQuantity() - quantity);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 1) {
                    System.out.println("Ingredient sold successfully.");
                } else {
                    System.out.println("Failed to sell ingredient.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            throw new Exception("Error: insuficient ingredients");
        }

    }

    @Override
    public void createIngredient(Ingredient ingredient) {
        String query = "INSERT INTO ingredients (ingredient, quantity) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ingredient.getIngredientName());
            statement.setInt(2, ingredient.getIngredientQuantity());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Ingredient saved successfully.");
            } else {
                System.out.println("Failed to save ingredient.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getAllIngredients() {
        String query = "SELECT * FROM ingredients";
        ResultSet result = null;
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement statement = connection.prepareStatement(query);

            result = statement.executeQuery();
            while (result.next()) {
                String ingrName = result.getString("ingredient");
                int quantity = result.getInt("quantity");
                Ingredient ingredient = new Ingredient(ingrName, quantity);
                ingredients.add(ingredient);
            }

            result.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredients;
    }

}
