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

import app.entity.Owner;
import app.service.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;

	@PostMapping("/saveAll") 
	public ResponseEntity<String> saveAll(@RequestBody List<Owner> owner){
		try {
			String message = this.ownerService.saveAll(owner);
			return ResponseEntity.ok(message);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/save") 
	public ResponseEntity<String> save(@RequestBody Owner owner){
		try {
			String message = this.ownerService.save(owner);
			return ResponseEntity.ok(message);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findByCpf/{cpf}")
	public ResponseEntity<Owner> findByCpf(@PathVariable String cpf){
		try {
			Owner owner = this.ownerService.findByCpf(cpf);
			return new ResponseEntity<>(owner, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Owner>> findAll(){
		try {
			List<Owner> owners = this.ownerService.findAll();
			return new ResponseEntity<>(owners, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteByCpf/{cpf}")
	public ResponseEntity<String> deleteByCpf(@PathVariable String cpf){
		try {
			String message = this.ownerService.deleteByCpf(cpf);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/update/{cpf}")
	public ResponseEntity<Owner> update(@PathVariable String cpf, @RequestBody Owner owner) {
	    try {
	        Owner updated = ownerService.update(cpf, owner);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}	
	
	@GetMapping("/findByName")
	public ResponseEntity<List<Owner>> findByName(@RequestParam String name){
		try{
			List<Owner> listOwners = this.ownerService.findByName(name);
			return new ResponseEntity<>(listOwners, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
