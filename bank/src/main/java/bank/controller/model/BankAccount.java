package bank.controller.model;

import bank.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class BankAccount{
	
private  Long accountId;
	
	private String accountNumber;
	private String accountBalance;
	
	public BankAccount(Account account) {
		accountId = account.getAccountId();
		accountNumber = account.getAccountNumber();
		accountBalance = account.getAccountBalance();
		

	}

}
