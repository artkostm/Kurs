/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import by.bsuir.chujko.dao.database.ConnectionWrapper;
import by.bsuir.chujko.dao.database.DatabaseException;
import by.bsuir.chujko.model.entities.Message;
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
public class MessageDAO extends AbstractDAO<Message>{

    public MessageDAO() {
        super();
    }

    @Override
    public List<Message> getAll() {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Message> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.MS_GET_ALL);
            if(rs.first()){
                do{
                    int idMessage = rs.getInt("idMessage");
                    String value = rs.getString("value");
                    int idSender = rs.getInt("idSender");
                    int idRecipient = rs.getInt("idRecipient");
                    boolean read = rs.getBoolean("isRead");
                    int idDialog = rs.getInt("idDialog");
                    int idNote = rs.getInt("idNote");
                    Message m = new Message(idMessage, idSender,idRecipient, 
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

    @Override
    public Message getEntityById(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            Message m = new Message();
            rs = con.createStatement().executeQuery(SQLQuery.MS_GET_BY_ID+id);
            if(rs.first()){
                m.setIdMessage(rs.getInt("idMessage"));
                m.setValue(rs.getString("value"));
                m.setIdSender(rs.getInt("idSender"));
                m.setIdRecipient(rs.getInt("idRecipient"));
                m.setRead(rs.getBoolean("isRead"));
                m.setIdDialog(rs.getInt("idDialog"));
                m.setIdNote(rs.getInt("idNote"));
            }
            return m;
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
            ps = con.prepareStatement(SQLQuery.MS_DELETE+id);
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
    public boolean delete(Message entity) {
        return delete(entity.getIdMessage());
    }

    @Override
    public boolean add(Message entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQLQuery.MS_ADD);
            ps.setInt(1, entity.getIdMessage());
            ps.setString(2, entity.getValue());
            ps.setInt(3, entity.getIdSender());
            ps.setBoolean(4, entity.isRead());
            ps.setInt(5, entity.getIdDialog());
            ps.setInt(6, entity.getIdNote());
            ps.setInt(7, entity.getIdRecipient());
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
    public Message update(Message entity) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            ps = con.prepareStatement(SQLQuery.MS_UPDATE+entity.getIdMessage());
            ps.setString(1, entity.getValue());
            ps.setInt(2, entity.getIdSender());
            ps.setBoolean(3, entity.isRead());
            ps.setInt(4, entity.getIdDialog());
            ps.setInt(5, entity.getIdNote());
            ps.setInt(6, entity.getIdRecipient());
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
    
//    public List<Message> getLastByDialog(int idUser) {
//        ResultSet rs = null;
//        ConnectionWrapper con = null;
//        PreparedStatement ps = null;
//        try {
//            con = getPool().takeConnection();
//            List<Message> list = new ArrayList<>();
//            ps = con.prepareStatement(SQLQuery.MS_GET_LAST_BY_D);
//            ps.setInt(1, idUser);
//            rs = ps.executeQuery();
//            if(rs.first()){
//                do{
//                    int idMessage = rs.getInt("idMessage");
//                    String value = rs.getString("value");
//                    int idSender = rs.getInt("idSender");
//                    boolean read = rs.getBoolean("isRead");
//                    int idDialog = rs.getInt("idDialog");
//                    int idNote = rs.getInt("idNote");
//                    Message m = new Message(idMessage, idSender, 
//                            idDialog, idNote, value, read);
//                    list.add(m);
//                    }while(rs.next());
//                }
//            ps.close();
//            return list;
//        } catch (DatabaseException ex) {
//            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
//            return Collections.EMPTY_LIST;
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
//            return Collections.EMPTY_LIST;
//        }finally{
//            try{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(con != null){
//                    getPool().releaseConnection(con);
//                }
//            }catch(SQLException ex){
//                Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
//            }
//        }
//    }
//    
//    public List<Message> getLastByNote(int idUser) {
//        ResultSet rs = null;
//        ConnectionWrapper con = null;
//        PreparedStatement ps = null;
//        try {
//            con = getPool().takeConnection();
//            List<Message> list = new ArrayList<>();
//            ps = con.prepareStatement(SQLQuery.MS_GET_LAST_BY_N);
//            ps.setInt(1, idUser);
//            rs = ps.executeQuery();
//            if(rs.first()){
//                do{
//                    int idMessage = rs.getInt("idMessage");
//                    String value = rs.getString("value");
//                    int idSender = rs.getInt("idSender");
//                    boolean read = rs.getBoolean("isRead");
//                    int idDialog = rs.getInt("idDialog");
//                    int idNote = rs.getInt("idNote");
//                    Message m = new Message(idMessage, idSender, 
//                            idDialog, idNote, value, read);
//                    list.add(m);
//                    }while(rs.next());
//                }
//            ps.close();
//            return list;
//        } catch (DatabaseException ex) {
//            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
//            return Collections.EMPTY_LIST;
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
//            return Collections.EMPTY_LIST;
//        }finally{
//            try{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(con != null){
//                    getPool().releaseConnection(con);
//                }
//            }catch(SQLException ex){
//                Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
//            }
//        }
//    }
//    
//    public List<Message> getUnread(int idUser) {
//        List<Message> list = getLastByDialog(idUser);
//        list.addAll(getLastByNote(idUser));
//        return list;
//    }
//    
//    public int getCountByDialog(int idUser) {
//        ResultSet rs = null;
//        ConnectionWrapper con = null;
//        PreparedStatement ps = null;
//        try {
//            con = getPool().takeConnection();
//            ps = con.prepareStatement(SQLQuery.MS_GET_COUNT_LAST_BY_D);
//            ps.setInt(1, idUser);
//            rs = ps.executeQuery();
//            int count = 0;
//            if(rs.first()){
//                count = rs.getInt("count");
//            }
//            ps.close();
//            return count;
//        } catch (DatabaseException ex) {
//            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
//            return 0;
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
//            return 0;
//        }finally{
//            try{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(con != null){
//                    getPool().releaseConnection(con);
//                }
//            }catch(SQLException ex){
//                Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
//            }
//        }
//    }
//    
//    public int getCountByNote(int idUser) {
//        ResultSet rs = null;
//        ConnectionWrapper con = null;
//        PreparedStatement ps = null;
//        try {
//            con = getPool().takeConnection();
//            ps = con.prepareStatement(SQLQuery.MS_GET_COUNT_LAST_BY_N);
//            ps.setInt(1, idUser);
//            rs = ps.executeQuery();
//            int count = 0;
//            if(rs.first()){
//                count = rs.getInt("count");
//            }
//            ps.close();
//            return count;
//        } catch (DatabaseException ex) {
//            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
//            return 0;
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
//            return 0;
//        }finally{
//            try{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(con != null){
//                    getPool().releaseConnection(con);
//                }
//            }catch(SQLException ex){
//                Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
//            }
//        }
//    }
//    
//    public int getUnreadCount(int idUser) {
//        int count = getCountByDialog(idUser);
//        count += getCountByNote(idUser);
//        return count;
//    }
    
    public int getCountById(int idUser) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            rs = con.createStatement().executeQuery(SQLQuery.MS_GET_COUNT+idUser);
            int count = 0;
            if(rs.first()){
                count = rs.getInt("count");
            }
            return count;
        } catch (DatabaseException ex) {
            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);/////////////////
            return 0;
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
    
    public List<Message> getUnread(int idUser) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        try {
            con = getPool().takeConnection();
            List<Message> list = new ArrayList<>();
            rs = con.createStatement().executeQuery(SQLQuery.MS_GET_UNREAD + idUser);
            if(rs.first()){
                do{
                    int idMessage = rs.getInt("idMessage");
                    String value = rs.getString("value");
                    int idSender = rs.getInt("idSender");
                    boolean read = rs.getBoolean("isRead");
                    int idDialog = rs.getInt("idDialog");
                    int idNote = rs.getInt("idNote");
                    int idRecipient = idUser;
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
    
    public Message getLastUnread(int id) {
        ResultSet rs = null;
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        try {
            con = getPool().takeConnection();
            Message m = new Message();
            ps = con.prepareStatement(SQLQuery.MS_GET_LAST_UNREAD);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.first()){
                m.setIdMessage(rs.getInt("idMessage"));
                m.setValue(rs.getString("value"));
                m.setIdSender(rs.getInt("idSender"));
                m.setIdRecipient(rs.getInt("idRecipient"));
                m.setRead(rs.getBoolean("isRead"));
                m.setIdDialog(rs.getInt("idDialog"));
                m.setIdNote(rs.getInt("idNote"));
            }
            ps.close();
            return m;
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

