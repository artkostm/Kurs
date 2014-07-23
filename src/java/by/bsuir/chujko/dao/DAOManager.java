/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Artyom
 */
public class DAOManager {
    
    private static final ReentrantLock lock = new ReentrantLock();
    private static DAOManager instance;
    
    private TagDAO t_dao;
    private SecurityDAO s_dao;
    private DialogDAO d_dao;
    private MessageDAO m_dao;
    private NoteDAO n_dao;
    private UserDAO u_dao;

    private DAOManager() {
    }
    
    
    public static DAOManager getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new DAOManager();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    
    public UserDAO getUserDAO(){
        if(u_dao == null){
            u_dao = new UserDAO();
        }
        return u_dao;
    }
    
    public NoteDAO getNoteDAO(){
        if(n_dao == null){
            n_dao = new NoteDAO();
        }
        return n_dao;
    }
    
    public DialogDAO getDialogDAO(){
        if(d_dao == null){
            d_dao = new DialogDAO();
        }
        return d_dao;
    }
    
    public TagDAO getTagDAO(){
        if(t_dao == null){
            t_dao = new TagDAO();
        }
        return t_dao;
    }
    
    public SecurityDAO getSecurityDAO(){
        if(s_dao == null){
            s_dao = new SecurityDAO();
        }
        return s_dao;
    }
    
    public MessageDAO getMessageDAO(){
        if(m_dao == null){
            m_dao = new MessageDAO();
        }
        return m_dao;
    }
   
    public void close(){
        if(d_dao != null){
            d_dao.close(d_dao.getPool());
        }else  if(t_dao != null){
            t_dao.close(t_dao.getPool());
        }else  if(s_dao != null){
            s_dao.close(s_dao.getPool());
        }else if(m_dao != null){
            m_dao.close(m_dao.getPool());
        } if(u_dao != null){
            u_dao.close(u_dao.getPool());
        }else if(n_dao != null){
            n_dao.close(n_dao.getPool());
        }
    }
    
}