/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.exceptions;

/**
 *
 * @author Artyom
 */
public class LogicalException extends Exception{

    public LogicalException() {
    }
    
    public LogicalException(String msg) {
        super(msg);
    }
    
    public LogicalException(String msg, Throwable t) {
        super(msg, t);
    }
}
