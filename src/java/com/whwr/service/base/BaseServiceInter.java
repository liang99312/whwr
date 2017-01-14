package com.whwr.service.base;

import java.io.Serializable;
import java.util.List;
import net.sf.json.JSONObject;
import org.hibernate.SessionFactory;

public interface BaseServiceInter {

    public SessionFactory getSessionFactory();

    public List getSqlResult(String sql);

    public List getResult(String hql, Object[] parameters);

    public List getPageList(String hql, Object[] parameters, int pageNow, int pageSize);

    public int getCount(String hql, Object[] parameters);

    public Integer saveObj(Object obj);

    public void delete(Object obj);

    public void update(Object obj);

    public void updateObjects(List updateList);

    public void saveObjects(List addList);

    public Object findById(Class c, Serializable id);

    public void excuteSqls(List<String> sqls);

    public void excuteSql(String sql);

    public JSONObject getPages(String sql, int pageNow);

    public void deleteObjById(String tabName, int id);
}
