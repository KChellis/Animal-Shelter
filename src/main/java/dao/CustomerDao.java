package dao;

import models.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAll();

    // CREATE
    void add(Customer customer);

    // READ
    Customer findById(int id);

    //UPDATE
    void update(int id, String name, String phone, String type, String breed);

    // DELETE
    void deleteById(int id);
    void clearAllCustomers();
}
