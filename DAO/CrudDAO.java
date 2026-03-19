package com.polycoffee.dao;

import java.util.List;

public interface CrudDAO<T, ID> {

	int create(T entity);

	int update(T entity);

	int delete(ID id);

	List<T> findAll();

	T findById(ID id);

	List<T> findBySql(String sql, Object... args);
}