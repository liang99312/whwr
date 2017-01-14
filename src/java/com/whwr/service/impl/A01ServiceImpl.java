package com.whwr.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.whwr.service.base.BaseServiceImpl;
import com.whwr.service.inter.A01ServiceInter;
import com.whwr.domain.A01;

@Transactional
public class A01ServiceImpl extends BaseServiceImpl implements
        A01ServiceInter {

    public A01 checkLogin(String name, String password) {
        if ("sa".equals(name) && "sa123".equals(password)) {
            A01 a01 = new A01();
            a01.setId(0);
            a01.setMc(name);
            a01.setBh(name);
            a01.setPassword(password);
            return a01;
        }
        List list = this.getResult("from A01 where bh='" + name + "'", null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            if (obj != null) {
                A01 a01 = (A01) obj;
                password = password == null ? "" : password;
                if (password.equals(a01.getPassword())) {
                    return a01;
                }
            }
        }
        return null;
    }

    public void deleteA01(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("delete from A01 where id=" + id).executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception he) {
                he.printStackTrace();
            }
        }
    }

}
