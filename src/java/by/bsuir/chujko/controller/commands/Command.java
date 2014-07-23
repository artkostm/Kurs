/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Artyom
 */
public interface Command {
    
    /**
     * 
     * @param request
     * @param response
     * @return name of page
     * @throws ServletException
     * @throws IOException 
     */
    public String execute(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException;
    
    final boolean ajax = false;
    
    public boolean isAjax();
}
