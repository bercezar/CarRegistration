package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import app.entity.Car;
import app.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public String saveAll(List<Car> car) {
		this.carRepository.saveAll(car);
		return "Carro salvo com sucesso";
	}
	public Car findById(Long id) {
		Optional<Car> car = this.carRepository.findById(id);
		return car.get();
	}
}
