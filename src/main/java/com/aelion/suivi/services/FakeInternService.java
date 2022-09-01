/**
 * 
 */
package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.repositories.FakeInternRepository;

/**
 * @author Jokey
 *
 */
@Service
public class FakeInternService implements ICrud<InternEntity> {

	private FakeInternRepository internRepository = new FakeInternRepository();
	
	@Override
	public InternEntity add(InternEntity t) {
		return this.internRepository.add(t);
	}

	@Override
	public void update(InternEntity t) {
		this.internRepository.update(t);
		
	}

	@Override
	public void delete(InternEntity t) {
		this.internRepository.delete(t);
		
	}

	@Override
	public List<InternEntity> findAll() {		
		return this.internRepository.getInterns();
	}

	@Override
	public Optional<InternEntity> findOne(Long id) {
		return this.internRepository.findOne(id);
	}

}
