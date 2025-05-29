package app.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dto.CarBrandsDTO;
import app.entity.CarBrands;
import app.service.CarBrandsService;


@RestController
@RequestMapping("/carBrands")
public class CarBrandsController {
	@Autowired
	private CarBrandsService carBrandsService; 
	
	@PostMapping("/saveAll") 
	public ResponseEntity<String> saveAll(@RequestBody List<CarBrands> carBrands){
		try {
			String message = this.carBrandsService.saveAll(carBrands);
			return ResponseEntity.ok(message);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/save") 
	public ResponseEntity<String> save(@RequestBody CarBrands carBrands){
		try {
			String message = this.carBrandsService.save(carBrands);
			return ResponseEntity.ok(message);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findByCnpj/{cnpj}")
	public ResponseEntity<CarBrandsDTO> findByCnpj(@PathVariable String cnpj){
		try {
			CarBrandsDTO carBrandsDTO = this.carBrandsService.findByCnpj(cnpj);
			return new ResponseEntity<CarBrandsDTO>(carBrandsDTO, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findAll")
		public ResponseEntity<List<CarBrandsDTO>> findAll(){
			try {
				List<CarBrandsDTO> carsBrandsDTO = this.carBrandsService.findAll();
				return new ResponseEntity<>(carsBrandsDTO, HttpStatus.OK);
				
			}catch(Exception e){
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
	
	@DeleteMapping("/deleteByCnpj/{cnpj}")
		public ResponseEntity<String> deleteByCnpj(@PathVariable String cnpj){
			try {
				String message = this.carBrandsService.deleteByCnpj(cnpj);
				return new ResponseEntity<>(message, HttpStatus.OK);
			}catch(Exception e){
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
		}
	
	@PutMapping("/update/{cnpj}")
	public ResponseEntity<CarBrands> update(@PathVariable String cnpj, @RequestBody CarBrands carBrands) {
	    try {
	        CarBrands updated = carBrandsService.update(cnpj, carBrands);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping("/findByName")
	public ResponseEntity<List<CarBrandsDTO>> findByName(@RequestParam String name){
		try{
			List<CarBrandsDTO> listCarsBrandsDTO = this.carBrandsService.findByName(name);
			return new ResponseEntity<>(listCarsBrandsDTO, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
