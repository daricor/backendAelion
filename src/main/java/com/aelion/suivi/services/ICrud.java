/**
 * 
 */
package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;

/**
 * @author Jokey
 *
 */
public interface ICrud<T> {
	public T add(T t);
	public void update(T t);
	public void delete(T t);
	public List<T> findAll();
	public Optional<T> findOne(Long id);
	}
