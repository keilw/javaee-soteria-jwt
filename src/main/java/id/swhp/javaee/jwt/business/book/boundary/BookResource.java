package id.swhp.javaee.jwt.business.book.boundary;

import id.swhp.javaee.jwt.business.book.entity.Book;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Stateless
@Path("books")
public class BookResource {

    @GET
    public Response findAllBook() {
        Jsonb jsonb = JsonbBuilder.create();

        String result = jsonb.toJson(getAllBook());

        return Response.ok().entity(result).build();
    }

    @GET
    @Path("{isbn}")
    public Response findBookByIsbn(@PathParam("isbn") long isbn) {
        Jsonb jsonb = JsonbBuilder.create();

        // TODO: Should retrieve book from database by ISBN number
        Book book = new Book(isbn, "Werewolf", 1.50);

        String result = jsonb.toJson(book);

        return Response.ok().entity(result).build();
    }

    protected List<Book> getAllBook() {
        return Arrays.asList(
                new Book(1l, "Blody Marry", 2.30),
                new Book(2l, "Haunted House", 2.0),
                new Book(3l, "Ghouls", 1.90)
        );
    }
}
