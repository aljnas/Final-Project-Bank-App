package bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.entity.Client;

public interface ClientDao extends JpaRepository<Client, Long>{

}