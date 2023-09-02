package bank.entity;





import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private  Long accountId;
	
	private String accountNumber;
	private String accountBalance;

	
@EqualsAndHashCode.Exclude
@ToString.Exclude
@ManyToMany(cascade = CascadeType.PERSIST)
@JoinTable(name = "client_account", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
private List<Client> clients;

@EqualsAndHashCode.Exclude
@ToString.Exclude
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "bank_id")
private Bank bank;
}