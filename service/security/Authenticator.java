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

    public Authenticator(UserServiceImpl userService) {
        this.userService = userService;
    }

   public boolean authenticate(String name, String password){
       boolean isAuthenticated = false;
       try{
           User user = userService.authUser(name, password);
           if(user.getPassword().equals(password)) isAuthenticated = true;
           else System.out.println("Error: Password not correct");
       }catch(Exception e){
           e.printStackTrace();
       }
       
       return isAuthenticated;
    }
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}

