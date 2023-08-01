package BankingSystem.dao;

import java.sql.SQLException;

import BankingSystem.classes.Account;

public interface AccountDAO {
	Account getAccountById(int id) throws SQLException;
	boolean addAccount(Account p) throws SQLException;

}
