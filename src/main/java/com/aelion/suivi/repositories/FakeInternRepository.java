/**
 * 
 */
package com.aelion.suivi.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aelion.suivi.entities.InternEntity;

/**
 * @author Jokey
 *
 */
public final class FakeInternRepository {
	private List<InternEntity> interns = new ArrayList<>();
	
	public FakeInternRepository() {
		this.populate();
	}
	
	public List<InternEntity> getInterns() {
		return this.interns;
	}
	
	public InternEntity add(InternEntity intern) {
		if(findOne(intern.getId()).isEmpty()) {
			this.interns.add(intern);
		}
		return intern;
	}
	
	public void delete(InternEntity intern) {
		this.interns.remove(intern);
	}
	
	public void update(InternEntity intern) {
		
		for(int i = 0; i < this.interns.size(); i++) {
			
			if (this.interns.get(i).getId() == intern.getId()) {
				this.interns.get(i).setFirstname(intern.getFirstname());
				this.interns.get(i).setName(intern.getName());
				this.interns.get(i).setEmail(intern.getEmail());
				this.interns.get(i).setPhoneNumber(intern.getPhoneNumber());
				this.interns.get(i).setBirthDate(intern.getBirthDate());
				break;
			}
		}
	}
	
	public Optional<InternEntity> findOne(long id) {
		InternEntity myIntern = null;
		for (InternEntity intern : interns) {
			if (intern.getId() == id) {
				myIntern = intern;
				break;				
			}
		}
		return Optional.ofNullable(myIntern);
	}
	
	private void populate() {
		InternEntity intern = new InternEntity();
		intern.setId(1L);
		intern.setName("Aubert");
		intern.setFirstname("Jean-Luc");
		intern.setEmail("jla@gmail.com");
		intern.setPhoneNumber("+(33)6 23 56 89 14");
		
		this.getInterns().add(intern);
		
		intern = new InternEntity();
		intern.setId(2L);
		intern.setName("TOTO");
		intern.setFirstname("Tata");
		intern.setEmail("tota@gmail.com");
		intern.setPhoneNumber("+(33)6 23 56 89 15");
		
		this.getInterns().add(intern);
	}
}
