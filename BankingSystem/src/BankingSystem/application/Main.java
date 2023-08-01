package BankingSystem.application;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import BankingSystem.classes.Account;
import BankingSystem.classes.Deposit;
import BankingSystem.classes.Logs;
import BankingSystem.classes.User;
import BankingSystem.classes.Withdraw;
import BankingSystem.dao.AccountDAO;
import BankingSystem.dao.UserDAO;
import BankingSystem.daoimpl.AccountDaoImpl;
import BankingSystem.daoimpl.UserDaoImpl;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		UserDAO userDao = new UserDaoImpl();
		AccountDAO accountDao = new AccountDaoImpl();
		
		while(true)
		{
			System.out.println("1.New User");
			System.out.println("2.Login");
			System.out.print("Enter your Choice: ");
			int choice=s.nextInt();
			s.nextLine();
			switch(choice)
			{
			case 1:
				System.out.println("\n<<-----------------Register User------------------>>");
				User newUser = new User();
		        System.out.print("Enter username: ");
		        newUser.setUsername(s.nextLine());
		        System.out.print("Enter password: ");
		        newUser.setPassword(s.nextLine());
		        userDao.addUser(newUser);
	
		        
		        Account newAccount = new Account();
		        newAccount.setAccNo(newUser.getAccNo()); // Set the user's ID as the foreign key value
		        System.out.print("Enter age: ");
		        newAccount.setAge(s.nextInt());
		        s.nextLine(); // Consume the newline character left by nextInt()
		        System.out.print("Enter first name: ");
		        newAccount.setFirstName(s.nextLine());
		        System.out.print("Enter last name: ");
		        newAccount.setLastName(s.nextLine());
		        System.out.print("Enter phone no: ");
		        newAccount.setPhoneNo(s.nextLine());
		        System.out.print("Enter gender: ");
		        newAccount.setGender(s.nextLine());
		        System.out.print("Enter balance: ");
		        newAccount.setBalance(s.nextInt());
		        accountDao.addAccount(newAccount);
		        
		         System.out.println("\n\nThanks for Creating Account...!Wish You a good luck..!");
		        break;
			case 2:
				System.out.println("\n<<-----------------Login User------------------>>\n");
				System.out.print("Enter username: ");
				String uname=s.nextLine();
				System.out.print("Enter password: ");
				String pwd=s.nextLine();
				int accNo=userDao.verifyUser(uname,pwd);
				if(accNo!=0)
				{
					Account account=accountDao.getAccountById(accNo);
					System.out.print("\nWelcome Back "+account.getFirstName()+" "+account.getLastName()+"!" + account.getBalance());
					
					while(true)
					{
						 System.out.println("\n\n-------------------------------------");
					        System.out.println("|           YOUR CHOICE        |");
					        System.out.println("-------------------------------------");
					        System.out.println("| 1. View Account                    |");
					        System.out.println("| 2. Update Account                  |");
					        System.out.println("| 3. Deposit                         |");
					        System.out.println("| 4. Withdraw                        |");
					        System.out.println("| 5. Check Balance                   |");
					        System.out.println("| 6. View Deposit History            |");
					        System.out.println("| 7. View Withdrawal History         |");
					        System.out.println("| 8. Delete Deposit Log              |");
					        System.out.println("| 9. Delete Withdraw Log             |");
					        System.out.println("| 10. Logout                          |");
					        System.out.println("---------------------------------------");
						System.out.print("Enter Your Choice : ");
						int choice2=s.nextInt();
						if(choice2==10)
						{
							System.out.println("Logout successful");
							break;
						}
						s.nextLine();
						switch(choice2)
						{
							case 1:
								
								if(account!=null)
								{   
									System.out.println("\n                    <<----PROFILE---->>");
									System.out.println("---------------------------------------------------------------");
					            System.out.printf("%-15s | %-15s | %-5s | %-6s | %-8s |\n", "First Name", "Last Name", "Age", "Gender", "Phone number");
					            System.out.println("---------------------------------------------------------------");
		
					            System.out.printf("%-15s | %-15s | %-5s | %-6s | %-15s |\n",
					                    account.getFirstName(),
					                    account.getLastName(),
					                    account.getAge(),
					                    account.getGender(),
					                    account.getPhoneNo());
		
					            System.out.println("---------------------------------------------------------------");
					       }
								break;
							case 3:
								System.out.print("Enter Date(YYYY-MM-DD):");
								String strDate=s.nextLine();
								Date date=Date.valueOf(strDate);
								System.out.print("Enter Amount :");
								int amt=s.nextInt();
								s.nextLine();
								System.out.print("Enter Description :");
								String des=s.nextLine();
								Logs dep=new Deposit(date,amt,des,accNo);
								dep.addLog(account.getBalance(), amt);
								break;
							case 4:
								
								 Account currentAccount = accountDao.getAccountById(accNo);
								 int currentBalance = currentAccount.getBalance();
								System.out.print("Enter Date(YYYY-MM-DD):");
								String strDate2=s.nextLine();
								Date date2=Date.valueOf(strDate2);
								System.out.print("Enter Amount :");
								int amt2=s.nextInt();
								s.nextLine();
								System.out.print("Enter Description :");
								String des2=s.nextLine();
								Logs wit=new Withdraw(date2,amt2,des2,accNo);
								wit.addLog(currentBalance, amt2);
								break;
							case 6:
								Logs viewDep=new Deposit();
								viewDep.viewLog(accNo);
								break;
							case 7:
								Logs viewWit=new Withdraw();
								viewWit.viewLog(accNo);
								break;
							case 9:
								System.out.print("Enter Withdrawal ID :");
								int wid=s.nextInt();
								Logs delWit=new Withdraw();
								delWit.deleteLog(wid);
								break;
							case 8:
								System.out.print("Enter Deposit ID :");
								int id=s.nextInt();
								Logs delDep=new Deposit();
								delDep.deleteLog(id);
								break;
							case 5:
								Account currentAccount1 = accountDao.getAccountById(accNo);
								 int currentBalance1 = currentAccount1.getBalance();
								System.out.print(currentBalance1);
								break;
					
						}
						
					}
				}
	
			}
		}
}

}
