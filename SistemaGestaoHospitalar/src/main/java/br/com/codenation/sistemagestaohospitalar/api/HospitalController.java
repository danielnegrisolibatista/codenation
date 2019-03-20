package br.com.codenation.sistemagestaohospitalar.api;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.sistemagestaohospitalar.api.dto.HospitalDTO;
import br.com.codenation.sistemagestaohospitalar.api.entities.Hospital;
import br.com.codenation.sistemagestaohospitalar.api.response.Response;
import br.com.codenation.sistemagestaohospitalar.api.service.HospitalService;


@RestController
@RequestMapping("/v1/hospitais")
public class HospitalController {

	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private HospitalService hospitalService;
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<HospitalDTO> getHospitalsAll() {
        List<Hospital> hospitals = hospitalService.getHospitalAllList();
        
        return hospitals
        		.stream()
        		.map(post -> convertToDto(post))
        		.collect(Collectors.toList());
    }
	
	/*
	@RequestMapping(params = { "page", "size" }, method = RequestMethod.GET)
    @ResponseBody
    public List<HospitalDTO> getHospitalsPaginated(
    			@RequestParam("page") int page, 
    			@RequestParam("size") int size, 
    			String sortDir, 
    			String sort) {
		
        List<Hospital> hospitals = hospitalService.getHospitalPageList(page, size, sortDir, sort);
        
        return hospitals
        		.stream()
        		.map(post -> convertToDto(post))
        		.collect(Collectors.toList());
    }*/
	
	@GetMapping(path = "/{id}")
    public HospitalDTO getHospital(@PathVariable("id") Long id) {
        return convertToDto(hospitalService.getHospitalById(id));
    }
	
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
	public ResponseEntity<Response<HospitalDTO>> createHospital(
		@Valid @RequestBody HospitalDTO hospitalDTO, BindingResult result){
		
		Response<HospitalDTO> response =  new Response<HospitalDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			Hospital hospital = convertToEntity(hospitalDTO);
			
			Hospital hospitalCreated = hospitalService.createHospital(hospital);
	
			response.setData(convertToDto(hospitalCreated)); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateHospital(@RequestBody HospitalDTO hospitalDTO) {
		Hospital hospital;
		try {
			hospital = convertToEntity(hospitalDTO);
			
			hospitalService.updateHospital(hospital); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private HospitalDTO convertToDto(Hospital hospital) {
		HospitalDTO hospitalDTO = modelMapper.map(hospital, HospitalDTO.class);
		
	    return hospitalDTO;
	}
	
	private Hospital convertToEntity(HospitalDTO hospitalDTO) throws ParseException {
		Hospital hospital = modelMapper.map(hospitalDTO, Hospital.class);
		
	    return hospital;
	}
}
