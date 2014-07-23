/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import by.bsuir.chujko.dao.DAOManager;
import by.bsuir.chujko.model.entities.Tag;
import by.bsuir.chujko.model.entities.User;
import by.bsuir.chujko.model.entities.security.Security;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Artyom
 */
public class LoginCommand implements Command{

    private static final String PARAM_EMAIL = "email"; 
    private static final String PARAM_PASSWORD  = "password";

    @Override
    public String execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        String page = null;    
        String email = request.getParameter(PARAM_EMAIL); 
        String password = request.getParameter(PARAM_PASSWORD);
        if(email.trim().isEmpty() || password.trim().isEmpty()){
           return "/login_error.jsp";
        }
        Security s = DAOManager.getInstance().getSecurityDAO()
                .getSecurityByEP(email, password);
        if(s == null){
            return "/login_error.jsp";
        }
        User u = DAOManager.getInstance().getUserDAO().getUserBySecurityId(s.getId());
        HttpSession session = request.getSession(true);
        session.setAttribute("user", u);
        session.setAttribute("email", s.getEmail());
        List<Tag> tags = DAOManager.getInstance().getTagDAO().getLast();
        request.setAttribute("tags", tags);
        request.setAttribute("email", s.getEmail());
        page = "/index.jsp";
        return page;
    }
    
    @Override
    public boolean isAjax() {
        return ajax;
    }
}
