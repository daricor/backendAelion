/**
 * 
 */
package com.aelion.suivi.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.dto.PoeShortListDto;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.services.POEService;
import com.aelion.suivi.services.exception.NotFoundException;

/**
 * @author Jokey
 *
 */
@RestController
@RequestMapping("/poe")
public class POE {

	@Autowired
	private POEService poeService;
	
	@PostMapping
	public POEEntity create(@RequestBody POEEntity poe) {
		return this.poeService.add(poe);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable long id) throws Exception {
		try {
			return ResponseEntity.ok(this.poeService.getOne(id));
		} catch (NotFoundException e) {
			return e.send();
		}
	}
	
	@GetMapping
	public List<POEEntity> getAll() {
		return this.poeService.findAll();
	}
	
	@GetMapping("/short")
	public List<PoeShortListDto> shortList() {
		return this.poeService.shortList();
	}
	
	@PutMapping
	public void update(@RequestBody POEEntity poe) {
		this.poeService.update(poe);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody POEEntity poe) throws Exception {
		try {
			this.poeService.delete(poe);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("id was not provided", HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		try {
			this.poeService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity<?> response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			return response;
		}		
	}
}
