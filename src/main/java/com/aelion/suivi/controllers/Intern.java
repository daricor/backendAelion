package com.aelion.suivi.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.cfg.ToOneBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.services.InternService;

@RestController
@RequestMapping("/intern")
public class Intern {

	@Autowired
	private InternService internService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> greetings() {
		return ResponseEntity.ok("Hello Spring");
	}
	
	/**
	 * 
	 * @param intern
	 * @return 201 created
	 */
	@PostMapping
	public InternEntity create(@RequestBody InternEntity intern){
		return this.internService.add(intern);
	}
	
	@GetMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<?> getOne(@PathVariable long id) {
		Optional<InternEntity> oInternEntity = this.internService.findOne(id);
		if (oInternEntity.isPresent()) {
			return ResponseEntity.ok(oInternEntity.get());
		}else {
			return (ResponseEntity<?>) ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	@CrossOrigin
	public List<InternEntity> getAll() {
		return this.internService.findAll();
	}
	
	@GetMapping("/short")
	public List<InternShortListDto> shortList() {
		return this.internService.shortList();
	}
	
	@PutMapping
	public void update(@RequestBody InternEntity intern) {
		this.internService.update(intern);
	}
	
	@DeleteMapping
	@CrossOrigin
	public ResponseEntity<?> delete(@RequestBody InternEntity intern) {			
					this.internService.delete(intern);
					return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/byname/{name}")
	public List<InternEntity> findByName(@PathVariable String name) {
		return this.internService.findByName(name);
	}
	
	@GetMapping("/bynameAndAge/{name}")
	public List<InternEntity> findNamebyAge(@PathVariable String name,
			@RequestParam int age) {
		return this.internService.findNamebyAge(name, age);
	}
	
	
}
