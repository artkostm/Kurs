/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.model.entities.security;

/**
 *
 * @author Artyom
 */
public class Security {
    private String email;
    private String password;
    private int id;

    public Security() {
    }

    public Security(String email, String password, int id) {
        this.email = email;
        this.password = password;
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 97 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 97 * hash + this.id;
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
        final Security other = (Security) obj;
        if ((this.email == null) 
                ? (other.email != null) 
                : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.password == null) 
                ? (other.password != null) 
                : !this.password.equals(other.password)) {
            return false;
        }
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Security{" + "email=" + email + 
               ", password=" + password + ", id=" + id + '}';
    }

    
}
