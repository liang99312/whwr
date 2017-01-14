/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.domain.ck;

import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class CkRkdMx {
    private Integer id;
    private Integer cangku_id;
    private String cangku_mc;
    private Integer rkd_id;
    private Integer m_gys_id;
    private String m_gys;
    private Integer wzzd_id;
    private String m_wzmc;
    private String m_xhgg;
    private String m_pp;
    private String m_scc;
    private Double m_dj;
    private String m_dw;
    private Double m_rksl;
    private String m_gxys;
    private String m_bz;

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

    public String getCangku_mc() {
        return cangku_mc;
    }

    public void setCangku_mc(String cangku_mc) {
        this.cangku_mc = cangku_mc;
    }

    public Integer getRkd_id() {
        return rkd_id;
    }

    public void setRkd_id(Integer rkd_id) {
        this.rkd_id = rkd_id;
    }

    public Integer getM_gys_id() {
        return m_gys_id;
    }

    public void setM_gys_id(Integer m_gys_id) {
        this.m_gys_id = m_gys_id;
    }

    public String getM_gys() {
        return m_gys;
    }

    public void setM_gys(String m_gys) {
        this.m_gys = m_gys;
    }

    public Integer getWzzd_id() {
        return wzzd_id;
    }

    public void setWzzd_id(Integer wzzd_id) {
        this.wzzd_id = wzzd_id;
    }

    public String getM_wzmc() {
        return m_wzmc;
    }

    public void setM_wzmc(String m_wzmc) {
        this.m_wzmc = m_wzmc;
    }

    public String getM_xhgg() {
        return m_xhgg;
    }

    public void setM_xhgg(String m_xhgg) {
        this.m_xhgg = m_xhgg;
    }

    public String getM_pp() {
        return m_pp;
    }

    public void setM_pp(String m_pp) {
        this.m_pp = m_pp;
    }

    public String getM_scc() {
        return m_scc;
    }

    public void setM_scc(String m_scc) {
        this.m_scc = m_scc;
    }

    public Double getM_dj() {
        return m_dj;
    }

    public void setM_dj(Double m_dj) {
        this.m_dj = m_dj;
    }

    public String getM_dw() {
        return m_dw;
    }

    public void setM_dw(String m_dw) {
        this.m_dw = m_dw;
    }

    public Double getM_rksl() {
        return m_rksl;
    }

    public void setM_rksl(Double m_rksl) {
        this.m_rksl = m_rksl;
    }

    public String getM_gxys() {
        return m_gxys;
    }

    public void setM_gxys(String m_gxys) {
        this.m_gxys = m_gxys;
    }

    public String getM_bz() {
        return m_bz;
    }

    public void setM_bz(String m_bz) {
        this.m_bz = m_bz;
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
        final CkRkdMx other = (CkRkdMx) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
