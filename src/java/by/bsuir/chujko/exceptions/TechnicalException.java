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
public class TechnicalException extends Exception{

    public TechnicalException() {
    }
    
    public TechnicalException(String msg) {
        super(msg);
    }
    
    public TechnicalException(String msg, Throwable t) {
        super(msg, t);
    }
}
