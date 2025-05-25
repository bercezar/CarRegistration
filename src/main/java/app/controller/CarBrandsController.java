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
	public ResponseEntity<CarBrands> findByCnpj(@PathVariable String cnpj){
		try {
			CarBrands carBrands = this.carBrandsService.findByCnpj(cnpj);
			return new ResponseEntity<CarBrands>(carBrands, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findAll")
		public ResponseEntity<List<CarBrands>> findAll(){
			try {
				List<CarBrands> carsBrands = this.carBrandsService.findAll();
				return new ResponseEntity<>(carsBrands, HttpStatus.OK);
				
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
	public ResponseEntity<List<CarBrands>> findByName(@RequestParam String name){
		try{
			List<CarBrands> listCarsBrands = this.carBrandsService.findByName(name);
			return new ResponseEntity<>(listCarsBrands, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
