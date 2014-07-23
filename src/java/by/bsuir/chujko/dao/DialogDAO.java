/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import by.bsuir.chujko.dao.database.ConnectionWrapper;
import by.bsuir.chujko.dao.database.DatabaseException;
import by.bsuir.chujko.model.entities.Dialog;
import by.bsuir.chujko.model.entities.Message;
import by.bsuir.chujko.model.entities.Note;
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
public class DialogDAO extends AbstractDAO<Dialog>{

    public DialogDAO() {
        super();
    }

    @Override
    public List<Dialog> getAll() {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Dialog> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.D_GET_ALL);
            if(rs.first()){
                do{
                    int idDialog = rs.getInt("idDialog");
                    int idNote = rs.getInt("idNote");
                    int idAnswered = rs.getInt("idAnswered");
                    Dialog d = new Dialog(idNote, idDialog, idAnswered);
                    d.setNote(getNoteById(idNote));
                    d.setMessages(getAllMesByDialogId(idDialog));
                    list.add(d);
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
    public Dialog getEntityById(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Dialog d = new Dialog();
            rs = con.createStatement().executeQuery(SQLQuery.D_GET_BY_ID+id);
            if(rs.first()){
                d.setIdDialog(id);
                int idNote;
                d.setIdNote(idNote = rs.getInt("idNote"));
                d.setIdAnsvered(rs.getInt("idAnswered"));
                d.setNote(getNoteById(idNote));
                d.setMessages(getAllMesByDialogId(id));
            }
            return d;
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
            ps = con.prepareStatement(SQLQuery.D_DELETE+id);
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
    public boolean delete(Dialog entity) {
        return delete(entity.getIdDialog());
    }

    @Override
    public boolean add(Dialog entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQLQuery.D_ADD);
            ps.setInt(1, entity.getIdNote());
            ps.setInt(2, 0);
            ps.setInt(3, entity.getIdAnsvered());
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
    public Dialog update(Dialog entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            ps = con.prepareStatement(SQLQuery.D_UPDATE+entity.getIdDialog());
            ps.setInt(1, entity.getIdNote());
            ps.setInt(2, entity.getIdAnsvered());
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
    
    public List<Message> getAllMesByDialogId(int id){
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Message> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.D_ALL_MS_BY_DIA_ID+id);
            if(rs.first()){
                do{
                    int idMessage = rs.getInt("idMessage");
                    int idRecipient = rs.getInt("idRecipient");
                    String value = rs.getString("value");
                    int idSender = rs.getInt("idSender");
                    boolean read = rs.getBoolean("isRead");
                    int idDialog = rs.getInt("idDialog");
                    int idNote = rs.getInt("idNote");
                    Message m = new Message(idMessage, idSender, idRecipient,
                            idDialog, idNote, value, read);
                    list.add(m);
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
    
    public List<Message> getAllMesByNoteId(int id){
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Message> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.D_ALL_MS_BY_NOTE_ID+id);
            if(rs.first()){
                do{
                    int idMessage = rs.getInt("idMessage");
                    int idRecipient = rs.getInt("idRecipient");
                    String value = rs.getString("value");
                    int idSender = rs.getInt("idSender");
                    boolean read = rs.getBoolean("isRead");
                    int idDialog = rs.getInt("idDialog");
                    int idNote = rs.getInt("idNote");
                    Message m = new Message(idMessage, idSender, idRecipient,
                            idDialog, idNote, value, read);
                    list.add(m);
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
    
    public Note getNoteById(int id){
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Note n = null;
            rs = con.createStatement().executeQuery(SQLQuery.D_GET_NOTE_BY_ID+id);
            if(rs.first()){
                n = new Note();
                n.setIdNote(id);
                n.setValue(rs.getString("value"));
                n.setIdSender(rs.getInt("idSender"));
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
    
    public Dialog getDialogByNoteId(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Dialog d = null;
            rs = con.createStatement().executeQuery(SQLQuery.D_GET_BY_NOTE_ID+id);
            if(rs.first()){
                d = new Dialog();
                d.setIdDialog(rs.getInt("idDialog"));
                d.setIdNote(rs.getInt("idNote"));
                d.setIdAnsvered(rs.getInt("idAnswered"));
                d.setNote(getNoteById(d.getIdDialog()));
                d.setMessages(getAllMesByDialogId(d.getIdDialog()));
            }
            return d;
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
