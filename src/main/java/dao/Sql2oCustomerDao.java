package dao;

import models.Customer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCustomerDao implements CustomerDao {
    private final Sql2o sql2o;

    public Sql2oCustomerDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public void add(Customer customer) {
        String sql = "INSERT INTO customers (name, phone, type, breed) VALUES (:name, :phone, :type, :breed)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(customer) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            customer.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Customer> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM customers") //raw sql
                    .executeAndFetch(Customer.class); //fetch a list
        }
    }


    @Override
    public Customer findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM customers WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Customer.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String name, String phone, String type, String breed){
        String sql = "UPDATE customers SET (name, phone, type, breed) = (:name, :phone, :type, :breed) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("phone", phone)
                    .addParameter("type", type)
                    .addParameter("breed", breed)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from customers WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllCustomers() {
        String sql = "DELETE from customers";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
