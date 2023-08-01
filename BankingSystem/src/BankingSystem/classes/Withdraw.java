package BankingSystem.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import BankingSystem.util.DbConnection;

public class Withdraw extends Logs{
	Scanner s=new Scanner(System.in);
	private static final String SELECT_QUERY="select * from withdraw where accNo=?";
	private static final String UPDATE_QUERY="UPDATE account SET balance=? where accNo=?";
	 private static final String DELETE_QUERY = "DELETE FROM withdraw WHERE wid = ?";
	private static final String INSERT_QUERY="insert into withdraw (date,amount,description,accNo) values(?,?,?,?)";

	public Withdraw(Date date, int amount, String description, int accNo) {
		super(date, amount, description, accNo);
	}

	public Withdraw() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void viewLog(int id) throws SQLException {
		Connection con = DbConnection.getConnection();
		PreparedStatement st = con.prepareStatement(SELECT_QUERY);
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		while(rs.next())
		{
            Date date = rs.getDate("date");
            int amount = rs.getInt("amount");
            String description = rs.getString("description");

            // Display the data in a table format
            System.out.printf("| %-12s | %-8s | %-20s |\n", date, amount, description);
		}
	}

	@Override
	public void deleteLog(int id) throws SQLException {
		Connection con = DbConnection.getConnection();
        PreparedStatement st = con.prepareStatement(DELETE_QUERY);
        st.setInt(1, id);

        int rowsDeleted = st.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Withdraw log with ID " + id + " has been deleted.");
        } else {
            System.out.println("Failed to delete withdraw log with ID " + id + ".");
        }

	}

	@Override
	public void addLog(int cur, int amt) throws SQLException {
		Connection con = DbConnection.getConnection();
		PreparedStatement st = con.prepareStatement(INSERT_QUERY);
		PreparedStatement ust = con.prepareStatement(UPDATE_QUERY);
		st.setDate(1, getDate());
        st.setInt(2, getAmount());
        st.setString(3, getDescription());
        st.setInt(4, getAccNo());
        int newBalance = cur - amt;
       // System.out.println(newBalance + "====" + cur + "=====" + amt);
        
        if(newBalance < 0) {
        	ust.setInt(1, 0);
        	ust.setInt(2, getAccNo());
        }
        else {
	        ust.setInt(1, newBalance);
	        ust.setInt(2, getAccNo());
        }
        int x = ust.executeUpdate();
        System.out.println("sddsd---->" + x);

        int rowsInserted = st.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Withdraw log added successfully.");
        } else {
            System.out.println("Failed to add withdraw log.");
        }
    }
   

}
