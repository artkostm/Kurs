/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import by.bsuir.chujko.dao.DAOManager;
import by.bsuir.chujko.model.entities.Dialog;
import by.bsuir.chujko.model.entities.Message;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Artyom
 */
public class DeleteCommand implements Command{

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //command=delete&lastid=
        int idMessage = Integer.parseInt(request.getParameter("lastid"));
        DAOManager manager = DAOManager.getInstance();
        Message m = manager.getMessageDAO().getEntityById(idMessage);
        Dialog d = manager.getDialogDAO().getEntityById(m.getIdDialog());
        manager.getNoteDAO().delete(m.getIdNote());
        for(Message mm : d.getMessages()){
            manager.getMessageDAO().delete(mm);
        }
        return "ok";
    }

    @Override
    public boolean isAjax() {
        return true;
    }
    
}
