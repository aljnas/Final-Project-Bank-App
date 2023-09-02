package bank.entity;
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
public class Bank {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long bankId;
	    
		private String bankName;
		private String bankAddress;
		
		//@OneToMany(mappedBy = "bank")
		//private List <Client> clients ;
	
//}
		@ManyToMany(cascade = CascadeType.PERSIST)
		@JoinTable(name = "bank_client", joinColumns = @JoinColumn(name = "bank_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
		

		
		//One to many relationship with employee table.Because one pet store has multiple employees.
		@EqualsAndHashCode.Exclude
		@ToString.Exclude
		private Set<Client>clients = new HashSet<>();
		@OneToMany(mappedBy = "bank",cascade = CascadeType.ALL, orphanRemoval = true)
		@EqualsAndHashCode.Exclude
		@ToString.Exclude
		private Set<Account>accounts = new HashSet<>();
	}
		
		/*@ManyToMany(cascade = CascadeType.PERSIST)
		@JoinTable(name = "bank_client", joinColumns = @JoinColumn(name = "bank_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Client>clients = new HashSet<>();*/
	/*@OneToMany(mappedBy = "bank",cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Account>accounts = new HashSet<>();*/


	
	
	


  