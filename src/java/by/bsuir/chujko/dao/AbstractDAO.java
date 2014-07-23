/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import by.bsuir.chujko.dao.database.ConnectionPool;
import by.bsuir.chujko.dao.database.ConnectionWrapper;
import by.bsuir.chujko.dao.database.DatabaseException;
import by.bsuir.chujko.dao.database.LoggingMessageConstants;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

/**
 *
 * @author Artyom
 * @param <T> 
 */
public abstract class AbstractDAO<T> {
    
    private ConnectionPool pool = ConnectionPool.getInstance();
    private final ReentrantLock lock = new ReentrantLock();

    public AbstractDAO() {
        if(!pool.isInit()){
            lock.tryLock();
            try{
                pool = ConnectionPool.getInstance();
                pool.setUrl(DBConfig.URL);
                pool.setPassword(DBConfig.PASSWORD);
                pool.setUsername(DBConfig.USER);
                pool.setPoolSize(20);
                try {
                    pool.init();
                } catch (DatabaseException ex) {
                    Logger.getLogger(LoggingMessageConstants.LOGGER_NAME).error(ex, ex);////////
                }
            }finally{
                lock.unlock();
            }
        }
    }
    
    

    public void setPool(ConnectionPool pool) {
        this.pool = pool;
    }

    public ConnectionPool getPool() {
        return pool;
    }
    
    public ConnectionWrapper getConnection() throws DatabaseException{
        return pool.takeConnection();
    }
    
    public void returnConnection(ConnectionWrapper connection) {
        pool.releaseConnection(connection);
    }
    
    public void close(Statement s){
        if(s != null){
            try {
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoggingMessageConstants.LOGGER_NAME).error(ex, ex);////////
            }
        }
    }
    
    public void close(PreparedStatement s){
        if(s != null){
            try {
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoggingMessageConstants.LOGGER_NAME).error(ex, ex);////////
            }
        }
    }
    
    public void close(ConnectionPool pool){
        if(pool != null){
            pool.closePool();
        }
    }
    
    public abstract List<T> getAll();
    public abstract T getEntityById(int id);
    public abstract boolean delete(int id);
    public abstract boolean delete(T entity);
    public abstract boolean add(T entity);
    public abstract T update(T entity);
}
