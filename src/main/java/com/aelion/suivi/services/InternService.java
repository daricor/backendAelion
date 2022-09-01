/**
 * 
 */
package com.aelion.suivi.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.repositories.InternRepository;

/**
 * @author Jokey
 *
 */
@Service
public class InternService implements ICrud<InternEntity> {

	@Autowired
	private InternRepository internRepository;
		
	/**
	 * INSERT INTO intern (name, firstname, ....., adress) VALUES (...);
	 */
	@Override
	public InternEntity add(InternEntity t){
		try {
			Optional<InternEntity> monIntern = this.internRepository.findById(t.getId());
			if (monIntern.isEmpty()) {
				return this.internRepository.save(t);
			}	
		} catch (Exception e) {
			return this.internRepository.save(t);
		}
		return t;
	}

	@Override
	public void update(InternEntity t) {
		Optional<InternEntity> monIntern = this.internRepository.findById(t.getId());
		if (monIntern.isPresent()) {
			this.internRepository.save(t);
		}
	}

	@Override
	public void delete(InternEntity t) {
		// TODO Auto-generated method stub
		this.internRepository.delete(t);
	}

	@Override
	public List<InternEntity> findAll() {
		// TODO Auto-generated method stub
		return (List<InternEntity>) this.internRepository.findAll();
	}
	
	/**
	 * 
	 * @return A List of InternShortListDto objects
	 */
	public List<InternShortListDto> shortList() {
		List<InternShortListDto> dtoList = new ArrayList<>();
		for (InternEntity intern : this.internRepository.findAll()) {
			dtoList.add(new InternShortListDto().transform(intern));
		}
		return dtoList;
	}

	@Override
	public Optional<InternEntity> findOne(Long id) {
		// TODO Auto-generated method stub
		return this.internRepository.findById(id);
	}
	
	public List<InternEntity> findByName(String name) {
		return this.internRepository.findByName(name);
	}
	
	public List<InternEntity> findNamebyAge(String name, int age) {
		LocalDate startDate = LocalDate.now().minusYears(age);
		LocalDate endDate = LocalDate.now().minusYears(age + 1);
		
		//LocalDate startDate = LocalDate.of(1967, 9, 02);
		//LocalDate endDate = LocalDate.of(1968, 9, 01);
		
		Date debutDate = (Date) Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		Date finDate = (Date) Date.from(endDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		return this.internRepository.findByNameAndBirthDateBetween(name, debutDate, finDate);
		//return new ArrayList<>();
	}
}
