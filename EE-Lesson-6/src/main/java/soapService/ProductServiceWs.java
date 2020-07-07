package soapService;


import service.ProductRepr;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
public interface ProductServiceWs {

    @WebMethod
    List<ProductRepr> findAllWs();

    @WebMethod
    ProductRepr findByIdWs(long id);

    @WebMethod
    void delete( long id);

    @WebMethod
    void insert(ProductRepr productRe);

    @WebMethod
    void update(ProductRepr productRe);

}
