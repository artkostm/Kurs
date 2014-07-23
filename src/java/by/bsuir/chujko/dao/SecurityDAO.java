/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import by.bsuir.chujko.dao.database.ConnectionWrapper;
import by.bsuir.chujko.dao.database.DatabaseException;
import by.bsuir.chujko.model.entities.security.Key;
import by.bsuir.chujko.model.entities.security.Security;
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
public class SecurityDAO extends AbstractDAO<Security>{

    public SecurityDAO() {
        super();
    }

    @Override
    public List<Security> getAll() {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Security> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.S_GET_ALL);
            if(rs.first()){
                do{
                    int id = rs.getInt("idSecurity");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    Security s = new Security(email, password, id);
                    list.add(s);
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
    public Security getEntityById(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Security s = new Security();
            rs = con.createStatement().executeQuery(SQLQuery.S_GET_BY_ID+id);
            if(rs.first()){
                s.setId(id);
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("email"));
            }
            return s;
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
            ps = con.prepareStatement(SQLQuery.S_DELETE+id);
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
    public boolean delete(Security entity) {
        return delete(entity.getId());
    }

    @Override
    public boolean add(Security entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQLQuery.S_ADD);
            ps.setInt(1, 0);
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPassword());
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
    public Security update(Security entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            ps = con.prepareStatement(SQLQuery.S_UPDATE+entity.getId());
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
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
    
    public Security getSecurityByEP(String email, String password){
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        Security s = null;
        try {
            con = getPool().takeConnection();
            ps = con.prepareStatement(SQLQuery.S_GET_BY_EP);
            ps.setString(1, password);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if(rs.first()){
                s = new Security();
                s.setId(rs.getInt("idSecurity"));
                s.setPassword(password);
                s.setEmail(email);
            }
            rs.close();
            return s;
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
    
    public Key getKey() {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Security s = new Security();
            rs = con.createStatement().executeQuery(SQLQuery.S_GET_KEY);
            Key k = new Key();
            if(rs.first()){
                k.add(rs.getInt("b1"));
                k.add(rs.getInt("b2"));
                k.add(rs.getInt("b3"));
                k.add(rs.getInt("b4"));
                k.add(rs.getInt("b5"));
                k.add(rs.getInt("b6"));
                k.add(rs.getInt("b7"));
                k.add(rs.getInt("b8"));
            }
            return k;
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
