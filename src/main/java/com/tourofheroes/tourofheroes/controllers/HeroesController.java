package com.tourofheroes.tourofheroes.controllers;

import com.tourofheroes.tourofheroes.DTOs.HeroDTO;
import com.tourofheroes.tourofheroes.services.HeroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/heroes")
@Slf4j
@RequiredArgsConstructor
public class HeroesController {

    private final HeroService heroService;


    @GetMapping
    public ResponseEntity<List<HeroDTO>> getAllHeroes() {
        return ResponseEntity.ok(heroService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<HeroDTO> getHero(@PathVariable Integer id) {
        HeroDTO heroDTO = heroService.getHero(id);
        if (heroDTO == null) {
            return new ResponseEntity<>(null,
                    HttpStatus.NO_CONTENT);
        }

        log.debug(heroDTO.toString());
        return ResponseEntity.ok(heroDTO);
    }


    @PostMapping
    public ResponseEntity<HeroDTO> addNewHero(@RequestBody HeroDTO heroDto) {
        log.debug(heroDto.getId() + "  " + heroDto.getName());
        return ResponseEntity.ok(heroService.newHero(heroDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> modifyName(@PathVariable Integer id,
                                             @RequestBody HeroDTO heroDTO) {

        if (heroService.updateHeroName(id, heroDTO)) {
            return ResponseEntity.ok("Updated Successfully");
        } else {
            return new ResponseEntity<>("Hero not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHero(@PathVariable Integer id) {
        if (heroService.deleteHero(id))
            return ResponseEntity.ok("Deletion successfully");
        else
            return new ResponseEntity<>("Hero not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<HeroDTO>> findByName(@RequestParam String name) {
        log.debug(name);
        return ResponseEntity.ok(heroService.findByPartOfName(name));
    }
}

