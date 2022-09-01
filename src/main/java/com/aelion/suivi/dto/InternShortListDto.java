/**
 * 
 */
package com.aelion.suivi.dto;

import java.util.Date;

import com.aelion.suivi.entities.InternEntity;

/**
 * @author Jokey
 *
 */
public class InternShortListDto {
	public Long id;
	public String name;
	public String firstname;
	public Date birthDate;
	
	public InternShortListDto transform(InternEntity intern) {
		this.id = intern.getId();
		this.name = intern.getName();
		this.firstname = intern.getFirstname();
		this.birthDate = intern.getBirthDate();
		
		return this;
	}
}
