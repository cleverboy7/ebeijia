package com.ebeijia.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
/**
 * 
 * @author Administrator
 * @param <T>
 */

public interface BaseDao<T> {
	public Object save(Object o);
	void delete(Object o);
	void deleteById(Serializable id);
	public void update(Object o);
	public void saveOrUpdate(Object o);
	
	public List getListSQL(final String sql); 
	public T getById(Serializable id);
	public List<T> findAll(Class<T> entityClass);
	public List<T> findByExample(Object o);
	public  List<T> find(String hql, Object... values);
	public  List<T> findByNamedParams(String hql,String[] paramNames,Object...values);
	public List<T> findByNamedParam(String hql, String paramNames,Object values);
	public List<T> findByCriteria(Criterion... criterion);
	public List<T> findByCriteria(int firstResult,int rowCount,Order order,Criterion... criterion);
	//...
}