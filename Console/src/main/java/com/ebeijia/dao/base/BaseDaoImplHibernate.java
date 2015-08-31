package com.ebeijia.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

/**
 * 泛型DAO实现
 * 
 * @author
 * @param <T>
 */

@SuppressWarnings("unchecked")
public class BaseDaoImplHibernate<T> extends HibernateDaoSupport implements
		BaseDao<T> {

	@Autowired
	public void setFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	protected Class<T> entityClass;

	private String hql = "";

	public BaseDaoImplHibernate() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.hql = "from " + entityClass.getName();
	}
	
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	public List<T> findAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void persist(final Object o) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.persist(o);
				return null;
			}

		});
	}

	public List<T> findAll(Class<T> entityClass, String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc)
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.asc(orderBy)));
		else
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.desc(orderBy)));
	}

	public List<T> findByExample(Object o) {
		return getHibernateTemplate().findByExample(o);
	}

	public T getById(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public void deleteById(Serializable id) {
		delete(getById(id));
	}

	public void delete(final Object o) {
		// getHibernateTemplate().delete(o);
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.delete(o);
				return null;
			}
		});
	}

	
	public Object save(Object o) {
		return getHibernateTemplate().merge(o);
	}

	public void saveOrUpdate(Object o) {
		getHibernateTemplate().merge(o);
	}

	public void update(Object o) {
		getHibernateTemplate().merge(o);
	}


	//不返回总条数
	public List<T> findByPage(final String hql, final int page, final int size) {
        return (List) super.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query=session.createQuery(hql);
                return query.setFirstResult((page-1)*size).setMaxResults(size).list();
            }
        });
	}

	//返回总条数
	public Map<String,Object> findByPageAndTotal(final String hql, final int page, final int size) {
        return (Map<String,Object>) super.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)throws HibernateException, SQLException {
                Query query=session.createQuery(hql);
                Map<String,Object> m = new HashMap<String,Object>();
        		int s =hql.indexOf("from");
        		String tmpHql=null;
        		if(s >0){
        			tmpHql =hql.substring(s,hql.length());
        		}else{
        			tmpHql=hql;
        		}
                m.put("total", getTotalRows(tmpHql));
                m.put("list",query.setFirstResult((page-1)*size).setMaxResults(size).list());
        		m.put("current", page);
                return m;
            }
        });
	}
	
	public int getTotalRows(final String hql) {
		String actualHql = "select count(*) " + hql;
		return ((Long) this.getHibernateTemplate().find(actualHql).get(0))
				.intValue();
	}

	public Long getCountRows(final String hql,final List<Object> inlist){
				String actualHql = "select count(*) " + hql;
				Query query = getSession().createQuery(actualHql);
				query.setParameterList("inlist", inlist);
				return   (Long)query.uniqueResult();
			}
	

	public List<T> find(String hql, Object... values) {
		return getHibernateTemplate().find(hql, values);
	}

	
	public List<T> find(String hql){
		return getHibernateTemplate().find(hql);
	}
	
	public List<T> findByNamedParams(String hql, String[] paramNames,
			Object... values) {
		return getHibernateTemplate().findByNamedParam(hql, paramNames, values);
	}

	
	public List<T> findByNamedParam(String hql, String paramNames, Object values) {
		return getHibernateTemplate().findByNamedParam(hql, paramNames, values);
	}

	public List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(entityClass);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public List<T> findByCriteria(int firstResult, int rowCount, Order order,
			Criterion... criterion) {
		Criteria crit = getSession().createCriteria(entityClass);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		if (order != null)
			crit.addOrder(order);
		crit.setFirstResult(firstResult);
		crit.setMaxResults(rowCount);
		return crit.list();

	}

	public List getListSQL(final String sql) {
		List retList = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						List list = query.list();
						return list;
					}
				});
		return retList;
	}

	public void updateAll(String sql) {
		final String queryString = sql;
		this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(queryString);
                return query.executeUpdate();
            }
        });
	}
	
	
	public static void main(String[] args) {
		String str ="select *  gihkjhk";
		int s =str.indexOf("from");
		System.out.println(s);
		str=str.substring(s,str.length());
		System.out.println(str);
	}
}
