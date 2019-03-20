package br.com.codenation.sistemagestaohospitalar.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.codenation.sistemagestaohospitalar.api.entities.Hospital;
import br.com.codenation.sistemagestaohospitalar.api.repository.HospitalRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;
	
	public List<Hospital> getHospitalAllList() {
		return hospitalRepository.findAll();
	}
	/*
	public List<Hospital> getHospitalPageList(int page, int size, String sortDir, String sort) {
	  
	    PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
	  
	    Page<Hospital> hospital = hospitalRepository.findAll(null, pageReq);
	    
	    return hospital.getContent();
	}

	public List<Hospital> getHospitalByAddressList(String address){
		return hospitalRepository.findAllByAddress(address);
	}*/
	
	public Hospital getHospitalById(Long id) {
		
		return hospitalRepository.getOne(id);
	}
	/*
	public Hospital getHospitalByName(String name) {
		
		return hospitalRepository.findByName(name);
	}
	
	public Hospital getHospitalByAddress(String address) {
		
		return hospitalRepository.findByAddress(address);
	}*/
	
	public Hospital createHospital(Hospital hospital) {
		
		return hospitalRepository.save(hospital);
	}
	
	public Hospital updateHospital(Hospital hospital) {
		Hospital updateHospital = getHospitalById(hospital.getId());
		
		updateHospital.setName(hospital.getName());
		updateHospital.setBeds(hospital.getBeds());
		updateHospital.setAddress(hospital.getAddress());
		
		return hospitalRepository.save(updateHospital);
	}
	
	public void deleteHospital(Hospital hospital) {
		Hospital deleteHospital = getHospitalById(hospital.getId());
		
		hospitalRepository.delete(deleteHospital);
	}

}
