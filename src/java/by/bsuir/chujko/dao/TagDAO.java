/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import by.bsuir.chujko.dao.database.ConnectionWrapper;
import by.bsuir.chujko.dao.database.DatabaseException;
import by.bsuir.chujko.model.entities.Tag;
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
public class TagDAO extends AbstractDAO<Tag>{

    public TagDAO() {
        super();
    }

    @Override
    public List<Tag> getAll() {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Tag> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.T_GET_ALL);
            if(rs.first()){
                do{
                    int idDialog = rs.getInt("idTag");
                    String value = rs.getString("value");
                    boolean emty = rs.getBoolean("isEmpty");
                    Tag t = new Tag(idDialog, value, emty);
                    list.add(t);
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
    public Tag getEntityById(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Tag t = new Tag();
            rs = con.createStatement().executeQuery(SQLQuery.T_GET_BY_ID+id);
            if(rs.first()){
                t.setIdTag(id);
                t.setValue(rs.getString("value"));
                t.setEmpty(rs.getBoolean("isEmty"));
            }
            return t;
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
            ps = con.prepareStatement(SQLQuery.T_DELETE+id);
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
    public boolean delete(Tag entity) {
        return delete(entity.getIdTag());
    }

    @Override
    public boolean add(Tag entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        if(getTagByValue(entity.getValue()) != null){
            return false;
        }
        try {
            con = getPool().takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQLQuery.T_ADD);
            ps.setInt(1, 0);
            ps.setString(2, entity.getValue());
            ps.setBoolean(3, entity.isEmpty());
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
    public Tag update(Tag entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            ps = con.prepareStatement(SQLQuery.T_UPDATE+entity.getIdTag());
            ps.setString(1, entity.getValue());
            ps.setBoolean(2, entity.isEmpty());
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
    
    public Tag getTagByValue(String value){
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Tag t = null;
            PreparedStatement ps = con.prepareStatement(SQLQuery.T_GET_BY_VAL);
            ps.setString(1, value.toLowerCase().trim());
            rs = ps.executeQuery();
            if(rs.first()){
                t = new Tag();
                t.setIdTag(rs.getInt("idTag"));
                t.setValue(value);
                t.setEmpty(rs.getBoolean("isEmpty"));
            }
            ps.close();
            return t;
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
    
    public List<Tag> getLast() {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Tag> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.T_GET_LAST);
            if(rs.first()){
                do{
                    int idDialog = rs.getInt("idTag");
                    String value = rs.getString("value");
                    boolean emty = rs.getBoolean("isEmpty");
                    Tag t = new Tag(idDialog, value, emty);
                    list.add(t);
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
}
