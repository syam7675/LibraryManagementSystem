package com.hcl.bhoomi.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.hcl.bhoomi.DAO.BookDAO;
import com.hcl.bhoomi.DAO.BookDAOImpl;
import com.hcl.bhoomi.service.BookService;
import com.hcl.bhoomi.service.BookServiceImpl;



public class Main {
	
	static BookDAO bookDAO=new BookDAOImpl();
	static BookService bookService=new BookServiceImpl(bookDAO);
	static Scanner sc=new Scanner(System.in);
	static String username,password,name;
	static String bookId,bookName,author,genre,status;
	static int price;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {	
		System.out.println("*****Welcome to Library Management System*****");
		MainOptions();
	
		}

	public static void MainOptions() throws IOException
	{
		int ch;
		while(true)
		{
			System.out.println("Choose your Role:\n1.Admin\n2.User\n3.Exit");
			System.out.println("Enter your choice:");
			ch=sc.nextInt();
			switch(ch)
			{
			case 1:
				System.out.println("Enter ur username:");
				username=br.readLine();
				System.out.println("Enter ur password:");
				password=br.readLine();
				name=username;
				bookDAO.login(username,password);
				break;
			case 2:
				userLoginOptions();
				break;
			case 3:
				System.out.println("*****Thank You******");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice");
			}
	}
	}	
	public static void adminOptions() throws IOException
	{   int choice; 
		while(true)
		{
			System.out.println("Menu:\n1.View Books\n2.Add an user\n3.Add a Book\n4.Update a Book\n5.Delete a Book\n6.SearchByID\n7.SearchByName\n8.Exit");
			System.out.println("Enter ur choice:");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				bookService.viewALlBooks();
				break;
			case 2:
				System.out.println("Enter username u want to add:");
				username=br.readLine();
				System.out.println("Enter password:");
				password=br.readLine();
				bookService.adduser(username, password);
				System.out.println("User successfully added");
				break;
			case 3:
				bookService.addBook();
				break;
			case 4:
				bookService.editBookDetails();
				break;
			case 5:
				bookService.deleteBook();
				break;
			case 6:
				System.out.println("Enter book id u want to search:");
				bookId=br.readLine();
				bookService.searchBookById(bookId);
				break;
			case 7:
				System.out.println("Enter book name u want to search:");
				bookName=br.readLine();
				bookService.searchBookByName(bookName);

				break;
			case 8:
				MainOptions();
				break;
			default:
				System.out.println("Invalid Choice");
			}
		}
	}
	public static void userOptions() throws IOException
	{
		int op;
		while(true)
		{
			System.out.println("Menu:\n1.View Books\n2.Take a Book\n3.My Books\n4.Return a book\n5.Exit\nEnter your choice:");
			op=sc.nextInt();
			switch(op)
			{
			case 1:
				bookService.viewALlBooks();
				break;
			case 2:
				System.out.println("Enter book id or name(id/name):");
				String col=br.readLine();
				if(col.equalsIgnoreCase("id"))
				{
					System.out.println("Enter book id you want to take:");
					bookId=sc.next();
					bookService.takeABookById(bookId,name);
				}
				else if(col.equalsIgnoreCase("name"))
				{
					System.out.println("Enter book name you want to take:");
					bookName=br.readLine();				
					bookService.takeABookByName(bookName,name);

				}
				else
				{
					System.out.println("You have to enter id or name only!!!");
				}
				
				break;
			case 3:
				name=username;
				System.out.println("Username="+name);
				bookService.myBooks(name);
				break;
			case 4:
				System.out.println("Enter book id u want to return:");
				bookId=br.readLine();
				bookService.returnBook(bookId);
				break;
			case 5:
				MainOptions();
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}
	public static void userLoginOptions() throws IOException
	{
		int c;
		while(true)
		{
			System.out.println("Menu:\n1.Login\n2.Register\n3.Exit\nEnter ur choice:");
			c=sc.nextInt();
		
		switch(c)
		{
		case 1:
			System.out.println("Enter ur username :");
			username=br.readLine();
			System.out.println("Enter ur password:");
			password=br.readLine();
			name=username;
			bookDAO.login(username,password);
			break;
		case 2:
			System.out.println("Enter ur username :");
			username=sc.next();
			System.out.println("Enter ur password:");
			password=sc.next();
			bookService.adduser(username, password);
			System.out.println("You registered successfully please login");
			break;
		case 3:
			MainOptions();
			break;
		default:
			System.out.println("Invalid choice");
		}
		}
	}
	

}
