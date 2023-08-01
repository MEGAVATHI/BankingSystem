package BankingSystem.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankingSystem.classes.Account;
import BankingSystem.dao.AccountDAO;
import BankingSystem.util.DbConnection;

public class AccountDaoImpl implements AccountDAO {
	private static final String INSERT_QUERY = "INSERT INTO account (accNo, firstName,lastName,age, phoneNo, gender) VALUES (?, ?, ?, ?,?,?)";
	 private static final String SELECT_QUERY= "SELECT * FROM account WHERE accNo = ?";
	@Override
	public Account getAccountById(int id) throws SQLException {
		
		Connection con = DbConnection.getConnection();
		PreparedStatement st = con.prepareStatement(SELECT_QUERY);
		st.setInt(1,id);
		ResultSet rs=st.executeQuery();
		if(rs.next())
		{
			 Account account = new Account();
	         account.setAccNo(rs.getInt("accNo"));
	         account.setAge(rs.getInt("age"));
	         account.setFirstName(rs.getString("firstName"));
	         account.setLastName(rs.getString("lastName"));
	         account.setPhoneNo(rs.getString("phoneNo"));
	         account.setGender(rs.getString("gender"));
	         account.setBalance(rs.getInt("balance"));
			return account;
		}
		return null;
	}

	@Override
	public boolean addAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DbConnection.getConnection();
		PreparedStatement st = con.prepareStatement(INSERT_QUERY);

        st.setInt(1, account.getAccNo());
        st.setString(2, account.getFirstName());
        st.setString(3, account.getLastName());
        st.setInt(4, account.getAge());
        st.setString(5,account.getPhoneNo());
        st.setString(6, account.getGender());
        // set other properties if needed
        return st.executeUpdate()>0;
	}

}
