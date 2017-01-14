package com.whwr.service.base;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class BaseServiceImpl implements BaseServiceInter {

    @Resource
    protected SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void delete(Object obj) {
        // TODO Auto-generated method stub
//		sessionFactory.getCurrentSession().delete(obj);
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(obj);
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

    public void excuteSqls(List<String> sqls) {
        // TODO Auto-generated method stub
//		sessionFactory.getCurrentSession().delete(obj);
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for (String sql : sqls) {
                session.createSQLQuery(sql).executeUpdate();
                session.flush();
            }
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

    public void excuteSql(String sql) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
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

    public List getSqlResult(String sql) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.list();
    }

    public List getResult(String hql, Object[] parameters) {
        // TODO Auto-generated method stub
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i, parameters[i]);
            }
        }
        return query.list();
    }

    public int getCount(String hql, Object[] parameters) {
        // TODO Auto-generated method stub
        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i, parameters[i]);
            }
        }
        List list = query.list();
        if (list == null || list.isEmpty() || list.get(0) == null) {
            return 0;
        }
        return Integer.valueOf(list.get(0).toString());
    }

    public List getPageList(String hql, Object[] parameters, int pageNow, int pageSize) {
        int b_row = (pageNow - 1) * pageSize;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i, parameters[i]);
            }
        }
        query.setFirstResult(b_row);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public List getPageListBySql(String sql, Object[] parameters, int pageNow, int pageSize) {
        int b_row = (pageNow - 1) * pageSize;
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i, parameters[i]);
            }
        }
        query.setFirstResult(b_row);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public Integer saveObj(Object obj) {
        Integer result = -1;
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            result = (Integer) session.save(obj);
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
        return result;
    }

    public void update(Object obj) {
        // TODO Auto-generated method stub
//		sessionFactory.getCurrentSession().update(obj);
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(obj);
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

    public void updateObjects(List updateList) {
        // TODO Auto-generated method stub
//		sessionFactory.getCurrentSession().update(obj);
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for (Object obj : updateList) {
                session.update(obj);
                session.flush();
            }
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

    public void saveObjects(List addList) {
        // TODO Auto-generated method stub
//		sessionFactory.getCurrentSession().update(obj);
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for (Object obj : addList) {
                session.save(obj);
                session.flush();
            }
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

    public Object findById(Class c, Serializable id) {
        // TODO Auto-generated method stub
        return sessionFactory.getCurrentSession().load(c, id);
    }

    public JSONObject getPages(String sql, int pageNow) {
        JSONObject result = new JSONObject();
        int total = getCount("select count(1) " + sql, null);
        int zys = (total - 1) / 20 + 1;
        result.put("zys", zys);
        result.put("yx", pageNow);
        result.put("jls", total);
        return result;
    }
    
    public void deleteObjById(String tblName,int id){
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("delete from "+tblName+" where id=" + id).executeUpdate();
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
