package br.com.codenation.sistemagestaohospitalar.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.codenation.sistemagestaohospitalar.api.entities.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	/*
	public Hospital findByName(String pName);
	
	public Hospital findByAddress(String pAddress);
	    
    @Query("SELECT h FROM Hospital h WHERE h.address like ?1% and h.numberBeds > 0")
    public List<Hospital> findAllByAddress(String pAddress);
	 */
	
}
