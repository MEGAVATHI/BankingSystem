package BankingSystem.classes;

public class User {
	
	private String username;
	private String password;
	private int accNo;
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User() {}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccNo() {
		// TODO Auto-generated method stub
		return this.accNo;
	}
	public void setAccNo(int id)
	{
		this.accNo=id;
	}

}
