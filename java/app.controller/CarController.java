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
import org.springframework.web.bind.annotation.RestController;

import app.entity.Car;
import app.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarService carService; 
	
	@PostMapping("/saveAll") 
	public ResponseEntity<String> saveAll(@RequestBody List<Car> car){
		try {
			String message = this.carService.saveAll(car);
			return ResponseEntity.ok(message);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/save") 
	public ResponseEntity<String> save(@RequestBody Car car){
		try {
			String message = this.carService.save(car);
			return ResponseEntity.ok(message);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Car> findById(@PathVariable Long id){
		try {
			Car car = this.carService.findById(id);
			return new ResponseEntity<Car>(car, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/findAll")
		public ResponseEntity<List<Car>> findAll(Car car){
			try {
				List<Car> cars = this.carService.findAll();
				return ResponseEntity.ok(cars);
				
			}catch(Exception e){
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
	
	@DeleteMapping("/deleteById/{id}")
		public ResponseEntity<String> deleteById(@PathVariable Long id){
			String message = this.carService.deleteById(id);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Car> update(@PathVariable Long id, @RequestBody Car car) {
	    try {
	        Car updated = carService.update(id, car);
	        return ResponseEntity.ok(updated);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}

}

