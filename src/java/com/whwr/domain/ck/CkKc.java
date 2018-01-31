/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.domain.ck;

import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class CkKc {
    private Integer id;
    private String lsh;
    private Integer cangku_id;
    private String cangku_mc;
    private Integer rkdmx_id;
    private Integer k_gys_id;
    private String k_gys;
    private Integer wzzd_id;
    private String k_wzmc;
    private String k_xhgg;
    private String k_pp;
    private String k_scc;
    private String k_dw;
    private Double k_rksl;
    private Double k_cksl;
    private Double k_syl;
    private Double k_rkjg;
    private Double k_ckjg;
    private String k_gxys;
    private String k_kw;
    private String k_bz;

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

    public Integer getRkdmx_id() {
        return rkdmx_id;
    }

    public void setRkdmx_id(Integer rkdmx_id) {
        this.rkdmx_id = rkdmx_id;
    }

    public Integer getK_gys_id() {
        return k_gys_id;
    }

    public void setK_gys_id(Integer k_gys_id) {
        this.k_gys_id = k_gys_id;
    }

    public String getK_gys() {
        return k_gys;
    }

    public void setK_gys(String k_gys) {
        this.k_gys = k_gys;
    }

    public Integer getWzzd_id() {
        return wzzd_id;
    }

    public void setWzzd_id(Integer wzzd_id) {
        this.wzzd_id = wzzd_id;
    }

    public String getK_wzmc() {
        return k_wzmc;
    }

    public void setK_wzmc(String k_wzmc) {
        this.k_wzmc = k_wzmc;
    }

    public String getK_xhgg() {
        return k_xhgg;
    }

    public void setK_xhgg(String k_xhgg) {
        this.k_xhgg = k_xhgg;
    }

    public String getK_pp() {
        return k_pp;
    }

    public void setK_pp(String k_pp) {
        this.k_pp = k_pp;
    }

    public String getK_scc() {
        return k_scc;
    }

    public void setK_scc(String k_scc) {
        this.k_scc = k_scc;
    }

    public String getK_dw() {
        return k_dw;
    }

    public void setK_dw(String k_dw) {
        this.k_dw = k_dw;
    }

    public Double getK_rksl() {
        return k_rksl;
    }

    public void setK_rksl(Double k_rksl) {
        this.k_rksl = k_rksl;
    }

    public Double getK_cksl() {
        return k_cksl;
    }

    public void setK_cksl(Double k_cksl) {
        this.k_cksl = k_cksl;
    }

    public Double getK_syl() {
        return k_syl;
    }

    public void setK_syl(Double k_syl) {
        this.k_syl = k_syl;
    }

    public String getK_gxys() {
        return k_gxys;
    }

    public Double getK_rkjg() {
        return k_rkjg;
    }

    public void setK_rkjg(Double k_rkjg) {
        this.k_rkjg = k_rkjg;
    }

    public Double getK_ckjg() {
        return k_ckjg;
    }

    public void setK_ckjg(Double k_ckjg) {
        this.k_ckjg = k_ckjg;
    }

    public void setK_gxys(String k_gxys) {
        this.k_gxys = k_gxys;
    }

    public String getK_kw() {
        return k_kw;
    }

    public void setK_kw(String k_kw) {
        this.k_kw = k_kw;
    }

    public String getK_bz() {
        return k_bz;
    }

    public void setK_bz(String k_bz) {
        this.k_bz = k_bz;
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
        final CkKc other = (CkKc) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    
    
}
