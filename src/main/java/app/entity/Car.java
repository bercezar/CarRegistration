package app.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String chassis;
	private String name;
	private String model;
	private int manufacturingYear;
	private String transmission;
	private int power;

	@ManyToOne  //Vários carros pertencem a uma marca
	@JoinColumn(name = "car_brand_id")
    @JsonIgnore
	private CarBrands carBrands;
	
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name = "car_owner",
      joinColumns = @JoinColumn(name = "car_id"),
      inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private List<Owner> owners;
}
