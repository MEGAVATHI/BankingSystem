package BankingSystem.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import BankingSystem.util.DbConnection;

public class Deposit extends Logs{
	Scanner s=new Scanner(System.in);
	private static final String SELECT_QUERY="select * from deposits where accNo=?";
	 private static final String DELETE_QUERY = "DELETE FROM deposits WHERE id = ?";

		private static final String UPDATE_QUERY="UPDATE account SET balance=? where accNo=?";
	private static final String INSERT_QUERY="insert into deposits (date,amount,description,accNo) values(?,?,?,?)";

	public Deposit(Date date, int amount, String description, int accNo) {
		super(date, amount, description, accNo);
	}
	public Deposit() {super();}
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
            System.out.println("Deposit log with ID " + id + " has been deleted.");
        } else {
            System.out.println("Failed to delete deposit log with ID " + id + ".");
        }

	}

	@Override
	public void addLog(int bal, int ori) throws SQLException {
		Connection con = DbConnection.getConnection();
		PreparedStatement st = con.prepareStatement(INSERT_QUERY);
		int amount = bal + ori;
		PreparedStatement ust = con.prepareStatement(UPDATE_QUERY);
		st.setDate(1, getDate());
        st.setInt(2, getAmount());
        st.setString(3, getDescription());
        st.setInt(4, getAccNo());
        ust.setInt(1, amount);
        ust.setInt(2, getAccNo());
        int x=ust.executeUpdate();
        int rowsInserted = st.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Deposit log added successfully. "+x);
        } else {
            System.out.println("Failed to add deposit log.");
        }

		
	}
	
	
}
