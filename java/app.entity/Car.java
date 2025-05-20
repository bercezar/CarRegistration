package app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String chassis;
  private String name;
  private String brand;
  private String model;
  private int manufacturingYear;
  private String transmission;
  private int power;
  
  public int getManufacturingYear() {
      return manufacturingYear;
  }
  public void setManufacturingYear(int manufacturingYear) {
      this.manufacturingYear = manufacturingYear;
  }
  public String getTransmission() {
      return transmission;
  }
  public void setTransmission(String transmission) {
      this.transmission = transmission;
  }
  public Long getId() {
      return id;
  }
  public void setId(Long id) {
      this.id = id;
  }
  public String getName() {
      return name;
  }
  public void setName(String name) {
      this.name = name;
  }
  public String getBrand() {
      return brand;
  }
  public void setBrand(String brand) {
      this.brand = brand;
  }
  public String getModel() {
      return model;
  }
  public void setModel(String model) {
      this.model = model;
  }
  public int getPower() {
      return power;
  }
  public void setPower(int power) {
      this.power = power;
  }
  public String getChassis() {
      return chassis;
  }
  public void setChassis(String chassis) {
      this.chassis = chassis;
  }

	
	
}
