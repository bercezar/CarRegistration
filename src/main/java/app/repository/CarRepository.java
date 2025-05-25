package app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Car;
import app.entity.CarBrands;


public interface CarRepository extends JpaRepository<Car, Long> {
	public List<Car> findByName(String name);
	Optional<Car> findByChassis(String chassis);
	void deleteByChassis(String chassis);
	public List<Car> findByCarBrands(CarBrands carBrands);
}
