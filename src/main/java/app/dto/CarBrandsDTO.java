package app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandsDTO {
	
	// Marca - montadora
	private Long id;
	private String name;
	private String cnpj;
	private String cep;
	
	// Carro
	private List<String> carName;
}
