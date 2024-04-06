package com.hcl.bhoomi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hcl.bhoomi.main.Main;
import com.hcl.bhoomi.model.Book;
import com.hcl.bhoomi.model.UserBooks;
import com.hcl.bhoomi.util.DatabaseConnection;

public class BookDAOImpl implements BookDAO{
	

	private static  PreparedStatement prepareStatement;
	private static ResultSet resultSet ;
	private static  String nameOfBook;
	private static String idOfBook;
	private static Book book=null;
	private static UserBooks userBook=null;
	private static final String SELECT_USER_QUERY="select * from users";
	private static final String SELECT_BOOK_QUERY="select * from books";
	private static final String SELECT_USERBOOKS_QUERY="select * from user_books";
	private static final String SELECT_USERBOOKS_USERNAME_QUERY="select * from user_books where username=?";
	private static final String SELECT_USERBOOKS_BOOKID_QUERY="select * from user_books where book_id=?";
	private static final String SELECT_BOOKID_QUERY="select book_id,book_name,status from books where book_id=?";
	private static final String SELECT_BOOKNAME_QUERY="select book_id,book_name,status from books where book_name=?";
	private static final String SELECT_BOOKID2_QUERY="select * from books where book_id=?";
	private static final String SELECT_BOOKNAME2_QUERY="select * from books where book_name=?";
	private static final String INSERT_USER_QUERY="insert into users values(?,?,?)";
	private static final String INSERT_BOOK_QUERY="insert into books values(?,?,?,?,?,?)";
	private static final String DELETE_BOOK_QUERY="delete from books where book_Id=?";
	private static final String DELETE_USERBOOKS_QUERY="delete from user_books where book_Id=?";
	private static final String UPDATE_BOOK_QUERY="update books set book_name=?,"
			+ "author=?,genre=?,price=?,status=? where book_id=?";
	private static final String UPDATE_BOOK_STATUS_ID_QUERY="update books set status=? where book_id=?";
	private static final String UPDATE_BOOK_STATUS_NAME_QUERY="update books set status=? where book_name=?";
	private static final String INSERT_USERBOOKS_QUERY="insert into user_Books values(?,?,?)";
	
	@Override
	public void login(String username, String password) {
		
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement= conn.prepareStatement(SELECT_USER_QUERY);
			resultSet = prepareStatement.executeQuery();
			boolean adminflag=false;
			boolean userflag=false;
			while(resultSet.next())
			{
				if(resultSet.getString(1).equalsIgnoreCase(username) && resultSet.getString(2).equals(password) && resultSet.getString(3).equalsIgnoreCase("admin")  )
				{
					adminflag=true;
					
					
				}
				else if(resultSet.getString(1).equalsIgnoreCase(username) && resultSet.getString(2).equals(password) && resultSet.getString(3).equalsIgnoreCase("user")  )
				{
					userflag=true;
					
				}
				
			}
			if(userflag==true)
			{
				Main.userOptions();
			}
			else if(adminflag==true)
			{
				Main.adminOptions();
			}
			else
			{
				System.out.println("you are not admin/user so u can't access this");

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertUser(String username, String password) {
		
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement= conn.prepareStatement(SELECT_USER_QUERY);
			resultSet = prepareStatement.executeQuery();
			boolean flag=false;
			while(resultSet.next())
			{
				if(resultSet.getString(1).equalsIgnoreCase(username) && resultSet.getString(2).equals(password) )
				{
					System.out.println("User already exist ");
					break;
				}
				else
				{
					flag=true;
					break;
				}
				
			}
			
			if(flag==true)
			{
					prepareStatement=conn.prepareStatement(INSERT_USER_QUERY);
					prepareStatement.setString(1, username);
					prepareStatement.setString(2, password);
					prepareStatement.setString(3, "user");
					prepareStatement.executeUpdate();
					
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int insertBook(Book book) {
		int count=0;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOK_QUERY);
			resultSet = prepareStatement.executeQuery();
			boolean flag=false;
			while(resultSet.next())
			{
				if(resultSet.getString(1).equals(book.getBookId()) && resultSet.getString(2).equalsIgnoreCase(book.getBookName()))
				{
					flag=true;
					break;
				}
				
			}
		if(flag==false)
		{
			prepareStatement=conn.prepareStatement(INSERT_BOOK_QUERY);
			prepareStatement.setString(1, book.getBookId());
			prepareStatement.setString(2, book.getBookName());
			prepareStatement.setString(3,book.getAuthor());
			prepareStatement.setString(4, book.getGenre());
			prepareStatement.setInt(5,book.getPrice());
			prepareStatement.setString(6,book.getStatus());
			count = prepareStatement.executeUpdate();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Book> getAllBooks() {
		
		List<Book> list=new ArrayList<Book>();
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOK_QUERY);
			 resultSet = prepareStatement.executeQuery();
			
			 while(resultSet.next())
			 {
				 book=new Book();
				 book.setBookId(resultSet.getString(1));
				 book.setBookName(resultSet.getString(2));
				 book.setAuthor(resultSet.getString(3));
				 book.setGenre(resultSet.getString(4));
				 book.setPrice(resultSet.getInt(5));
				 book.setStatus(resultSet.getString(6));
				 list.add(book);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteBook(String bookId) {
		int count=0;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOK_QUERY);
			resultSet = prepareStatement.executeQuery();
			boolean flag=false;
			while(resultSet.next())
			{
				if(resultSet.getString(1).equals(bookId))
				{
					flag=true;
					break;
				}
				else
				{
					flag=false;
					
				}
			}
			if(flag==true)
			{
				prepareStatement=conn.prepareStatement(DELETE_BOOK_QUERY);
				prepareStatement.setString(1, bookId);
				count = prepareStatement.executeUpdate();
			}
			else
			{
				System.out.println("Book is not available so can't delete");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateBookDetails(Book book) {
		int count=0;
		boolean flag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOKID_QUERY);
			prepareStatement.setString(1, book.getBookId());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(1).equals(book.getBookId()))
				{
					flag=true;
					break;
				}
				else
				{
					flag=false;
				}
			}
			if(flag==true) {
				
				prepareStatement=conn.prepareStatement( UPDATE_BOOK_QUERY);
				prepareStatement.setString(1,book.getBookName());
				prepareStatement.setString(2,book.getAuthor());
				prepareStatement.setString(3,book.getGenre());
				prepareStatement.setInt(4,book.getPrice());
				prepareStatement.setString(5,book.getStatus());
				prepareStatement.setString(6,book.getBookId());
				count = prepareStatement.executeUpdate();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public boolean checkBookById(String bookId) {
		boolean flag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOKID_QUERY);
			prepareStatement.setString(1, bookId);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(1).equals(bookId))
				{
					flag=true;
					break;
				}
				else
				{
					flag=false;
				}
			}
			if(flag==false)
			{
				System.out.println("You can't update details of book because book is not avialable");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertTakenBookByName(String bookName,String name) {
		
		boolean flag=checkBookByName(bookName);
		if(flag==true)
		{
			try(Connection conn=DatabaseConnection.getConnectionDb())
			{
				prepareStatement=conn.prepareStatement(INSERT_USERBOOKS_QUERY);
				prepareStatement.setString(1, name);
				prepareStatement.setString(2, idOfBook);
				prepareStatement.setString(3, bookName);
				prepareStatement.executeUpdate();
				prepareStatement=conn.prepareStatement(UPDATE_BOOK_STATUS_NAME_QUERY);
				prepareStatement.setString(1, "issued");
				prepareStatement.setString(2, bookName);
				prepareStatement.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("This book is not available please take another book");
		}
		return false;
	}

	@Override
	public boolean insertTakenBookById(String bookId,String name) {
		
		boolean flag=checkBookById2(bookId);
		if(flag==true)
		{
			try(Connection conn=DatabaseConnection.getConnectionDb())
			{
				prepareStatement=conn.prepareStatement(INSERT_USERBOOKS_QUERY);
				prepareStatement.setString(1, name);
				prepareStatement.setString(2, bookId);
				prepareStatement.setString(3, nameOfBook);
				prepareStatement.executeUpdate();
				prepareStatement=conn.prepareStatement(UPDATE_BOOK_STATUS_ID_QUERY);
				prepareStatement.setString(1, "issued");
				prepareStatement.setString(2, bookId);
				prepareStatement.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("This book is not available please take another book");
		}
		return false;
	}

	@Override
	public boolean checkBookByName(String bookName) {
		
		boolean flag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOKNAME_QUERY);
			prepareStatement.setString(1, bookName);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(2).equalsIgnoreCase(bookName))
				{
					flag=true;
					idOfBook=resultSet.getString(1);
					break;
				}
				else
				{
					flag=false;
				}
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean checkBookById2(String bookId) {
		
		boolean flag=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOKID_QUERY);
			prepareStatement.setString(1, bookId);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString(1).equals(bookId) && resultSet.getString(3).equalsIgnoreCase("available"))
				{
					flag=true;
					nameOfBook=resultSet.getString(2);
					break;
				}
				else
				{
					flag=false;
				}
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<UserBooks> getMyBooks(String name) {
		
		List<UserBooks> list=new ArrayList<UserBooks>();
		
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_USERBOOKS_USERNAME_QUERY);
			prepareStatement.setString(1, name);
			resultSet=prepareStatement.executeQuery();
			
			while(resultSet.next())
			{		
				 if(resultSet.getString(1).equals(name))
				 {
					
				 userBook=new UserBooks();
				 userBook.setUsername(resultSet.getString(1));
				 userBook.setBookId(resultSet.getString(2));
				 userBook.setBookName(resultSet.getString(3));		 
				 list.add(userBook);	
				 }
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public List<Book> getBookById(String bookId) {
		List<Book> list=new ArrayList<>();
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOKID2_QUERY);
			prepareStatement.setString(1, bookId);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				 book=new Book();
				 book.setBookId(resultSet.getString(1));
				 book.setBookName(resultSet.getString(2));
				 book.setAuthor(resultSet.getString(3));
				 book.setGenre(resultSet.getString(4));
				 book.setPrice(resultSet.getInt(5));
				 book.setStatus(resultSet.getString(6));
				 list.add(book);	
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> getBookByName(String bookName) {
		List<Book> list=new ArrayList<>();
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_BOOKNAME2_QUERY);
			prepareStatement.setString(1, bookName);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				book=new Book();
				 book.setBookId(resultSet.getString(1));
				 book.setBookName(resultSet.getString(2));
				 book.setAuthor(resultSet.getString(3));
				 book.setGenre(resultSet.getString(4));
				 book.setPrice(resultSet.getInt(5));
				 book.setStatus(resultSet.getString(6));
				 list.add(book);	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean returnBook(String bookId) {
		
		boolean flag=false,flag2=false;
		try(Connection conn=DatabaseConnection.getConnectionDb())
		{
			prepareStatement=conn.prepareStatement(SELECT_USERBOOKS_BOOKID_QUERY);
			prepareStatement.setString(1, bookId);
			resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				flag=true;	
			}
			if(flag==true)
			{
				prepareStatement=conn.prepareStatement(SELECT_BOOKID2_QUERY);
				prepareStatement.setString(1, bookId);
				resultSet=prepareStatement.executeQuery();
				while(resultSet.next())
				{
					if(resultSet.getString(1).equals(bookId))
					{
						flag2=true;
						break;
					}
				}
			}
			if(flag2==true)
			{
				prepareStatement=conn.prepareStatement(UPDATE_BOOK_STATUS_ID_QUERY);
				prepareStatement.setString(1, "available");
				prepareStatement.setString(2, bookId);
				prepareStatement.executeUpdate();
				prepareStatement=conn.prepareStatement(DELETE_USERBOOKS_QUERY);
				prepareStatement.setString(1, bookId);
				prepareStatement.executeUpdate();
				
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}

	

	

}
