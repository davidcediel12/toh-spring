package com.tourofheroes.tourofheroes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tourofheroes.tourofheroes.dto.PowerDTO;
import com.tourofheroes.tourofheroes.services.PowerService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/powers")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @GetMapping
    public ResponseEntity<List<PowerDTO>> getAllPowers() {
        List<PowerDTO> powersDto = powerService.getPowers();
        if (powersDto.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(powersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerDTO> findById(@PathVariable Integer id) {
        PowerDTO powerDto = powerService.findById(id);
        if (powerDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(powerDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> modifyName(@PathVariable Integer id,
                                             @RequestBody PowerDTO powerDto) {

        if (powerService.updatePowerName(id, powerDto))
            return ResponseEntity.ok("Updated Succesfully");
        else
            return new ResponseEntity<>("Error updating", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<PowerDTO> newPower(@RequestBody PowerDTO powerDto) {
        return ResponseEntity.ok(powerService.newPower(powerDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHero(@PathVariable Integer id) {
        if (powerService.deletePower(id)) {
            return ResponseEntity.ok("Deletion successfully");
        } else {
            return new ResponseEntity<>("Deletion went wrong", HttpStatus.BAD_REQUEST);
        }
    }

}