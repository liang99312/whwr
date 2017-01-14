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
public class CkRkd {
    private Integer id;
    private String lsh;
    private Integer cangku_id;
    private String cangku_mc;
    private Integer a01_id;
    private String r_ly;
    private Integer r_gys_id;
    private String r_gys;
    private String r_dh;
    private Double r_zsl;
    private Double r_zje;
    private String r_jhr;
    private String r_spr;
    private Date r_date;
    private String r_state;
    private String r_bz;

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

    public Integer getA01_id() {
        return a01_id;
    }

    public void setA01_id(Integer a01_id) {
        this.a01_id = a01_id;
    }

    public String getCangku_mc() {
        return cangku_mc;
    }

    public void setCangku_mc(String cangku_mc) {
        this.cangku_mc = cangku_mc;
    }

    public String getR_ly() {
        return r_ly;
    }

    public void setR_ly(String r_ly) {
        this.r_ly = r_ly;
    }

    public Integer getR_gys_id() {
        return r_gys_id;
    }

    public void setR_gys_id(Integer r_gys_id) {
        this.r_gys_id = r_gys_id;
    }

    public String getR_gys() {
        return r_gys;
    }

    public void setR_gys(String r_gys) {
        this.r_gys = r_gys;
    }

    public String getR_dh() {
        return r_dh;
    }

    public void setR_dh(String r_dh) {
        this.r_dh = r_dh;
    }

    public Double getR_zsl() {
        return r_zsl;
    }

    public void setR_zsl(Double r_zsl) {
        this.r_zsl = r_zsl;
    }

    public Double getR_zje() {
        return r_zje;
    }

    public void setR_zje(Double r_zje) {
        this.r_zje = r_zje;
    }

    public String getR_jhr() {
        return r_jhr;
    }

    public void setR_jhr(String r_jhr) {
        this.r_jhr = r_jhr;
    }

    public String getR_spr() {
        return r_spr;
    }

    public void setR_spr(String r_spr) {
        this.r_spr = r_spr;
    }

    public Date getR_date() {
        return r_date;
    }

    public void setR_date(Date r_date) {
        this.r_date = r_date;
    }

    public String getR_state() {
        return r_state;
    }

    public void setR_state(String r_state) {
        this.r_state = r_state;
    }

    public String getR_bz() {
        return r_bz;
    }

    public void setR_bz(String r_bz) {
        this.r_bz = r_bz;
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
        final CkRkd other = (CkRkd) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
