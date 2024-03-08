package com.sfr.bankingSystem;

import java.util.Scanner;
import java.io.Console;
import java.sql.Connection;

class BankingSystem {
	static Scanner sc = new Scanner(System.in);
	static Console console = System.console();
	private static Connection con = Database.getInstance().makeConnection();
	public static void main(String args[])
	{
		try
		{  
			System.out.println("Connection :"+con);
			System.out.println("statemnt :"+Database.st);
	//:		Database.connect();
			TableCreation.tables();
			System.out.println("\t\t Welcome to online Banking");
			Validation valid = new Validation();
			AddInformation add = new AddInformation();
			InsertIntoDatabase insert = new InsertIntoDatabase();
			insert.insertRole();
			insert.interest();
			if(!valid.isAdmin())
			{
				System.out.println("For add Admin:");
				add.addAdmin();
			}
			boolean flag = true;
			while(flag)
			{
				System.out.println("press 1 - Login \npress 2 - Exit");
				int opt = sc.nextInt();
				switch(opt)
				{
					case 1 : System.out.println("Enter your login");
                            			 String login = console.readLine();
		                                 System.out.println("Enter your password");
                    			         String password = console.readLine();
						 if(!valid.isValidLoginPass(login,password))
						 {
							 System.out.println("\t\t NO SUCH USER");
							 return;
						 }
						 int count = 0;
					         Login personLogin = new Login();
					         personLogin.login(login,password);
	/*                       		while(!valid.isCrtPass(login,password))
		                     		{
							 count++;
							 if(count == 3)
							 {
                                  				System.out.println("\t\t Passsword Incorrect ! \t Maximum attempt reached");
						 	      	System.out.println("1 for forgot password \t 2 - exit");
								opt = sc.nextInt();
						  	     	 if(opt == 1)
						  	      	 {
								    valid.resetPassword(login);
								    break;
								  }
							 }
							 System.out.println("Password incorect ! enter again");
							 password = console.readLine();
						 }*/
			                         break;
					case 2 : System.out.println("\t\t Thank you");
							 flag = false;
							 break;
					default : System.out.println("Enter any valid option ( 1 or 2 )");
			}
		}
	 }
	 catch(Exception e)
	 {
	 		e.printStackTrace();
	 }
	 finally
	 {
	 	try
	 	{
	 	     Database.getInstance().close();
	 	}
	 	catch(Exception e)
	 	{
	 		e.printStackTrace();
	 	}
	 }
  }
}
