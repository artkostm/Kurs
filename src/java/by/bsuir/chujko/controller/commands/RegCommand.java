/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import by.bsuir.chujko.ProjectProps;
import by.bsuir.chujko.dao.DAOManager;
import by.bsuir.chujko.model.entities.Tag;
import by.bsuir.chujko.model.entities.User;
import by.bsuir.chujko.model.entities.security.Security;
import by.bsuir.chujko.model.entities.security.StringCrypter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Artyom
 */
public class RegCommand implements Command{
    
    private static final String PARAM_EMAIL = "email"; 
    private static final String PARAM_PASSWORD  = "password";
    private static final String PARAM_PASSWORD1  = "password1";


    @Override
    public String execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        String page = null;    
        String email = request.getParameter(PARAM_EMAIL); 
        String password = request.getParameter(PARAM_PASSWORD);
        String password1 = request.getParameter(PARAM_PASSWORD1);
        if(email.trim().isEmpty() || password.trim().isEmpty()
                || password1.trim().isEmpty() || !password.equals(password1) 
                || checkEmail(email)){
           return "/login_error.jsp";
        }
        Security s;
        s = DAOManager.getInstance().getSecurityDAO()
                .getSecurityByEP(email, password);
        if(s != null){
            return "/login_error.jsp";
        }
        //Key k = DAOManager.getInstance().getSecurityDAO().getKey();
        StringCrypter sc = new StringCrypter();
        s = new Security(email, password, 0);
        String sid = sc.encrypt(email.concat(password1));
        Cookie c = new Cookie("SID", sid);
        c.setMaxAge(180);
        response.addCookie(c);
        DAOManager.getInstance().getSecurityDAO().add(s);
        s = DAOManager.getInstance().getSecurityDAO()
                .getSecurityByEP(email, password);
        User u = new User(0, s.getId());
        DAOManager.getInstance().getUserDAO().add(u);
        u = DAOManager.getInstance().getUserDAO().getUserBySecurityId(s.getId());
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
    
    public boolean checkEmail(String email){
        Pattern p = Pattern.compile(ProjectProps.REGEX_EMAIL);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public boolean checkPassword(String p1, String p2){
        return p1.equals(p2);
    }
    
}
