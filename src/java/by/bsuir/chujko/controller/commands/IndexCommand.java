/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import by.bsuir.chujko.dao.DAOManager;
import by.bsuir.chujko.model.entities.Tag;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Artyom
 */
public class IndexCommand implements Command{

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        List<Tag> tags = DAOManager.getInstance().getTagDAO().getLast();
        String email = (String)request.getSession().getAttribute("email");
        request.setAttribute("tags", tags);
        request.setAttribute("email", email);
        return "/index.jsp";
    }

    @Override
    public boolean isAjax() {
        return ajax;
    }
    
    
}
