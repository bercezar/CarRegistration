package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.CarDTO;
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
	
	public CarDTO convertToDto(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setChassis(car.getChassis());
        dto.setName(car.getName());
        dto.setModel(car.getModel());
        dto.setManufacturingYear(car.getManufacturingYear());
        dto.setTransmission(car.getTransmission());
        dto.setPower(car.getPower());


        if (car.getCarBrands() != null) {
            dto.setNameCarBrands(car.getCarBrands().getName()); 
        }

        if (car.getOwners() != null && !car.getOwners().isEmpty()) {
            dto.setIdOwner(car.getOwners().stream()
                                      .map(Owner::getId) 
                                      .collect(Collectors.toList()));
            dto.setNameOwner(car.getOwners().stream()
                                      .map(Owner::getName) 
                                      .collect(Collectors.toList()));
        } else {
            dto.setIdOwner(List.of()); // Garante lista vazia ao invés de null
            dto.setNameOwner(List.of());
        }
        return dto;
    }
	
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
	
    public CarDTO findByChassis(String chassis) {
        Optional<Car> car = this.carRepository.findByChassis(chassis);
        return car.map(this::convertToDto) 
                  .orElseThrow(() -> new RuntimeException("Carro com chassi " + chassis + " não encontrado."));
    }
	
    public List<CarDTO> findAll(){
        return this.carRepository.findAll().stream()
                .map(this::convertToDto) // Converte cada entidade para DTO
                .collect(Collectors.toList());
    }
    
    public String deleteByChassis(String chassis){
        carRepository.deleteByChassis(chassis);
        return "Carro excluído com sucesso";
    }
	
	public CarDTO update(String chassis, Car updatedCar) {
	    Car existingCar = carRepository.findByChassis(chassis)
	        .orElseThrow(() -> new RuntimeException("Carro com ID " + chassis + " não encontrado."));

	    existingCar.setName(updatedCar.getName());
	    existingCar.setModel(updatedCar.getModel());
	    existingCar.setTransmission(updatedCar.getTransmission());
	    existingCar.setPower(updatedCar.getPower());
	    existingCar.setManufacturingYear(updatedCar.getManufacturingYear());

	    return convertToDto(carRepository.save(existingCar));
	}
	
    public List<CarDTO> findByName(String name){
        return this.carRepository.findByName(name).stream()
                .map(this::convertToDto) 
                .collect(Collectors.toList());
    }
	
    public List<CarDTO> findByCarBrands(Long idCarBrands){
        CarBrands carBrands = new CarBrands();
        carBrands.setId(idCarBrands);
        List<Car> listCars = this.carRepository.findByCarBrands(carBrands); 

        return listCars.stream()
                .map(this::convertToDto) 
                .collect(Collectors.toList());
    }
}
