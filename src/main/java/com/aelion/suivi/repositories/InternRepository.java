package com.aelion.suivi.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aelion.suivi.entities.InternEntity;


public interface InternRepository extends CrudRepository<InternEntity, Long> {
	/*@Query(value = "SELECT * FROM intern i WHERE i.name = ?1",
		   nativeQuery = true)	*/
	public List<InternEntity> findByName(String name);
	public List<InternEntity> findByNameAndBirthDateBetween(String name, Date startDate, Date endDate);
	public List<InternEntity> findByFirstname(String name);
}
