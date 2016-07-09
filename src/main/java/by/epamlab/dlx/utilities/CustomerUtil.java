package by.epamlab.dlx.utilities;

import by.epamlab.dlx.beans.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sergei Astapenko on 06.07.2016.
 */
public class CustomerUtil {
    private final static Comparator<Customer> COMP_BY_ID = new Comparator<Customer>() {
        public int compare(Customer one, Customer two) {
            return one.getCustomerDocID().compareTo(two.getCustomerDocID());
        }
    };

    public static CustomerListWrapper getCustomers() {
        return new CustomerListWrapper(readCustomers());
    }

    public static Customer getCustomer(String id) {
        ArrayList<Customer> customers = readCustomers();
        if(getCustomerIndex(id) < 0) {
            return new Customer("NOT FOUND", "NOT FOUND", "NOT FOUND", "NOT FOUND", null, null);
        } else {
            return customers.get(getCustomerIndex(id));
        }
    }

    public static void delCustomer(String id) {
        ArrayList<Customer> customers = readCustomers();
        if(getCustomerIndex(id) >= 0) {
            customers.remove(getCustomerIndex(id));
            try {
                saveCustomers(customers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addCustomer (Customer customer) {
        ArrayList<Customer> customers = readCustomers();
        customers.add(customer);
        try {
            saveCustomers(customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(String customerDocID, Customer customer) {
        ArrayList<Customer> customers = readCustomers();
        if(getCustomerIndex(customerDocID) >= 0) {
            int index = getCustomerIndex(customerDocID);
            customers.remove(index);
            customers.add(index, customer);
            try {
                saveCustomers(customers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<Customer> readCustomers() {
        Reservation reservation = null;
        FileReader in = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Reservation.class);
            Unmarshaller u = context.createUnmarshaller();
            in = new FileReader("d:/reservation.xml");
            reservation = (Reservation) u.unmarshal(in);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reservation.getCustomers();
    }

    private static void saveCustomers(ArrayList<Customer> customers) throws IOException, JAXBException {
        FileOutputStream out = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Reservation.class);
            Marshaller m = context.createMarshaller();
            Reservation reservation = new Reservation();
            reservation.setCustomers(customers);
            out = new FileOutputStream("d:/reservation.xml");
            m.marshal(reservation, out);
        } finally {
            out.close();
        }
    }

    private static Reservation readReservation() throws JAXBException, IOException {
        Reservation reservation = null;
        FileReader in = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Reservation.class);
            Unmarshaller u = context.createUnmarshaller();
            in = new FileReader("d:/reservation.xml");
            reservation = (Reservation) u.unmarshal(in);
        } finally {
            in.close();
        }
        return reservation;
    }

    private static int getCustomerIndex(String id) {
        ArrayList<Customer> customers = readCustomers();
        sortCustomer(customers);
        int index = Collections.binarySearch(customers, new Customer(id, null, null, null, null, null), COMP_BY_ID);

        return index;
    }

    private static String getNextID(ArrayList<Customer> customers) {
        sortCustomer(customers);
        String lastID = customers.get(customers.size() - 1).getCustomerDocID();
        String nextID = "id" + (Integer.parseInt(lastID.substring(2)) + 1);
        return nextID;
    }

    private static void sortCustomer(ArrayList<Customer> customers) {
        Collections.sort(customers, COMP_BY_ID);
    }
}
