/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko;

/**
 *
 * @author Artyom
 */
public class ProjectProps {

    private ProjectProps() {
    }
    
    public static final String LOGGER_WEB = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/config")
                  .getString("log.logger.web.name");
    
    public static final String LOGGER_DAO = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/config")
                  .getString("log.logger.dao.name");
    
    public static final String LOG_FILE_PATH = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/config")
                  .getString("log.file.path");
    
    public static final String REGEX_EMAIL = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/config")
                  .getString("regex.email");
}
