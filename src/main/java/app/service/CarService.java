package app.service;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Car;
import app.entity.CarBrands;
import app.entity.Owner;
import app.repository.CarRepository;
import app.repository.OwnerRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	public String saveAll(List<Car> cars) {
		for(Car car: cars) {
			List<Owner> attachedOwners = new ArrayList<>();
			for(Owner owner : car.getOwners()) {
	            Owner attached = ownerRepository.findById(owner.getId())
	                    .orElseThrow(() -> new RuntimeException("Owner not found"));
	                attachedOwners.add(attached);
			}
			car.setOwners(attachedOwners);
			carRepository.save(car);
		}
		return "Carros salvos com sucesso";
	}
	
	public String save(Car car) {
	    List<Owner> attachedOwners = new ArrayList<>();
	    for (Owner owner : car.getOwners()) {
	        Owner attached = ownerRepository.findById(owner.getId())
	            .orElseThrow(() -> new RuntimeException("Owner not found"));
	        attachedOwners.add(attached);
	    }
	    car.setOwners(attachedOwners);
	    this.carRepository.save(car);
	    return "Carro salvo com sucesso";
	}
	
	public Car findByChassis(String chassis) {
		Optional<Car> car = this.carRepository.findByChassis(chassis);
		return car.get();
	}
	
	public List<Car> findAll(){
		return this.carRepository.findAll();
	}
	
	public String deleteByChassis(String chassis){
		carRepository.deleteByChassis(chassis);
		return "Carro excluído com sucesso";
	}
	
	public Car update(String chassis, Car updatedCar) {
	    Car existingCar = carRepository.findByChassis(chassis)
	        .orElseThrow(() -> new RuntimeException("Carro com ID " + chassis + " não encontrado."));

	    existingCar.setName(updatedCar.getName());
	    existingCar.setModel(updatedCar.getModel());
	    existingCar.setTransmission(updatedCar.getTransmission());
	    existingCar.setPower(updatedCar.getPower());
	    existingCar.setManufacturingYear(updatedCar.getManufacturingYear());

	    return carRepository.save(existingCar); 
	}
	
	public List<Car> findByName(String name){
		return this.carRepository.findByName(name);
	}
	
	public List<Car> findByCarBrands(Long idCarBrands){
		CarBrands carBrands = new CarBrands();
		carBrands.setId(idCarBrands);
		return this.carRepository.findByCarBrands(carBrands);
	}
}
