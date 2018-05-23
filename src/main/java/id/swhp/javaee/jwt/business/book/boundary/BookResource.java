package id.swhp.javaee.jwt.business.book.boundary;

import id.swhp.javaee.jwt.business.book.entity.Book;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Sukma Wardana
 * @author Werner Keil
 * @since 1.0
 */
@Stateless
@Path("books")
public class BookResource {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @GET
    @RolesAllowed("ADMIN")
    public Response findAllBooks(@Context SecurityContext securityContext) {
    	String caller;
    	if (null != securityContext.getUserPrincipal()) {
    		caller = securityContext.getUserPrincipal().getName();
    		logger.info(caller);
    	} else {
    		caller = "";
    	}
    		
        Jsonb jsonb = JsonbBuilder.create();

        String result = jsonb.toJson(getBooksWithPermission(getAllBooks(), caller)	);
        logger.fine( () -> MessageFormat.format("Books: {0}", result));
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("{isbn}")
    public Response findBookByIsbn(@PathParam("isbn") long isbn) {
        Jsonb jsonb = JsonbBuilder.create();

        // TODO: Should retrieve book from database by ISBN number
        Book book = new Book(isbn, "Werewolf", 1.50);

        String result = jsonb.toJson(book);
        logger.fine( () -> MessageFormat.format("Book: {0}", result));
        return Response.ok().entity(result).build();
    }

    protected List<Book> getAllBooks() {
        return Arrays.asList(
                new Book(1l, "Blody Marry", 2.30),
                new Book(2l, "Haunted House", 2.0),
                new Book(3l, "Ghouls", 1.90)
        );
    }
    
    protected List<Book> getBooksWithPermission(final List<Book> bookList, String caller) {
		final long isbn = 1;
		if ("username".equals(caller)) {
			System.out.println("\nSearch objects having isbn "+ isbn);
			List<Book> foundObjs = bookList.stream()
									 .filter(book -> 
									 book.getIsbn() == isbn )
									 .collect(Collectors.toList());
			if(null != foundObjs) {
				foundObjs.forEach(System.out::println);
				return foundObjs;
			} else {
				System.out.println("Could not found objects in list");
				return Collections.emptyList();
			}
		} else {
			return bookList;
		}
    }
}
