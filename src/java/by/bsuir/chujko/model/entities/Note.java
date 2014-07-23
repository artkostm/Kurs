/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.model.entities;

/**
 *
 * @author Artyom
 */
public class Note {
    private int idNote;
    private int idSender;
    private int idTag;
    private String value;
    private boolean answered;

    public Note() {
    }

    public Note(int idNote, int idSender, int idTag, String value) {
        this.idNote = idNote;
        this.idSender = idSender;
        this.idTag = idTag;
        this.value = value;
    }

    public Note(int idNote, int idSender, int idTag, String value, boolean answered) {
        this.idNote = idNote;
        this.idSender = idSender;
        this.idTag = idTag;
        this.value = value;
        this.answered = answered;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idNote;
        hash = 79 * hash + this.idSender;
        hash = 79 * hash + this.idTag;
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
        final Note other = (Note) obj;
        if (this.idNote != other.idNote) {
            return false;
        }
        if (this.idSender != other.idSender) {
            return false;
        }
        if (this.idTag != other.idTag) {
            return false;
        }
        return this.value.equals(other.value);
    }

    @Override
    public String toString() {
        return "Note{" + "idNote=" + idNote + ", idSender=" + idSender
                + ", idTag=" + idTag + ", value=" + value + '}';
    }
    
    
}
