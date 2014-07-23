/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import by.bsuir.chujko.dao.DAOManager;
import by.bsuir.chujko.model.entities.Dialog;
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
public class NewMessageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        if(u == null){
            return "/login.jsp";
        }
        Message m = DAOManager.getInstance().getMessageDAO().getLastUnread(u.getIdUser());
        if(m == null){
            return "/error_page.jsp";
        }
        Dialog d = DAOManager.getInstance().getDialogDAO().getEntityById(m.getIdDialog());
        request.setAttribute("lastMesID", m.getIdMessage());
        request.setAttribute("note", d.getNote());
        request.setAttribute("dialog", d);
        return "/dialog.jsp";
    }

    @Override
    public boolean isAjax() {
        return ajax;
    }
}
