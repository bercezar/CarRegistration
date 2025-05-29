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
public class CarDTO {

	// Carro
	private Long id;
    private String chassis;
    private String name;
    private String model;
    private int manufacturingYear;
    private String transmission;
    private int power;
    
    
    // Marca - Montadora
    private String nameCarBrands;
    
    // Dono - proprietário
    private List<Long> idOwner;
    private List<String> nameOwner;
	
}
