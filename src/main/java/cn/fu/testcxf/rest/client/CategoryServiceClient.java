package cn.fu.testcxf.rest.client;

import java.util.Iterator;

import org.apache.cxf.jaxrs.client.WebClient;

import cn.fu.testcxf.rest.Book;
import cn.fu.testcxf.rest.Category;

public class CategoryServiceClient {
	public static void main(String[] args) {
		try {
			String domainAddress = "http://localhost:8081/spring_restFul_cxf/";
			String contentType= "application/json";
			// Service instance
			WebClient client = WebClient.create(domainAddress);
			Category category = client.path("categoryservice/category/001")
					.accept(contentType).get(Category.class);
			System.out.println("Category details from REST service.");
			System.out.println("Category Name " + category.getCategoryName());
			System.out.println("Category Id " + category.getCategoryId());
			System.out.println("Book details for Category "
					+ category.getCategoryId() + " from REST service");
			String bookString = "categoryservice/category/"
					+ category.getCategoryId() + "/books";
			WebClient clientBook = WebClient.create(domainAddress);
			Category categoryBooks = clientBook.path(bookString)
					.accept(contentType).get(Category.class);
			Iterator<Book> iterator = categoryBooks.getBooks().iterator();
			while (iterator.hasNext()) {
				Book book = iterator.next();
				System.out.println("Book Name " + book.getBookName());
				System.out.println("Book ISBN " + book.getBookISBNnumber());
				System.out.println("Book ID " + book.getBookId());
				System.out.println("Book Author " + book.getAuthor());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
