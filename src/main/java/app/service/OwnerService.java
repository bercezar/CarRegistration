package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Owner;
import app.repository.OwnerRepository;

@Service
public class OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;
	
	public String saveAll(List<Owner> owner) {
		this.ownerRepository.saveAll(owner);
		return "Carros salvos com sucesso";
	}
	
	public String save(Owner owner) {
		this.ownerRepository.save(owner);
		return "Proprietário " + owner+  " salvo com sucesso";
	}
	
	public Owner findByCpf(String cpf) {
		Optional<Owner> owner = this.ownerRepository.findByCpf(cpf);
		return owner.get();
	}
	
	public List<Owner> findAll(){
		return this.ownerRepository.findAll();
	}
	
	public String deleteByCpf(String cpf){
		ownerRepository.deleteByCpf(cpf);
		return "Proprietário excluído com sucesso";
	}
	
	public Owner update(String cpf, Owner updatedOwner) {
	    Owner existingOwner = ownerRepository.findByCpf(cpf)
	        .orElseThrow(() -> new RuntimeException("Owner com CPF " + cpf + " não encontrado."));

	    existingOwner.setName(updatedOwner.getName());
	    existingOwner.setCpf(updatedOwner.getCpf());

	    return ownerRepository.save(existingOwner); 
	}
	
	public List<Owner> findByName(String name){
		return this.ownerRepository.findByName(name);
	}
}
