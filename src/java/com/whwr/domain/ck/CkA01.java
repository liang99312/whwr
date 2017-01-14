/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.domain.ck;

import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class CkA01 {
    private Integer id;
    private Integer cangku_id;
    private Integer a01_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCangku_id() {
        return cangku_id;
    }

    public void setCangku_id(Integer cangku_id) {
        this.cangku_id = cangku_id;
    }

    public Integer getA01_id() {
        return a01_id;
    }

    public void setA01_id(Integer a01_id) {
        this.a01_id = a01_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final CkA01 other = (CkA01) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
