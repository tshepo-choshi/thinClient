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
public class Transmission implements Serializable{
    
    private String decision;
    private Object object;

    public Transmission() {
    }

    public Transmission(String decision, Object object) {
        this.decision = decision;
        this.object = object;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
