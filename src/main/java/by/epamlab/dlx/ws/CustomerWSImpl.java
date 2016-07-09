package by.epamlab.dlx.ws;

import by.epamlab.dlx.beans.Customer;
import by.epamlab.dlx.beans.CustomerListWrapper;
import by.epamlab.dlx.utilities.CustomerUtil;

import javax.jws.WebService;
import java.util.ArrayList;

/**
 * Created by Sergei Astapenko on 07.07.2016.
 */
@WebService(endpointInterface = "by.epamlab.dlx.ws.CustomerWS",
        portName="customerWSPort",
        serviceName="customerWS")
public class CustomerWSImpl implements CustomerWS {

    public Customer getCustomer(String id) {
        return CustomerUtil.getCustomer(id);
    }

    public CustomerListWrapper getCustomers() {
        return CustomerUtil.getCustomers();
    }

    public void delCustomer(String id) {
        CustomerUtil.delCustomer(id);
    }

    public void addCustomer(Customer customer) {
        CustomerUtil.addCustomer(customer);
    }

    public void updateCustomer(String id, Customer customer) {
        CustomerUtil.updateCustomer(id, customer);
    }
}
