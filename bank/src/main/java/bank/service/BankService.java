package bank.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.controller.model.BankAccount;
import bank.controller.model.BankClient;
import bank.controller.model.BankData;
import bank.dao.AccountDao;
import bank.dao.BankDao;
import bank.dao.ClientDao;
import bank.entity.Account;
import bank.entity.Bank;
import bank.entity.Client;

@Service
//Where data is transformed from DTOs into the JPA entities.
public class BankService {
	
	@Autowired
	private BankDao bankDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ClientDao clientDao;
	
  @Transactional(readOnly = false)
	public BankData saveBank(BankData bankData) {
		Long bankId = bankData.getBankId();
		Bank bank = findOrCreateBank(bankId);
		
		copyBankFields(bank, bankData);
	
		return new BankData(bankDao.save(bank));
	}

	private void copyBankFields(Bank bank, BankData bankData) {
	
		
		
		bank.setBankId(bankData.getBankId());	
	    bank.setBankName(bankData.getBankName());
	    bank.setBankAddress(bankData.getBankAddress());	
	}

	private Bank findOrCreateBank(Long bankId) {
		
		if(Objects.isNull(bankId)) {
			return  new Bank();
		}else {
			return findBankById(bankId);
		}
	
	}
		private Bank findBankById(Long bankId) {
			return bankDao.findById(bankId)
					.orElseThrow(() -> new NoSuchElementException(
							"Bank with ID=" + bankId + " was not found."));
		}
        @Transactional(readOnly = false)
		public BankAccount saveAccount( Long bankId, BankAccount bankAccount) {
            Bank bank  = findBankById(bankId);
        	Account account = findOrCreateAccount(bankId, bankAccount.getAccountId());
            
        	copyAccountFields(account, bankAccount);
        	
        	account.setBank(bank);
        	bank.getAccounts().add(account);
        	
        	return new BankAccount (accountDao.save(account));
        }

		private void copyAccountFields(Account account, BankAccount bankAccount) {
			 account.setAccountId(bankAccount.getAccountId());
			 
			 account.setAccountNumber(bankAccount.getAccountNumber());
			 account.setAccountBalance(bankAccount.getAccountBalance());
		
			 
			
		//		private String accountNumber;
		//		private String accountBalance;
				
		}

		private Account findOrCreateAccount(Long bankId, Long accountId) {
			if(Objects.isNull(accountId)) {  
			return new Account();
		}else {
			return findAccountById(bankId, accountId);
		}
		}	
		

		private Account findAccountById(Long bankId, Long accountId) {
			Account account =  accountDao.findById(accountId)
					.orElseThrow(() -> new NoSuchElementException("Account with ID=" + accountId + " was not found."));
			
			if(account.getBank().getBankId()!= bankId ) {
				throw new IllegalArgumentException(
						"Account with ID="+ accountId + " does not belong to bank  with ID=" + bankId + ".");
			}
			return account;
		}
		 @Transactional(readOnly = false)
			public BankClient saveClient(Long bankId, BankClient bankClient) {
				Bank bank = findBankById(bankId); 
				Client client = findOrCreateClient(bankId, bankClient.getClientId());
			      
			      copyClientFields(client, bankClient);
			      
			      client.getBanks();//.add(bank);
			      
			      bank.getClients().add(client);
			    
				return new BankClient (clientDao.save(client));
			}

			private void copyClientFields(Client client, BankClient bankClient) {
				client.setClientId(bankClient.getClientId());
				
				client.setClientName(bankClient.getClientName());
				client.setClientLastName(bankClient.getClientLastName());
				client.setClientEmail(bankClient.getClientEmail());
					
			}

			private Client findOrCreateClient(Long bankId, Long clientId) {
				if(Objects.isNull(clientId)) {  
				return new Client();
			}else {
				return findClientById(bankId, clientId);
			}
			}	
			

			private Client findClientById(Long bankId, Long clientId) {
				Client client =  clientDao.findById(clientId)
						.orElseThrow(() -> new NoSuchElementException("Client with ID=" + clientId + " was not found."));
				boolean found = false;
				
				for (Bank bank: client.getBanks()) {
					if (bank.getBankId()== bankId) {
						found = true;
						break;
						
					}
				}
				
				if(!found ) {
					throw new IllegalArgumentException(
							"Client with ID="+ clientId + " does not belongsto bank with  ID=" + bankId + ".");
				}
					return client;
			
			}
            @Transactional(readOnly = true)
			public List<BankData> retrieveAllBanks() {
                 
            	List<BankData> bankData = new LinkedList<>();
            	
            	for (Bank bank: bankDao.findAll()) {
            		
            		BankData bd = new BankData(bank);
            		
            		bd.getClients().clear();
            		bd.getAccounts().clear();
            		bankData.add(bd);
            	}
            	
            	 return bankData;
            	}
             @Transactional(readOnly = true)
             public BankData retrieveBankById(Long bankId) {
            	 
            	 return new BankData(findBankById(bankId));
             }
             
             public void deleteBankById(Long bankId) {
             	
             	Bank bank = findBankById(bankId);
             	
             	bankDao.delete(bank);
             	
             
             }
 
         }	
         	
             
       
             
			
				
			
			

	

