/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

/**
 *
 * @author Artyom
 */
public class DBConfig {
    
    public static final String LOGGER_NAME = "by.bsuir.chujko.dao";
    
    public static final String USER 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/config")
                  .getString("database.user");
    
    public static final String PASSWORD
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/config")
                  .getString("database.password");
    
    public static final String URL
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/config")
                  .getString("database.url");
}

