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

import app.dto.CarDTO;
import app.entity.Car;
import app.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarService carService; 
	
	@PostMapping("/saveAll") 
	public ResponseEntity<String> saveAll(@RequestBody List<Car> cars){
	    try {
	        String result = carService.saveAll(cars);
	        return ResponseEntity.ok(result);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
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
	
	@GetMapping("/findByChassis")
	public ResponseEntity<CarDTO> findByChassis(@RequestParam String chassis){
		try {
			CarDTO carDTO = this.carService.findByChassis(chassis);
			return new ResponseEntity<CarDTO>(carDTO, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	} // Implementar melhorias no retorno, sem loops infinitos de serialização
	
	@GetMapping("/findAll")
		public ResponseEntity<List<CarDTO>> findAll(){
			try {
				List<CarDTO> carsDTO = this.carService.findAll();
				return new ResponseEntity<>(carsDTO, HttpStatus.OK);
				
			}catch(Exception e){
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
	
	@DeleteMapping("/deleteByChassis")
		public ResponseEntity<String> deleteByChassis(@RequestParam String chassis){
			try {
				String message = this.carService.deleteByChassis(chassis);
				return new ResponseEntity<>(message, HttpStatus.OK);
			}catch(Exception e){
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
		}
	
	@PutMapping("/update/{chassis}")
	public ResponseEntity<CarDTO> update(@PathVariable String chassis, @RequestBody Car car) {
	    try {
	        CarDTO updated = carService.update(chassis, car);
	        return new ResponseEntity<CarDTO>(updated, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}

	@GetMapping("/findByName")
	public ResponseEntity<List<CarDTO>> findByName(@RequestParam String name){
		try{
			List<CarDTO> listCarsDTO = this.carService.findByName(name);
			return new ResponseEntity<>(listCarsDTO, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	} // Implementar melhorias no retorno, sem loops infinitos de serialização

	@GetMapping("/findByCarBrands")
	public ResponseEntity<List<CarDTO>> findByCarBrands(@RequestParam Long idCarBrands){
		try{
			List<CarDTO> listCarsDTO = this.carService.findByCarBrands(idCarBrands);
			return new ResponseEntity<>(listCarsDTO, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}// Implementar melhorias no retorno, sem loops infinitos de serialização
}



























