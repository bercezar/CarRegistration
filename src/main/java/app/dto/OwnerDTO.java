package app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {
	
	// Dono - proprietário
	private Long id;
	private String name;
	private String cpf;
	
	
	// Carro
	private List<String> chassis;
	
}
