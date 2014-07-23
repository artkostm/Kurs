/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller;

import by.bsuir.chujko.ProjectProps;
import by.bsuir.chujko.controller.commands.Command;
import by.bsuir.chujko.dao.DAOManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Artyom
 */
public class Controller extends HttpServlet {

    private RequestHelper requestHelper = RequestHelper.getInstance();
    private DAOManager manager;
    private static final Logger logger = Logger.getLogger(ProjectProps.LOGGER_WEB);

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init(); 
        manager = DAOManager.getInstance();
    }

    @Override
    public void destroy() {
        manager.close();
        super.destroy();
    }
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("INSIDE processRequest");
        String page;
        Command command = requestHelper.getCommand(request);
        if(command.isAjax()){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            getServletContext().log("========  =======");
            out.print(command.execute(request, response));
            out.close();
        }else{
            page = command.execute(request, response);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page); 
            dispatcher.forward(request, response);
        } 
    }
    
    @Override
    public void log(String msg) {
        getServletContext().log(msg);        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
