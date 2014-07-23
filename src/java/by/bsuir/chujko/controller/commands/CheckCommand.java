/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import by.bsuir.chujko.dao.DAOManager;
import by.bsuir.chujko.model.entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Artyom
 */
public class CheckCommand implements Command{

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        if(u == null){
            return "/error_page.jsp";
        }
        DAOManager manager = DAOManager.getInstance();
        int count = manager.getMessageDAO().getCountById(u.getIdUser());
        return String.valueOf(count);
    }

    @Override
    public boolean isAjax() {
        return true;
    }
    
    
}
