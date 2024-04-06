package com.hcl.bhoomi.model;

public class UserBooks {
	
	private String username,bookId,bookName;
	public UserBooks()
	{
		
	}
	
	public UserBooks(String username, String bookId, String bookName) {
		super();
		this.username = username;
		this.bookId = bookId;
		this.bookName = bookName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Override
	public String toString() {
		return "UserBooks [username=" + username + ", bookId=" + bookId + ", bookName=" + bookName + "]";
	}
	

}
