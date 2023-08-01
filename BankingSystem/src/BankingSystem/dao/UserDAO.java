package BankingSystem.dao;

import java.sql.SQLException;

import BankingSystem.classes.User;

public interface UserDAO {
	boolean addUser(User u) throws SQLException;
	int verifyUser(String username,String password) throws SQLException;
}
