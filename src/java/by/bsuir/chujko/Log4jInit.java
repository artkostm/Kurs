/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author Artyom
 */
public class Log4jInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String homeDir = sce.getServletContext().getRealPath("/");
        File propertiesFile = new File(homeDir,ProjectProps.LOG_FILE_PATH);
        PropertyConfigurator.configure(propertiesFile.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
