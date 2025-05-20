package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
