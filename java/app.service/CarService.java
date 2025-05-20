package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Car;
import app.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public String saveAll(List<Car> car) {
		this.carRepository.saveAll(car);
		return "Carros salvos com sucesso";
	}
	
	public String save(Car car) {
		this.carRepository.save(car);
		return "Carro salvo com sucesso";
	}
	
	public Car findById(Long id) {
		Optional<Car> car = this.carRepository.findById(id);
		return car.get();
	}
	
	public List<Car> findAll(){
		return this.carRepository.findAll();
	}
	
	public String deleteById(Long id){
		carRepository.deleteById(id);
		return "Carro excluído com sucesso";
	}
	
	public Car update(Long id, Car updatedCar) {
	    Car existingCar = carRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Carro com ID " + id + " não encontrado."));

	    existingCar.setName(updatedCar.getName());
	    existingCar.setBrand(updatedCar.getBrand());
	    existingCar.setModel(updatedCar.getModel());
	    existingCar.setChassis(updatedCar.getChassis());
	    existingCar.setTransmission(updatedCar.getTransmission());
	    existingCar.setPower(updatedCar.getPower());
	    existingCar.setManufacturingYear(updatedCar.getManufacturingYear());

	    return carRepository.save(existingCar); 
	}

}
