package br.com.codenation.sistemagestaohospitalar.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class HospitalDTO {
	private Long id;
	private String name;
	private String address;
	private Integer numberBeds;
	
	public HospitalDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message="Nome não pode ser vazio")
	@Size(max = 255, message="Nome não pode ser maior que 255 caracteres")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message="Endereço não pode ser vazio")
	@Size(max = 255, message="Endereço não pode ser maior que 255 caracteres")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotEmpty(message="Número de leitos não pode ser vazio")
	public Integer getNumberBeds() {
		return numberBeds;
	}

	public void setNumberBeds(Integer numberBeds) {
		this.numberBeds = numberBeds;
	}
	
	
}
