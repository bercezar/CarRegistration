package app.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.CarBrands;
import app.repository.CarBrandsRepository;

@Service
public class CarBrandsService {
	@Autowired
	private CarBrandsRepository carBrandsRepository;
	
	public String saveAll(List<CarBrands> carBrands) {
		this.carBrandsRepository.saveAll(carBrands);
		return "Marcas salvos com sucesso";
	}
	
	public String save(CarBrands carBrands) {
		this.carBrandsRepository.save(carBrands);
		return "Marca salvo com sucesso";
	}
	
	public CarBrands findByCnpj(String cnpj) {
		Optional<CarBrands> carBrands = this.carBrandsRepository.findByCnpj(cnpj);
		return carBrands.get();
	}
	
	public List<CarBrands> findAll(){
		return this.carBrandsRepository.findAll();
	}
	
	public String deleteByCnpj(String cnpj){
		carBrandsRepository.deleteByCnpj(cnpj);
		return "Marca excluída com sucesso";
	}
	
	public CarBrands update(String cnpj, CarBrands updatedCarBrands) {
	    CarBrands existingCarBrands = carBrandsRepository.findByCnpj(cnpj)
	        .orElseThrow(() -> new RuntimeException("Marca com CNPJ " + cnpj + " não encontrado."));

	    existingCarBrands.setName(updatedCarBrands.getName());
	    existingCarBrands.setCnpj(updatedCarBrands.getCnpj());
	    existingCarBrands.setCep(updatedCarBrands.getCep());


	    return carBrandsRepository.save(existingCarBrands); 
	}
	
	public List<CarBrands> findByName(String name){
		return this.carBrandsRepository.findByName(name);
	}
}
