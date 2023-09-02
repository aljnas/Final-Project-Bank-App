package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class PetStore {
	
 //Identifies the primary key for the pet_store table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petStoreId;
	
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	
	//Many to many relationship with customer.Joins the tables by table,
	//pet_store_customer via pet_store_id and custumer_id
	

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "pet_store_customer", joinColumns = @JoinColumn(name = "pet_store_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	

	
	//One to many relationship with employee table.Because one pet store has multiple employees.
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Customer>customers = new HashSet<>();
	@OneToMany(mappedBy = "petStore",cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Employee>employees = new HashSet<>();
}
	
