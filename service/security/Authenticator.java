package com.metaphorce.inventorymanager.service.security;
import com.metaphorce.inventorymanager.model.User;
import com.metaphorce.inventorymanager.repository.user.UserRepositoryImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import com.metaphorce.inventorymanager.service.user.UserServiceImpl;

public class Authenticator {
    private final String secretKey = "helpme";
    private final long expirationTime = 20000;
    private UserServiceImpl userService= new UserServiceImpl();

    /**
     * Constructs an Authenticator with the provided UserServiceImpl instance.
     *
     * @param userService The UserServiceImpl instance to be used for user
     *                    retrieval.
     */
    public Authenticator(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Verifies if the user is registered into the DB system, if so,
     * will return a true boolean, otherwise, false.
     *
     * @param name The user to verify identity
     * @param password The password of the user
     * @return boolean that grants or denies access permission 
     */
   public boolean authenticate(String name, String password){
       boolean isAuthenticated = false;
       try{
           User user = userService.searchUserByName(name);
           if(user.getPassword().equals(password)) isAuthenticated = true;
           else System.out.println("Error: Password not correct");
       }catch(Exception e){
           e.printStackTrace();
       }
       
       return isAuthenticated;
    }
    
}

