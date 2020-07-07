package rest;

import persist.ProductCategory;
import service.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/product")
public interface ProductServiceRs {

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepr productRe);

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepr productRe);

    @DELETE
    @Path("/{id}/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") long id);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findAll();

    @GET
    @Path("/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepr findById(@PathParam("id") long id);
}
