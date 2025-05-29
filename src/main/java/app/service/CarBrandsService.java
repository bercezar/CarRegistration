package app.service;
import java.util.List; 
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.CarBrandsDTO;
import app.entity.Car;
import app.entity.CarBrands;
import app.repository.CarBrandsRepository;

@Service
public class CarBrandsService {
	@Autowired
	private CarBrandsRepository carBrandsRepository;
	
	public CarBrandsDTO convertToDto(CarBrands carBrands) {
		CarBrandsDTO dto = new CarBrandsDTO();
		dto.setId(carBrands.getId());
		dto.setName(carBrands.getName());
		dto.setCnpj(carBrands.getCnpj());
		dto.setCep(carBrands.getCep());
		
        if (carBrands.getCars() != null && !carBrands.getCars().isEmpty()) {
            dto.setCarName(carBrands.getCars().stream()
                                    .map(Car::getName)
                                    .collect(Collectors.toList()));
        }else {
        	dto.setCarName(List.of());
        }
		return dto;
	}
	
	public String saveAll(List<CarBrands> carBrands) {
		this.carBrandsRepository.saveAll(carBrands);
		return "Marcas salvos com sucesso";
	}
	
	public String save(CarBrands carBrands) {
		this.carBrandsRepository.save(carBrands);
		return "Marca salvo com sucesso";
	}
	
	public CarBrandsDTO findByCnpj(String cnpj) {
		Optional<CarBrands> carBrands = this.carBrandsRepository.findByCnpj(cnpj);
		return carBrands.map(this::convertToDto) 
                .orElseThrow(() -> new RuntimeException("Marca com CNPJ " + cnpj + " não encontrado."));
	}
	
	public List<CarBrandsDTO> findAll(){
		return this.carBrandsRepository.findAll().stream()
        .map(this::convertToDto) 
        .collect(Collectors.toList());

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
	
	public List<CarBrandsDTO> findByName(String name){
        return this.carBrandsRepository.findByName(name).stream()
                .map(this::convertToDto) // Converte cada entidade para DTO
                .collect(Collectors.toList());
	}
}
