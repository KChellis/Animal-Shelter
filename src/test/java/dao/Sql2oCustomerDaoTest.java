package dao;

import models.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oCustomerDaoTest {
    private Sql2oCustomerDao customerDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        customerDao = new Sql2oCustomerDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    private Customer setupNewCustomer() {
        return new Customer("Kristen Chellis", "503-555-5555", "dog", "german shepherd");
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Customer customer = setupNewCustomer();
        int originalCustomerId = customer.getId();
        customerDao.add(customer);
        assertNotEquals(originalCustomerId, customer.getId()); //how does this work?
    }

    @Test
    public void addedCustomersAreReturnedFromgetAll() throws Exception {
        Customer customer = setupNewCustomer();
        customerDao.add(customer);
        assertEquals(1, customerDao.getAll().size());
    }

    @Test
    public void noCustomersReturnsEmptyList() throws Exception {
        assertEquals(0, customerDao.getAll().size());
    }

    @Test
    public void updateChangesCustomerContent() throws Exception {

        Customer customer = setupNewCustomer();
        customerDao.add(customer);
        String initialName = customer.getName();

        customerDao.update(customer.getId(), "Sarah Chellis", "503-333-3333", "cat", "siamese");
        Customer updatedCustomer = customerDao.findById(customer.getId());
        assertNotEquals(initialName, updatedCustomer.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectCustomer() throws Exception {
        Customer customer = setupNewCustomer();
        customerDao.add(customer);
        customerDao.deleteById(customer.getId());
        assertEquals(0, customerDao.getAll().size());
    }


    @Test
    public void clearAllClearsAll() throws Exception {
        Customer customer = setupNewCustomer();
        Customer otherCustomer = new Customer("Sarah Chellis", "503-333-3333", "cat", "siamese");
        customerDao.add(customer);
        customerDao.add(otherCustomer);
        int daoSize = customerDao.getAll().size();
        customerDao.clearAllCustomers();
        assertTrue(daoSize > 0 && daoSize > customerDao.getAll().size()); //this is a little overcomplicated, but illustrates well how we might use `assertTrue` in a different way.
    }

}
