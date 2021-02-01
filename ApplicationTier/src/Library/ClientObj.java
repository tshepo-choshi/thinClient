/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.io.Serializable;

/**
 *
 * @author eliasc
 */
public class ClientObj implements Serializable {
    private int clientid;
    private int clientno;
    private String name;
    private String surname;
    private String email;
    private String password;

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getClientno() {
        return clientno;
    }

    public void setClientno(int clientno) {
        this.clientno = clientno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    
    
   
    
    
    
    
    
    
}
