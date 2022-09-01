package com.aelion.suivi.dto;

import com.aelion.suivi.entities.POEEntity;

public class PoeShortListDto {
	public Long id;
	public String name;
	
	public PoeShortListDto transform(POEEntity poe) {
		this.id = poe.getId();
		this.name = poe.getName();
		
		return this;
	}
}
