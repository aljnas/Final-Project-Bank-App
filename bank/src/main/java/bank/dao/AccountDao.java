package bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.entity.Account;

public interface AccountDao extends  JpaRepository<Account, Long>{

}