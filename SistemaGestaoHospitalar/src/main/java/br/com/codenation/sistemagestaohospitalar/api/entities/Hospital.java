package br.com.codenation.sistemagestaohospitalar.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String address;
	private Integer numberBeds;
	
	public Hospital() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="address", nullable=false)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="number_beds", nullable=false)
	public Integer getNumberBeds() {
		return numberBeds;
	}
	
	public void setNumberBeds(Integer numberBeds) {
		this.numberBeds = numberBeds;
	}
}
