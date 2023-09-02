package bank.controller.model;

import java.util.HashSet;
import java.util.Set;

import bank.entity.Account;
import bank.entity.Bank;
import bank.entity.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankData {

	private Long bankId;
    
	private String bankName;
	private String bankAddress;
	
	private Set<BankClient> clients = new HashSet<>();
	private Set<BankAccount> accounts = new HashSet<>();
	
	public BankData(Bank bank) {
		bankId = bank.getBankId();
		bankName = bank.getBankName();
		bankAddress = bank.getBankAddress();
	
	
	
	
	for (Client client : bank.getClients()) {
		BankClient bankClient = new BankClient(client);
		clients.add(bankClient);
	}
	 for (Account account: bank.getAccounts()) {
		 BankAccount bankAccount = new BankAccount(account);
		 accounts.add(bankAccount);
	 }
}
}

