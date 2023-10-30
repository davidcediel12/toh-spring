package com.tourofheroes.tourofheroes.controllers;

import com.tourofheroes.tourofheroes.dto.HeroDTO;
import com.tourofheroes.tourofheroes.services.HeroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/heroes")
@Slf4j
@RequiredArgsConstructor
public class HeroesController {

    private final HeroService heroService;


    @GetMapping
    public ResponseEntity<List<HeroDTO>> getHeroes(@RequestParam(required = false) String name) {

        return ResponseEntity.ok(heroService.getHeroes(name));
    }


    @GetMapping("/{id}")
    public ResponseEntity<HeroDTO> getHero(@PathVariable Integer id) {
        HeroDTO heroDTO = heroService.getHero(id);
        if (heroDTO == null) {
            return new ResponseEntity<>(null,
                    HttpStatus.NOT_FOUND);
        }

        log.debug(heroDTO.toString());
        return ResponseEntity.ok(heroDTO);
    }


    @PostMapping
    public ResponseEntity<HeroDTO> addNewHero(@RequestBody HeroDTO heroDto) {
        log.debug(heroDto.getId() + "  " + heroDto.getName());
        return ResponseEntity.ok(heroService.newHero(heroDto));
    }

    @PatchMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
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

}

