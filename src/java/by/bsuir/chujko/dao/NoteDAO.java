/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import by.bsuir.chujko.dao.database.ConnectionWrapper;
import by.bsuir.chujko.dao.database.DatabaseException;
import by.bsuir.chujko.model.entities.Note;
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
public class NoteDAO extends AbstractDAO<Note>{

    public NoteDAO() {
        super();
    }

    @Override
    public List<Note> getAll() {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Note> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.N_GET_ALL);
            if(rs.first()){
                do{
                    int idMessage = rs.getInt("idNote");
                    String value = rs.getString("value");
                    int idSender = rs.getInt("idSender");
                    int idDialog = rs.getInt("idTag");
                    boolean answered = rs.getBoolean("isAnswered");
                    Note n = new Note(idSender, idSender, 
                            idDialog, value, answered);
                    list.add(n);
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
    public Note getEntityById(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Note n = null;
            rs = con.createStatement().executeQuery(SQLQuery.N_GET_BY_ID+id);
            if(rs.first()){
                n = new Note();
                n.setValue(rs.getString("value"));
                n.setIdSender(rs.getInt("idSender"));
                n.setAnswered(rs.getBoolean("isAnswered"));
                n.setIdTag(rs.getInt("idTag"));
                n.setIdNote(rs.getInt("idNote"));
            }
            return n;
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
            ps = con.prepareStatement(SQLQuery.N_DELETE+id);
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
    public boolean delete(Note entity) {
        return delete(entity.getIdNote());
    }

    @Override
    public boolean add(Note entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQLQuery.N_ADD);
            ps.setInt(1, entity.getIdNote());
            ps.setString(2, entity.getValue());
            ps.setInt(3, entity.getIdSender());
            ps.setInt(4, entity.getIdTag());
            ps.setBoolean(5, entity.isAnswered());
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
    public Note update(Note entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            ps = con.prepareStatement(SQLQuery.N_UPDATE+entity.getIdNote());
            ps.setString(1, entity.getValue());
            ps.setInt(2, entity.getIdSender());
            ps.setInt(3, entity.getIdTag());
            ps.setBoolean(4, entity.isAnswered());
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
    
    public Tag getTagById(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Tag t = new Tag();
            rs = con.createStatement().executeQuery(SQLQuery.N_GET_TAG_BY_ID+id);
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
    
    //sql.note.get_one_last
    public Note getOneLast(){
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Note n = null; 
            rs = con.createStatement().executeQuery(SQLQuery.N_GET_ONE_LAST);
            if(rs.first()){
                n = new Note();
                n.setIdNote(rs.getInt("idNote"));
                n.setValue(rs.getString("value"));
                n.setIdSender(rs.getInt("idSender"));
                n.setAnswered(rs.getBoolean("isAnswered"));
                n.setIdTag(rs.getInt("idTag"));
            }
            return n;
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
    
    public Note getOneLast(int idSender, int idTag){
        ResultSet rs = null;
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            Note n = null; 
            ps = con.prepareStatement(SQLQuery.N_GET_ONE_LAST_NEW);
            ps.setInt(1, idSender);
            ps.setInt(2, idTag);
            rs = ps.executeQuery();
            if(rs.first()){
                n = new Note();
                n.setIdNote(rs.getInt("idNote"));
                n.setValue(rs.getString("value"));
                n.setIdSender(rs.getInt("idSender"));
                n.setAnswered(rs.getBoolean("isAnswered"));
                n.setIdTag(rs.getInt("idTag"));
            }
            return n;
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
