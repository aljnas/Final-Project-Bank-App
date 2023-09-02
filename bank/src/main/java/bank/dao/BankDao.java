package bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.entity.Bank;

public interface BankDao extends JpaRepository<Bank, Long> {

}
