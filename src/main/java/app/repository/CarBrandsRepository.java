package app.repository;

import java.util.List; 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.CarBrands;
public interface CarBrandsRepository extends JpaRepository<CarBrands, Long> {
	Optional<CarBrands> deleteByCnpj(String cnpj);
	Optional<CarBrands> findByCnpj(String cnpj);
	public List<CarBrands> findByName(String name);
	public List<CarBrands> findByCep(String cep);
}
