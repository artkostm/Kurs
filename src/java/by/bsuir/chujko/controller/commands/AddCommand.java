/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import by.bsuir.chujko.dao.DAOManager;
import by.bsuir.chujko.model.entities.Message;
import by.bsuir.chujko.model.entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Artyom
 */
public class AddCommand implements Command {
    
private static final String TEST = "test";

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mes = request.getParameter(TEST);
        int idMessage = Integer.parseInt(request.getParameter("lastid"));
        User u = (User)request.getSession().getAttribute("user");
        if(mes == null || mes.trim().isEmpty() || u == null){
            return "error";
        }
        DAOManager manager = DAOManager.getInstance();
        Message m_last = manager.getMessageDAO().getEntityById(idMessage);
        m_last.setRead(true);
        manager.getMessageDAO().update(m_last);
        Message m = new Message(0, u.getIdUser(), m_last.getIdSender(), m_last.getIdDialog(), m_last.getIdNote(), mes);
        manager.getMessageDAO().add(m);
//        Dialog d = new Dialog(idNote, 0, u.getIdUser());
//        DAOManager.getInstance().getDialogDAO().add(d);
//        d = DAOManager.getInstance().getDialogDAO().getDialogByNoteId(idNote);
//        Message m = new Message(0, u.getIdUser(), n.getIdSender(), d.getIdDialog(), idNote, mes);
//        DAOManager.getInstance().getMessageDAO().add(m);
        return mes;
    }

    @Override
    public boolean isAjax() {
        return true;
    }
}
