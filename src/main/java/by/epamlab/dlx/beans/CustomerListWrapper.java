package by.epamlab.dlx.beans;

import java.util.ArrayList;

/**
 * Created by Sergei Astapenko on 09.07.2016.
 */
public class CustomerListWrapper {
    private ArrayList<Customer> customerArrayList;

    public CustomerListWrapper() {
    }

    public CustomerListWrapper(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }

    public ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }

    public void setCustomerArrayList(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }
}
