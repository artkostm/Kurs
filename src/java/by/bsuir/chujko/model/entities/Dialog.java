/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.model.entities;

import java.util.List;

/**
 *
 * @author Artyom
 */
public class Dialog {
    private int idNote;
    private int idDialog;
    private int idAnsvered;
    private Note note;
    private List<Message> messages;

    public Dialog() {
    }

    public Dialog(int idNote, int idDialog, int idAnsvered) {
        this.idNote = idNote;
        this.idDialog = idDialog;
        this.idAnsvered = idAnsvered;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public int getIdDialog() {
        return idDialog;
    }

    public void setIdDialog(int idDialog) {
        this.idDialog = idDialog;
    }

    public int getIdAnsvered() {
        return idAnsvered;
    }

    public void setIdAnsvered(int idAnsvered) {
        this.idAnsvered = idAnsvered;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idNote;
        hash = 89 * hash + this.idDialog;
        hash = 89 * hash + this.idAnsvered;
        hash = 89 * hash + this.note.hashCode();
        hash = 89 * hash + this.messages.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dialog other = (Dialog) obj;
        if (this.idNote != other.idNote) {
            return false;
        }
        if (this.idDialog != other.idDialog) {
            return false;
        }
        if (this.idAnsvered != other.idAnsvered) {
            return false;
        }
        if (this.note.equals(other.note)) {
            return false;
        }
        return this.messages.equals(other.messages);
    }

    @Override
    public String toString() {
        return "Dialog{" + "idNote=" + idNote + ", idDialog=" + idDialog 
                + ", idAnsvered=" + idAnsvered + ", note=" + note 
                + ", messages=" + messages + '}';
    }
}
