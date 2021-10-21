package com.tourofheroes.tourofheroes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tourofheroes.tourofheroes.DTOs.HeroDTO;
import com.tourofheroes.tourofheroes.model.Hero;
import com.tourofheroes.tourofheroes.repositories.HeroRepository;
import com.tourofheroes.tourofheroes.repositories.PowerRepository;

@Service
public class HeroService {
	@Autowired
	private HeroRepository heroRepo;
	@Autowired
	private PowerRepository powerRepo;
	@Autowired
	private ModelMapper mapper;

	
	
	/*
	 * Basic CRUD
	 */
	public List<HeroDTO> getAll() {
//		List<Hero> heroes = heroRepo.findAll();
		List<Hero> heroes = heroRepo.findByOrderByIdAsc();
		List<HeroDTO> heroesDTO = new ArrayList<HeroDTO>();
		for(Hero hero : heroes)
			heroesDTO.add(mapper.map(hero, HeroDTO.class));
		return heroesDTO;
	}
	
	public HeroDTO getHero(Integer id) {
		Optional<Hero> heroOpt = heroRepo.findById(id);
		if(heroOpt.isEmpty()) 
			return null;
			
		Hero hero = heroOpt.get();
//		System.out.println(hero.getPower().getName());
		return mapper.map(hero, HeroDTO.class);
	
		
	}
	
	public boolean updateHeroName(Integer id, HeroDTO heroDTO) {
		Optional<Hero> heroOpt = heroRepo.findById(id);
		if(heroOpt.isEmpty())
			return false;
		
		
		Hero hero = heroOpt.get();
		boolean needUpdate = false;
		if(StringUtils.hasLength(hero.getName())) {
			hero.setName(heroDTO.getName());
			needUpdate = true;
		}
		
		if(needUpdate)
			heroRepo.save(hero);
		return true;
	}
	
	
	public HeroDTO newHero(HeroDTO heroDto) {
		
		Hero hero = new Hero(heroDto.getName());
		// Adding the power if it has 
		if(StringUtils.hasLength(heroDto.getPowerName())) 
			hero.setPower(powerRepo.findByName(heroDto.getPowerName()).get());
		heroRepo.save(hero);
		return heroDto;
	}
	
	public boolean deleteHero(Integer id) {
		Optional<Hero> heroOpt = heroRepo.findById(id);
		if(heroOpt.isEmpty())
			return false;
		heroRepo.delete(heroOpt.get());
		return true;
	}
	
	
	/*
	 * Other Operations
	 */
	
	public List<HeroDTO> findByPartOfName(String name) {
		List<Hero> heroes = heroRepo.findByNameStartsWithIgnoreCase(name);
		List<HeroDTO> heroesDto = new ArrayList<HeroDTO>(); 
		for(Hero hero : heroes)
			heroesDto.add(mapper.map(hero, HeroDTO.class));
		return heroesDto;
	}
}
