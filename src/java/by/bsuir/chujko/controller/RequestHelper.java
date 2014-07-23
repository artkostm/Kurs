/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller;

import by.bsuir.chujko.controller.commands.AddCommand;
import by.bsuir.chujko.controller.commands.AddNewCommand;
import by.bsuir.chujko.controller.commands.CheckCommand;
import by.bsuir.chujko.controller.commands.Command;
import by.bsuir.chujko.controller.commands.DeleteCommand;
import by.bsuir.chujko.controller.commands.IndexCommand;
import by.bsuir.chujko.controller.commands.LoginCommand;
import by.bsuir.chujko.controller.commands.NewMessageCommand;
import by.bsuir.chujko.controller.commands.NoCommand;
import by.bsuir.chujko.controller.commands.RegCommand;
import by.bsuir.chujko.controller.commands.SendNoteCommand;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Artyom
 */
public class RequestHelper {
    
    private static RequestHelper instance;
    private static final ReentrantLock lock = new ReentrantLock();
    private final HashMap<String, Command> commands 
            = new HashMap<>();
    
    private RequestHelper(){
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegCommand());
        commands.put("send_note", new SendNoteCommand());
        commands.put("index", new IndexCommand());
        commands.put("add", new AddCommand());
        commands.put("add_new", new AddNewCommand());
        commands.put("check", new CheckCommand());
        commands.put("new_mes", new NewMessageCommand());
        commands.put("delete", new DeleteCommand());
    }
    
    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        } 
        return command; 
    } 

    public static RequestHelper getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new RequestHelper();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
}
