package bank.controller.model;

import bank.entity.Client;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class BankClient {


		private Long clientId;
		
	    private String clientName;
		private String clientLastName;
		private String clientEmail;


    public BankClient(Client client) {
	clientId = client.getClientId();
	clientName = client.getClientName();
    clientLastName = client.getClientLastName();	
    clientEmail = client.getClientEmail();
    }}

