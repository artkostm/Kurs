/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao.database;

/**
 *
 * @author Artyom
 */
public final class LoggingMessageConstants {

    private LoggingMessageConstants() {
    }
    
    
    public static final String LOGGER_NAME = "by.epam.finalproject.dao";
    
    static final String SUCCESS_POOL_CLOSE = "Connection pool is successfully closed.";
    static final String SUCCESS_POOL_RELEASE_CONN = "SUCCESS_POOL_RELEASE_CONN";
    static final String SUCCESS_POOL_TAKE_CONN = "SUCCESS_POOL_TAKE_CONN";
    static final String POOL_CONNECT_ERROR = "POOL_CONNECT_ERROR";
    static final String POOL_INIT_ERROR = "POOL_INIT_ERROR";
    static final String SUCCESS_POOL_INIT = "SUCCESS_POOL_INIT";
}
