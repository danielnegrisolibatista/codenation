package br.com.codenation.sistemagestaohospitalar.api.service;

import java.util.List;
import java.util.Optional;

import br.com.codenation.sistemagestaohospitalar.api.dto.HospitalDTO;
import br.com.codenation.sistemagestaohospitalar.api.entities.Hospital;

public interface HospitalService {

	List<Hospital> getHospitalAllList();
	
	//List<Hospital> getHospitalPageList(int page, int size, String sortDir, String sort);
	
	//List<Hospital> getHospitalByAddressList(String address);
	
	Hospital getHospitalById(Long id);
	
	//Hospital getHospitalByName(String name);
	
	//Hospital getHospitalByAddress(String address);
	
	Hospital createHospital(Hospital hospital);
	
	Hospital updateHospital(Hospital hospital);
	
	void deleteHospital(Hospital hospital);
}
