/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.model.entities;

import java.util.Objects;

/**
 *
 * @author Artyom
 */
public class Message {
    private int idMessage;
    private int idSender;
    private int idRecipient;
    private int idDialog;
    private int idNote;
    private String value;
    private boolean read;

    public Message() {
    }

    public Message(int idMessage, int idSender, int idDialog, int idNote, 
            String value, boolean read) {
        this.idMessage = idMessage;
        this.idSender = idSender;
        this.idDialog = idDialog;
        this.idNote = idNote;
        this.value = value;
        this.read = read;
    }

    public Message(int idMessage, int idSender, int idRecipient, int idDialog, 
            int idNote, String value, boolean read) {
        this.idMessage = idMessage;
        this.idSender = idSender;
        this.idRecipient = idRecipient;
        this.idDialog = idDialog;
        this.idNote = idNote;
        this.value = value;
        this.read = read;
    }
    
    public Message(int idMessage, int idSender, int idDialog, int idNote, 
            String value) {
        this.idMessage = idMessage;
        this.idSender = idSender;
        this.idDialog = idDialog;
        this.idNote = idNote;
        this.value = value;
    }

    public Message(int idMessage, int idSender, int idRecipient, int idDialog,
            int idNote, String value) {
        this.idMessage = idMessage;
        this.idSender = idSender;
        this.idRecipient = idRecipient;
        this.idDialog = idDialog;
        this.idNote = idNote;
        this.value = value;
    }
    
    

    public int getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(int idRecipient) {
        this.idRecipient = idRecipient;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdDialog() {
        return idDialog;
    }

    public void setIdDialog(int idDialog) {
        this.idDialog = idDialog;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.idMessage;
        hash = 37 * hash + this.idSender;
        hash = 37 * hash + this.idDialog;
        hash = 37 * hash + this.idNote;
        hash = 37 * hash + Objects.hashCode(this.value);
        hash = 37 * hash + (this.read ? 1 : 0);
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
        final Message other = (Message) obj;
        if (this.idMessage != other.idMessage) {
            return false;
        }
        if (this.idSender != other.idSender) {
            return false;
        }
        if (this.idDialog != other.idDialog) {
            return false;
        }
        if (this.idNote != other.idNote) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return this.read == other.read;
    }

    @Override
    public String toString() {
        return "Message{" + "idMessage=" + idMessage + ", idSender="
                + idSender + ", idDialog=" + idDialog + ", idNote=" 
                + idNote + ", value=" + value + ", read=" + read + '}';
    }
    
    
}
