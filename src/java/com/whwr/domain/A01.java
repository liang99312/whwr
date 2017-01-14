package com.whwr.domain;

import javax.persistence.Entity;

@Entity
public class A01 {

    private Integer id;
    private String bh;
    private String mc;
    private String dm;
    private String a0111;
    private String a0105;
    private String a01pic;
    private String a01qx;
    private String password;
    private String a01flag;

    public A01() {
    }

    public A01(String bh, String mc, String a0111, String a0105) {
        this.bh = bh;
        this.mc = mc;
        this.a0111 = a0111;
        this.a0105 = a0105;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getA0111() {
        return a0111;
    }

    public void setA0111(String a0111) {
        this.a0111 = a0111;
    }

    public String getA0105() {
        return a0105;
    }

    public void setA0105(String a0105) {
        this.a0105 = a0105;
    }

    public String getA01pic() {
        return a01pic;
    }

    public void setA01pic(String a01pic) {
        this.a01pic = a01pic;
    }

    public String getA01qx() {
        return a01qx;
    }

    public void setA01qx(String a01qx) {
        this.a01qx = a01qx;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getA01flag() {
        return a01flag;
    }

    public void setA01flag(String a01flag) {
        this.a01flag = a01flag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bh == null) ? 0 : bh.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        A01 other = (A01) obj;
        if (bh == null) {
            if (other.bh != null) {
                return false;
            }
        } else if (!bh.equals(other.bh)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return mc;
    }

}
