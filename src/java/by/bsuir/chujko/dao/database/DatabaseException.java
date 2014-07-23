/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao.database;

import by.bsuir.chujko.exceptions.TechnicalException;

/**
 *
 * @author Artyom
 */
public class DatabaseException extends TechnicalException{

    public DatabaseException() {
    }
    
    public DatabaseException(String msg) {
        super(msg);
    }
    
    public DatabaseException(String msg, Throwable t) {
        super(msg, t);
    }
}
