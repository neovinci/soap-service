package by.epamlab.dlx.ws;

import by.epamlab.dlx.beans.Customer;
import by.epamlab.dlx.beans.CustomerListWrapper;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

/**
 * Created by Sergei Astapenko on 07.07.2016.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CustomerWS {
    @WebMethod
    Customer getCustomer(String id);
    @WebMethod
    CustomerListWrapper getCustomers();
    @WebMethod
    void delCustomer(String id);
    @WebMethod
    void addCustomer(Customer customer);
    @WebMethod
    void updateCustomer(String id, Customer customer);
}
