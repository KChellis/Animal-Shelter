package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    private Customer setupNewCustomer() {
        return new Customer("Kristen Chellis", "503-555-5555", "dog", "german shepherd");
    }

    @Test
    public void CustomerInstantiatesWithname_true() throws Exception {
        Customer customer = setupNewCustomer();
        assertEquals("Kristen Chellis", customer.getName());
    }

    @Test
    public void CustomerInstantiatesWithPhone_true() throws Exception {
        Customer customer = setupNewCustomer();
        assertEquals("503-555-5555", customer.getPhone());
    }

    @Test
    public void CustomerInstantiatesWithType_true() throws Exception {
        Customer customer = setupNewCustomer();
        assertEquals("dog", customer.getType());
    }

    @Test
    public void CustomerInstantiatesWithBreed_true() throws Exception {
        Customer customer = setupNewCustomer();
        assertEquals("german shepherd", customer.getBreed());
    }

    @Test
    public void setId_setsId_1() throws Exception {
        Customer customer = setupNewCustomer();
        customer.setId(1);
        assertEquals(1, customer.getId());
    }
}