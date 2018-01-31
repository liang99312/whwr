/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.domain.ck;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class CkLld {
    private Integer id;
    private String lsh;
    private Integer cangku_id;
    private String cangku_mc;
    private Integer a01_id;
    private Double l_zsl;
    private Double l_zje;
    private String l_jhr;
    private String l_spr;
    private Date l_date;
    private String l_state;
    private String l_bz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public Integer getCangku_id() {
        return cangku_id;
    }

    public void setCangku_id(Integer cangku_id) {
        this.cangku_id = cangku_id;
    }

    public String getCangku_mc() {
        return cangku_mc;
    }

    public void setCangku_mc(String cangku_mc) {
        this.cangku_mc = cangku_mc;
    }

    public Integer getA01_id() {
        return a01_id;
    }

    public void setA01_id(Integer a01_id) {
        this.a01_id = a01_id;
    }

    public Double getL_zsl() {
        return l_zsl;
    }

    public void setL_zsl(Double l_zsl) {
        this.l_zsl = l_zsl;
    }

    public Double getL_zje() {
        return l_zje;
    }

    public void setL_zje(Double l_zje) {
        this.l_zje = l_zje;
    }

    public String getL_jhr() {
        return l_jhr;
    }

    public void setL_jhr(String l_jhr) {
        this.l_jhr = l_jhr;
    }

    public String getL_spr() {
        return l_spr;
    }

    public void setL_spr(String l_spr) {
        this.l_spr = l_spr;
    }

    public Date getL_date() {
        return l_date;
    }

    public void setL_date(Date l_date) {
        this.l_date = l_date;
    }

    public String getL_state() {
        return l_state;
    }

    public void setL_state(String l_state) {
        this.l_state = l_state;
    }

    public String getL_bz() {
        return l_bz;
    }

    public void setL_bz(String l_bz) {
        this.l_bz = l_bz;
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
        final CkLld other = (CkLld) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
