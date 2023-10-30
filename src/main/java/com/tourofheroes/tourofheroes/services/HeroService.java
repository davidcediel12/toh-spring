package com.tourofheroes.tourofheroes.services;

import com.tourofheroes.tourofheroes.dto.HeroDTO;
import com.tourofheroes.tourofheroes.model.Hero;
import com.tourofheroes.tourofheroes.repositories.HeroRepository;
import com.tourofheroes.tourofheroes.repositories.PowerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepo;
    private final PowerRepository powerRepo;
    private final ModelMapper mapper;


    public List<HeroDTO> getHeroes(String name) {

        List<Hero> heroes;
        if (name != null) {
            heroes = heroRepo.findByNameStartsWithIgnoreCase(name);
        } else {
            heroes = heroRepo.findByOrderByIdAsc();
        }

        List<HeroDTO> heroesDTO = new ArrayList<>();
        for (Hero hero : heroes) {
            heroesDTO.add(mapper.map(hero, HeroDTO.class));
        }
        return heroesDTO;
    }

    public HeroDTO getHero(Integer id) {
        Optional<Hero> heroOpt = heroRepo.findById(id);
        if (heroOpt.isEmpty()) {
            return null;
        }
        Hero hero = heroOpt.get();
        return mapper.map(hero, HeroDTO.class);


    }

    public boolean updateHeroName(Integer id, HeroDTO heroDTO) {
        Optional<Hero> heroOpt = heroRepo.findById(id);
        if (heroOpt.isEmpty()) {
            return false;
        }
        Hero hero = heroOpt.get();
        boolean needUpdate = false;
        if (StringUtils.hasLength(hero.getName())) {
            hero.setName(heroDTO.getName());
            needUpdate = true;
        }

        if (needUpdate) {
            heroRepo.save(hero);
        }
        return true;
    }


    public HeroDTO newHero(HeroDTO heroDto) {

        Hero hero = new Hero(heroDto.getName(), heroDto.getAlterEgo());

        // Adding the power if it has one
        if (StringUtils.hasLength(heroDto.getPowerName())) {
            hero.setPower(powerRepo.findByName(heroDto.getPowerName())
                    .orElseThrow(() -> new IllegalStateException("Power not found")));
        }

        return mapper.map(heroRepo.save(hero), HeroDTO.class);
    }

    public boolean deleteHero(Integer id) {
        Optional<Hero> heroOpt = heroRepo.findById(id);
        if (heroOpt.isEmpty())
            return false;
        heroRepo.delete(heroOpt.get());
        return true;
    }
}
