package BankingSystem.classes;

import java.sql.Date;
import java.sql.SQLException;

public abstract class Logs {
	private int id;
    private Date date;
    private int amount;
    private String description;
    private int accNo;
    public Logs(Date date, int amount, String description, int accNo) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.accNo = accNo;
    }
    public Logs() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public abstract void viewLog(int id) throws SQLException;
    public abstract void deleteLog(int id) throws SQLException;
    public abstract void addLog(int i, int j) throws SQLException;
}
