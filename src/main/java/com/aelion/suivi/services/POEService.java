/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.PoeShortListDto;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.POERepository;
import com.aelion.suivi.services.exception.NotFoundException;

/**
 * @author Jokey
 *
 */
@Service
public class POEService implements ICrud<POEEntity> {

	@Autowired
	private POERepository poeRepository;
	
	@Override
	public POEEntity add(POEEntity t) {
		Optional<POEEntity> maPoe = this.poeRepository.findById(t.getId());
		if (maPoe.isEmpty()) {
			return this.poeRepository.save(t);
		}else {
			return t;
		}
	}

	@Override
	public void update(POEEntity t) {
		Optional<POEEntity> maPoe = this.poeRepository.findById(t.getId());
		if (maPoe.isPresent()) {
			this.poeRepository.save(t);
		}
	}

	@Override
	public void delete(POEEntity t) {
		this.poeRepository.delete(t);
	}
	
	public void delete(Long id) {	
			this.poeRepository.deleteById(id);	
	}

	@Override
	public List<POEEntity> findAll() {
		return (List<POEEntity>) this.poeRepository.findAll();
	}

	public List<PoeShortListDto> shortList() {
		List<PoeShortListDto> poeShortList = new ArrayList<>();
		for (POEEntity poe : this.poeRepository.findAll()) {
			poeShortList.add(new PoeShortListDto().transform(poe));
		}
		return poeShortList;
	}
	
	@Override
	public Optional<POEEntity> findOne(Long id){
		return this.poeRepository.findById(id);
	}
	
	
	public POEEntity getOne(Long id) throws Exception{
		Optional<POEEntity> oEntity = this.poeRepository.findById(id);
		if (oEntity.isPresent()) {
			return oEntity.get();
		}
		throw new NotFoundException("the poe with "+id+" as id not found");
	}
}
