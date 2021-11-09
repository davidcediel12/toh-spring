package com.tourofheroes.tourofheroes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourofheroes.tourofheroes.DTOs.HeroDTO;
import com.tourofheroes.tourofheroes.services.HeroService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/heroes")
public class HeroesController {
	
	@Autowired
	private HeroService heroService;
	
	/*
	 * Basic CRUD
	 */
	
	@GetMapping("/getAll")
	public ResponseEntity<List<HeroDTO>> getAllHeroes(){
		System.out.println("HELLO IM THE CONTROLLER");
		return ResponseEntity.ok(heroService.getAll());
	}

	
	@GetMapping("/get")
	public ResponseEntity<HeroDTO> getHero(@RequestParam Integer id){
		HeroDTO heroDTO = heroService.getHero(id);
		if(heroDTO == null)
			return new ResponseEntity<>(null, 
								HttpStatus.NO_CONTENT);
		
		System.out.println(heroDTO.toString());
		return ResponseEntity.ok(heroDTO);
	}
	
	
	@PostMapping("/newHero")
	public ResponseEntity<HeroDTO> addNewHero(@RequestBody HeroDTO heroDto) {
		System.out.println(heroDto.getName() + "  " +  heroDto.getName());
		return ResponseEntity.ok(heroService.newHero(heroDto));
	}
	
	@PatchMapping("/modifyName/{id}")
	public ResponseEntity<String> modifyName(@PathVariable Integer id, 
									@RequestBody HeroDTO heroDTO) {
		
		if(heroService.updateHeroName(id, heroDTO))
			return ResponseEntity.ok("Updated Succesfully");
		else
			return new ResponseEntity<>("Error updating", HttpStatus.BAD_REQUEST);
	}
	
	
	@DeleteMapping("/deleteHero/{id}")
	public ResponseEntity<String> deleteHero(@PathVariable Integer id){
		if(heroService.deleteHero(id))
			return ResponseEntity.ok("Deletion successfully");
		else
			return new ResponseEntity<String>("Deletion went wrong", HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * Other operations
	 */
	
	@GetMapping("/findByName")
	public ResponseEntity<List<HeroDTO>> findByName(@RequestParam String name){
		System.out.println(name);
		return ResponseEntity.ok(heroService.findByPartOfName(name));
	}
}

