package com.hcl.bhoomi.service;

import java.io.IOException;

public interface BookService {
	
	public abstract void login(String username,String password);
	public abstract void adduser(String username,String password);
	public abstract void addBook() throws IOException;
	public abstract void takeABookByName(String bookName,String name);
	public abstract void takeABookById(String bookId,String name);
	public abstract void myBooks(String name);
	public abstract void viewALlBooks();
	public abstract void deleteBook();
	public abstract void returnBook(String bookId);
	public abstract void editBookDetails() throws IOException;
	public abstract void searchBookById(String bookId);
	public abstract void searchBookByName(String bookName);

}
