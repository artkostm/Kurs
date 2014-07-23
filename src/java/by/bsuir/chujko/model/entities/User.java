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
public class User {
    private int idUser;
    private int idSecurity;

    public User() {
    }

    public User(int idUser, int idSecurity) {
        this.idUser = idUser;
        this.idSecurity = idSecurity;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdSecurity() {
        return idSecurity;
    }

    public void setIdSecurity(int idSecurity) {
        this.idSecurity = idSecurity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idUser;
        hash = 41 * hash + this.idSecurity;
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
        final User other = (User) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idSecurity != other.idSecurity) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", idSecurity=" + idSecurity + '}';
    }
    
}
