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
public class Tag {
    private int idTag;
    private String value;
    private boolean empty;

    public Tag() {
    }

    public Tag(int idTag, String value, boolean empty) {
        this.idTag = idTag;
        this.value = value;
        this.empty = empty;
    }
    
    public Tag(int idTag, String value) {
        this.idTag = idTag;
        this.value = value;
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

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idTag;
        hash = 97 * hash + Objects.hashCode(this.value);
        hash = 97 * hash + (this.empty ? 1 : 0);
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
        final Tag other = (Tag) obj;
        if (this.idTag != other.idTag) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (this.empty != other.empty) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tag{" + "idTag=" + idTag + ", value=" + value + ", empty=" + empty + '}';
    }
    
}
