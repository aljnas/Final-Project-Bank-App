package bank.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Entity
@Data
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	

			private Long clientId;
			
		    private String clientName;
			private String clientLastName;
			private String clientEmail;
		

@EqualsAndHashCode.Exclude
@ToString.Exclude
@ManyToMany(mappedBy = "clients", cascade = CascadeType.PERSIST)
private Set<Bank> banks = new HashSet<>();
}

	
		
	
	
		
    

	
	
