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

@Service
public class HeroService {
	@Autowired
	private HeroRepository heroRepo;
	@Autowired
	private ModelMapper mapper;
	
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
		if(heroOpt.isPresent()) {
			Hero hero = heroOpt.get();
			return mapper.map(hero, HeroDTO.class);
		}
		return null;
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
}
