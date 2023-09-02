package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
//Where data is transformed from DTOs into the JPA entities.
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CustomerDao customerDao;
	
  @Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		copyPetStoreFields(petStore, petStoreData);
	
		return new PetStoreData(petStoreDao.save(petStore));
	}

	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
	
		
		
		petStore.setPetStoreId(petStoreData.getPetStoreId());	
	    petStore.setPetStoreName(petStoreData.getPetStoreName());
	    petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());	
	    petStore.setPetStoreCity(petStoreData.getPetStoreCity());
	    petStore.setPetStoreState(petStoreData.getPetStoreState());
	    petStore.setPetStoreZip(petStoreData.getPetStoreZip());
	    petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		
		if(Objects.isNull(petStoreId)) {
			return  new PetStore();
		}else {
			return findPetStoreById(petStoreId);
		}
	
	}
		private PetStore findPetStoreById(Long petStoreId) {
			return petStoreDao.findById(petStoreId)
					.orElseThrow(() -> new NoSuchElementException(
							"Pet Store with ID=" + petStoreId + " was not found."));
		}
        @Transactional(readOnly = false)
		public PetStoreEmployee saveEmployee( Long petStoreId, PetStoreEmployee petStoreEmployee) {
            PetStore petStore = findPetStoreById(petStoreId);
        
        	Employee employee = findOrCreateEmployee(petStoreId, petStoreEmployee.getEmployeeId());
            
        	copyEmployeeFields(employee, petStoreEmployee);
        	
        	employee.setPetStore(petStore);
        	petStore.getEmployees().add(employee);
        	
        	return new PetStoreEmployee (employeeDao.save(employee));
        }

		private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
			 employee.setEmployeeId(petStoreEmployee.getEmployeeId());
			 employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
			 employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
			 employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
			 employee.setEmployeeJobTitle(employee.getEmployeeJobTitle());
			
		}

		private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
			if(Objects.isNull(employeeId)) {  
			return new Employee();
		}else {
			return findEmployeeById(petStoreId, employeeId);
		}
		}	
		

		private Employee findEmployeeById(Long petStoreId, Long employeeId) {
			Employee employee =  employeeDao.findById(employeeId)
					.orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " was not found."));
			
			if(employee.getPetStore().getPetStoreId()!= petStoreId ) {
				throw new IllegalArgumentException(
						"Employee with ID="+ employeeId + " does not work at pet store with ID=" + petStoreId + ".");
			}
				return employee;
		}
		 @Transactional(readOnly = false)
			public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
				PetStore petStore = findPetStoreById(petStoreId); 
				Customer customer = findOrCreateCustomer(petStoreId, petStoreCustomer.getCustomerId());
			      
			      copyCustomerFields(customer, petStoreCustomer);
			      
			      customer.getPetStores().add(petStore);
			      
			      petStore.getCustomers().add(customer);
			    
				return new PetStoreCustomer (customerDao.save(customer));
			}

			private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
				customer.setCustomerId(petStoreCustomer.getCustomerId());
				customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
				customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
				customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
				
			}

			private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
				if(Objects.isNull(customerId)) {  
				return new Customer();
			}else {
				return findCustomerById(petStoreId, customerId);
			}
			}	
			

			private Customer findCustomerById(Long petStoreId, Long customerId) {
				Customer customer =  customerDao.findById(customerId)
						.orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));
				boolean found = false;
				
				for (PetStore petStore: customer.getPetStores()) {
					if (petStore.getPetStoreId()== petStoreId) {
						found = true;
						break;
						
					}
				}
				
				if(!found ) {
					throw new IllegalArgumentException(
							"Customer with ID="+ customerId + " does not shop at pet store with ID=" + petStoreId + ".");
				}
					return customer;
			
			}
            @Transactional(readOnly = true)
			public List<PetStoreData> retrieveAllPetStores() {
                 
            	List<PetStoreData> petStoreData = new LinkedList<>();
            	
            	for (PetStore petStore: petStoreDao.findAll()) {
            		
            		PetStoreData psd = new PetStoreData(petStore);
            		
            		psd.getCustomers().clear();
            		psd.getEmployees().clear();
            		petStoreData.add(psd);
            	}
            	
            	 return petStoreData;
            	}
             @Transactional(readOnly = true)
             public PetStoreData retrievePetStoreById(Long petStoreId) {
            	 
            	 return new PetStoreData(findPetStoreById(petStoreId));
             }
             
             public void deletePetStoreById(Long petStoreId) {
             	
             	PetStore petStore = findPetStoreById(petStoreId);
             	
             	petStoreDao.delete(petStore);
             	
             
             }
         }	
         	
             
       
             
			
				
			
			

	

