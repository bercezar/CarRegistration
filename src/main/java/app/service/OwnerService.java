package app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.OwnerDTO;
import app.entity.Car;
import app.entity.Owner;
import app.repository.OwnerRepository;

@Service
public class OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;
	
	public OwnerDTO convertToDto(Owner owner) {
        OwnerDTO dto = new OwnerDTO();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setCpf(owner.getCpf()); 

        if (owner.getCars() != null && !owner.getCars().isEmpty()) {
            dto.setChassis(owner.getCars().stream()
                                    .map(Car::getChassis)
                                    .collect(Collectors.toList()));
        } else {
            dto.setChassis(List.of()); 
        }
        return dto;
	}
	
	public String saveAll(List<Owner> owner) {
		this.ownerRepository.saveAll(owner);
		return "Carros salvos com sucesso";
	}
	
	public String save(Owner owner) {
		this.ownerRepository.save(owner);
		return "Proprietário " + owner+  " salvo com sucesso";
	}
	
	public OwnerDTO findByCpf(String cpf) {
		Optional<Owner> owner = this.ownerRepository.findByCpf(cpf);
		return owner.map(this::convertToDto) 
                    .orElseThrow(() -> new RuntimeException("Proprietário com CPF " + cpf + " não encontrado."));
	}
	
	public List<OwnerDTO> findAll(){
		return this.ownerRepository.findAll().stream()
                .map(this::convertToDto) 
                .collect(Collectors.toList());
	}
	
	public String deleteByCpf(String cpf){
		ownerRepository.deleteByCpf(cpf);
		return "Proprietário excluído com sucesso";
	}
	

    public OwnerDTO update(String cpf, Owner updatedOwner) { // Retorno alterado para OwnerDTO
        Owner existingOwner = ownerRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Owner com CPF " + cpf + " não encontrado.")); // CORREÇÃO AQUI

        existingOwner.setName(updatedOwner.getName());
        existingOwner.setCpf(updatedOwner.getCpf()); // Você está atualizando o CPF. Cuidado se ele for um identificador único para o update.

        // Retorna o DTO do Owner salvo
        return convertToDto(ownerRepository.save(existingOwner));
    }
	
	public List<OwnerDTO> findByName(String name){
		return this.ownerRepository.findByName(name).stream()
                .map(this::convertToDto) // Converte cada entidade para DTO
                .collect(Collectors.toList());
	}
}
