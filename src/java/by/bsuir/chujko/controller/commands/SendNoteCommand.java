/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import by.bsuir.chujko.dao.DAOManager;
import by.bsuir.chujko.model.entities.Note;
import by.bsuir.chujko.model.entities.Tag;
import by.bsuir.chujko.model.entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Artyom
 */
public class SendNoteCommand implements Command{

    private static final String TEST = "test";
    
    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String note = request.getParameter(TEST);
        User u = (User)request.getSession().getAttribute("user");
        if(note == null || note.trim().isEmpty() || u == null){
            return "/error_send_note.jsp";
        }
        Tag t = new Tag();
        String tag = TagUtil.getFirstTag(note);
        if(tag == null || tag.trim().isEmpty()){
            t.setEmpty(true);
            t.setValue("");
        } else{
            t.setValue(tag);
        }
        DAOManager.getInstance().getTagDAO().add(t);
        t = DAOManager.getInstance().getTagDAO().getTagByValue(t.getValue());
        if(t == null){
            return "/nonote.jsp";
        }
        Note n = new Note(0, u.getIdUser(), t.getIdTag(), note);
        DAOManager.getInstance().getNoteDAO().add(n);
        Note newNote = DAOManager.getInstance().getNoteDAO().getOneLast(u.getIdUser(), t.getIdTag());
        if(newNote == null || newNote.getIdSender() == 0 || newNote.isAnswered()){
            request.setAttribute("tag", t.getValue());
            return "/nonote.jsp";
        }
        request.setAttribute("newNote", newNote);
        return "/new_note.jsp";
    }
    
    @Override
    public boolean isAjax() {
        return ajax;
    }
}
