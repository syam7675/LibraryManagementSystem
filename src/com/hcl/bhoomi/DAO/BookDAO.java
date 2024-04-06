package com.hcl.bhoomi.DAO;

import java.util.List;

import com.hcl.bhoomi.model.Book;
import com.hcl.bhoomi.model.UserBooks;

public interface BookDAO {

	public abstract void login(String username,String password);
	public abstract void insertUser(String username,String password);
	public abstract int insertBook(Book book);
	public abstract List<Book> getAllBooks();
	public abstract int deleteBook(String bookId);
	public abstract int updateBookDetails(Book book);
	public abstract boolean checkBookById(String bookId);
	public abstract boolean checkBookById2(String bookId);
	public abstract List<UserBooks> getMyBooks(String name);
	public abstract boolean checkBookByName(String bookName);
	public abstract boolean insertTakenBookByName(String bookName,String name);
	public abstract boolean insertTakenBookById(String bookId,String name);
	public abstract List<Book> getBookById(String bookId);
	public abstract List<Book> getBookByName(String bookName);
	public abstract boolean returnBook(String bookId);
}
