/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import by.bsuir.chujko.dao.database.ConnectionWrapper;
import by.bsuir.chujko.dao.database.DatabaseException;
import by.bsuir.chujko.model.entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Artyom
 */
public class UserDAO extends AbstractDAO<User>{

    public UserDAO() {
        super();
    }

    @Override
    public List<User> getAll() {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<User> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.U_GET_ALL);
            if(rs.first()){
                do{
                    int idUser = rs.getInt("idUser");
                    int idSecurity = rs.getInt("idSecurity");
                    User u = new User(idUser, idSecurity);
                    list.add(u);
                    }while(rs.next());
                }
            return list;
        } catch (DatabaseException ex) {
            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
            return Collections.EMPTY_LIST;
        } catch (SQLException ex) {
            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
            return Collections.EMPTY_LIST;
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(con != null){
                    getPool().releaseConnection(con);
                }
            }catch(SQLException ex){
                Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
            }
        }
    }

    @Override
    public User getEntityById(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            User u = new User();
            rs = con.createStatement().executeQuery(SQLQuery.U_GET_BY_ID+id);
            if(rs.first()){
                u.setIdUser(id);
                u.setIdSecurity(rs.getInt("idSecurity"));
            }
            return u;
        } catch (DatabaseException ex) {
            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
            return null;
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(con != null){
                    getPool().releaseConnection(con);
                }
            }catch(SQLException ex){
                Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
            }
        }
    }

    @Override
    public boolean delete(int id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            ps = con.prepareStatement(SQLQuery.U_DELETE+id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } catch (DatabaseException ex) {
            return false;
        }finally{
            if(ps != null){
                close(ps);
            }
            if(con != null){
                getPool().releaseConnection(con);
            }
        }
    }

    @Override
    public boolean delete(User entity) {
        return delete(entity.getIdUser());
    }

    @Override
    public boolean add(User entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQLQuery.U_ADD);
            ps.setInt(1, 0);
            ps.setInt(2, entity.getIdSecurity());
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException ex) {
            return false;
        } catch (DatabaseException ex) {
            return false;
        }finally{
            if(ps != null){
                close(ps);
            }
            if(con != null){
                getPool().releaseConnection(con);
            }
        }
    }

    @Override
    public User update(User entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            ps = con.prepareStatement(SQLQuery.U_UPDATE+entity.getIdUser());
            ps.setInt(1, entity.getIdSecurity());
            ps.executeUpdate();
            return entity;
        } catch (SQLException ex) {
            return null;
        } catch (DatabaseException ex) {
            return null;
        }finally{
            if(ps != null){
                close(ps);
            }
            if(con != null){
                getPool().releaseConnection(con);
            }
        }
    }
    
    public User getUserBySecurityId(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            User u = new User();
            rs = con.createStatement().executeQuery(SQLQuery.U_GET_BY_SEC_ID+id);
            if(rs.first()){
                u.setIdUser(rs.getInt("idUser"));
                u.setIdSecurity(id);
            }
            return u;
        } catch (DatabaseException ex) {
            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
            return null;
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(con != null){
                    getPool().releaseConnection(con);
                }
            }catch(SQLException ex){
                Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
            }
        }
    }
}
