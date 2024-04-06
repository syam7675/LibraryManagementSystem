package com.hcl.bhoomi.model;

public class Book {
	
	private String bookId;
	private String bookName;
	private String author;
	private String genre;
	private int price;
	private String status;
	public Book()
	{
		
	}
	
	public Book(String bookId, String bookName, String author, String genre, int price,String status) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.genre = genre;
		this.price = price;
		this.status=status;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", genre=" + genre
				+ ", price=" + price + ", status=" + status + "]";
	}

	
	

}
