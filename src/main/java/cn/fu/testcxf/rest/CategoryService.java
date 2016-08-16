package cn.fu.testcxf.rest;

//JAX-RS Imports
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * CategoryService class.
 */
@Path("/categoryservice")
@Produces({"application/xml", "application/json"})
public class CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	// Wired using Spring
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@GET
	@Path("/category/{id}")
	public Category getCategory(@PathParam("id") String id) {
		Category cat = (Category) getCategoryDAO().getCategory(id);
		if (cat == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			builder.entity("<error>Category Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return cat;
		}
	}

	@POST
	@Path("/category")
	@Consumes({"application/xml", "application/json"})
	public Response addCategory(Category category) {
		Category cat = (Category) getCategoryDAO().getCategory(
				category.getCategoryId());
		if (cat != null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			getCategoryDAO().addCategory(category);
			return Response.ok(category).build();
		}
	}

	@DELETE
	@Path("/category/{id}")
	public Response deleteCategory(@PathParam("id") String id) {
		Category cat = (Category) getCategoryDAO().getCategory(id);
		if (cat == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			getCategoryDAO().deleteCategory(id);
			return Response.ok().build();
		}
	}

	@PUT
	@Path("/category")
	public Response updateCategory(Category category) {
		Category cat = (Category) getCategoryDAO().getCategory(
				category.getCategoryId());
		if (cat == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			getCategoryDAO().updateCategory(category);
			return Response.ok(category).build();
		}
	}

	@POST
	@Path("/category/book")
	@Consumes({"application/xml","application/json"})
	public Response addBooks(Category category) {
		Category cat = (Category) getCategoryDAO().getCategory(
				category.getCategoryId());
		if (cat == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			getCategoryDAO().addBook(category);
			return Response.ok(category).build();
		}
	}

	@GET
	@Path("/category/{id}/books")
	@Consumes({"application/xml", "application/json"})
	public Response getBooks(@PathParam("id") String id) {
		Category cat = (Category) getCategoryDAO().getCategory(id);
		if (cat == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			cat.setBooks(getCategoryDAO().getBooks(id));
			return Response.ok(cat).build();
		}
	}
}