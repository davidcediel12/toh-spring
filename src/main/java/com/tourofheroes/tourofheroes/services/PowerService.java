package com.tourofheroes.tourofheroes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tourofheroes.tourofheroes.DTOs.PowerDTO;
import com.tourofheroes.tourofheroes.model.Power;
import com.tourofheroes.tourofheroes.repositories.PowerRepository;

@Service
public class PowerService {
	
	@Autowired 
	PowerRepository powerRepo;
	@Autowired
	ModelMapper mapper;
	
	public List<PowerDTO> getPowers(){
		List<Power> powers = powerRepo.findAll();
		List<PowerDTO> powersDto = new ArrayList<>();
		for(Power power : powers)
			// TAKE CARE WITH LAZY/EAGER FETCHING
			powersDto.add(mapper.map(power, PowerDTO.class));
		return powersDto;
	}
	
	public PowerDTO findByName(String name) {
		Optional<Power> powerOpt = powerRepo.findByName(name);
		if(powerOpt.isEmpty())
			return null;
		return mapper.map(powerOpt.get(), PowerDTO.class);
	}
	
	public PowerDTO findById(Integer id) {
		Optional<Power> powerOpt = powerRepo.findById(id);
		if(powerOpt.isEmpty())
			return null;
		
		return mapper.map(powerOpt.get(), PowerDTO.class);
	}
	
	public boolean updatePowerName(Integer id, PowerDTO powerDTO) {
		Optional<Power> powerOpt = powerRepo.findById(id);
		if(powerOpt.isEmpty())
			return false;
		
		
		Power power = powerOpt.get();
		boolean needUpdate = false;
		if(StringUtils.hasLength(power.getName())) {
			power.setName(powerDTO.getName());
			needUpdate = true;
		}
		
		if(needUpdate)
			powerRepo.save(power);
		return true;
	}
	
	
	public PowerDTO newPower(PowerDTO powerDto) {
		powerRepo.save(new Power(powerDto.getName()));
		return powerDto;
	}
	
	public boolean deletePower(Integer id) {
		Optional<Power> powerOpt = powerRepo.findById(id);
		if(powerOpt.isEmpty())
			return false;
		powerRepo.delete(powerOpt.get());
		return true;
	}
}
