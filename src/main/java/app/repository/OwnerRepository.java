package app.repository;
import java.util.List; 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Owner;


public interface OwnerRepository extends JpaRepository<Owner, Long> {
	public List<Owner> findByName(String name);
	Optional<Owner> findByCpf(String cpf);
	Optional<Owner> deleteByCpf(String cpf);
}
