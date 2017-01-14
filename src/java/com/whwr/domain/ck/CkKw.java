/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.domain.ck;

import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class CkKw {
    private Integer id;
    private Integer cangku_id;
    private String mc;
    private Integer qi_id;
    private Integer zhi_id;

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

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public Integer getQi_id() {
        return qi_id;
    }

    public void setQi_id(Integer qi_id) {
        this.qi_id = qi_id;
    }

    public Integer getZhi_id() {
        return zhi_id;
    }

    public void setZhi_id(Integer zhi_id) {
        this.zhi_id = zhi_id;
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
        final CkKw other = (CkKw) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
