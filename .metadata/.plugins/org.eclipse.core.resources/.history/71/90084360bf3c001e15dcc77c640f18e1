package pet.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data                                                                                                                                                                                                                                                                                                                                                                                            
public class Employee {
  
	//Allows know the primary key for the employee table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employee;
	
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhone;
	private String employeeJobTittle;
	
	// Many to one relationship with pet_store
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	// Joins tables by pet_store_id
	@JoinColumn(name = "pet_store_id")
	private PetStore petStore;
	
}
