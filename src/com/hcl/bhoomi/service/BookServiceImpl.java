package com.hcl.bhoomi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.hcl.bhoomi.DAO.BookDAO;
import com.hcl.bhoomi.model.Book;
import com.hcl.bhoomi.model.UserBooks;

public class BookServiceImpl implements BookService {
	private final BookDAO bookDAO;
	static String bookId,bookName,author,genre,status;
	static int price;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

	static Scanner sc=new Scanner(System.in);
	Book book;
	UserBooks userBook;
	public BookServiceImpl()
	{
		this.bookDAO=null;
	}
	public BookServiceImpl(BookDAO bookDAO)
	{
		this.bookDAO=bookDAO;
	}
	@Override
	public void login(String username, String password) {
			bookDAO.login(username, password);	
	}
	@Override
	public void adduser(String username, String password) {
		
		bookDAO.insertUser(username, password);
		
		
	}
	@Override
	public void addBook() throws IOException {
		
		System.out.println("Enter book details:\nEnter Book id:");
		bookId=br.readLine();
		System.out.println("Enter Book name:");
		bookName=br.readLine();
		System.out.println("Enter author name:");
		author=br.readLine();
		System.out.println("Enter genre:");
		genre=br.readLine();
		System.out.println("Enter book price:");
		price=sc.nextInt();
		Book book=new Book(bookId,bookName,author,genre,price,"available");
		int count=bookDAO.insertBook(book);
		if(count>0)
		{
			System.out.println("Book is successfully inserted into database");
		}
		else
		{
			System.out.println("Book is failed to insert into database");
		}
	}
	@Override
	public void viewALlBooks() {
		List<Book> list=null;
		try
		{
			System.out.println("Books List is:");
			list=bookDAO.getAllBooks();
			Iterator<Book> iterator = list.iterator();
			while(iterator.hasNext())
			{
				book = iterator.next();
				System.out.println(book);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
	
	}
	@Override
	public void deleteBook() {
		
		System.out.println("Enter book id u want to delete:");
		bookId=sc.next();
		int count=bookDAO.deleteBook(bookId);
		if(count>0)
		{
			System.out.println("Book Successfully deleted");
			
		}
		else
		{
			System.out.println("Book is failed to delete");
		}
		
	}
	@Override
	public void editBookDetails() throws IOException {
		
		System.out.println("Enter book id u want update:");
		bookId=br.readLine();
		boolean flag=bookDAO.checkBookById(bookId);
		if(flag==true)
		{
		System.out.print("Enter updated details:\nName:");
		bookName=br.readLine();
		System.out.print("Author:");
		author=br.readLine();
		System.out.print("Genre:");
		genre=br.readLine();
		System.out.print("price:");
		price=sc.nextInt();
		System.out.print("status:");
		status=br.readLine();
		book=new Book(bookId,bookName,author,genre,price,status);
		int count=bookDAO.updateBookDetails(book);
		if(count>0)
		{
			System.out.println("Successfully book details updated");
		}
		else
		{
			System.out.println("Failed to update book details");
		}
		}
		
		
	}
	@Override
	public void takeABookByName(String bookName,String name) {
		
		bookDAO.insertTakenBookByName(bookName,name);
		
	}
	@Override
	public void takeABookById(String bookId,String name) {
		
		bookDAO.insertTakenBookById(bookId,name);
		
	}
	@Override
	public void myBooks(String name) {
		List<UserBooks> list;
		try
		{
			list=new ArrayList<UserBooks>();
			
			list=bookDAO.getMyBooks(name);
			if(list.isEmpty())
			{
				System.out.println("You don't have any Books");
			}
			else
			{
				System.out.println("My Books List is:");
			Iterator<UserBooks> iterator = list.iterator();
			while(iterator.hasNext())
			{
				
				userBook = iterator.next();
				System.out.println(userBook);
			}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		
	}
	@Override
	public void searchBookById(String bookId) {
		
		List<Book> list=null;
		try
		{
			
			list=bookDAO.getBookById(bookId);
			if(list.isEmpty())
			{
				System.out.println("You searched book is not in the list");
			}
			else
			{
				System.out.println("Books List is:");
			Iterator<Book> iterator = list.iterator();
			while(iterator.hasNext())
			{
				book = iterator.next();
				System.out.println(book);
			}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void searchBookByName(String bookName) {
		List<Book> list=null;
		try
		{
			
			list=bookDAO.getBookByName(bookName);
			if(list.isEmpty())
			{
				System.out.println("You searched book is not in the list");
			}
			else
			{
			System.out.println("Books List is:");
			Iterator<Book> iterator = list.iterator();
			while(iterator.hasNext())
			{
				book = iterator.next();
				System.out.println(book);
			}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void returnBook(String bookId) {
		
		boolean flag=bookDAO.returnBook(bookId);
		if(flag==false)
		{
			System.out.println("You can't return this book bcz you don't have this book");
		}
		
	}
	
	
	
	
	

}
