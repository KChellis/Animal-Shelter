import dao.Sql2oAnimalDao;
import dao.Sql2oCustomerDao;
import models.Animal;
import models.Customer;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString= "jdbc:h2:~/animal-shelter.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oAnimalDao animalDao = new Sql2oAnimalDao(sql2o);
        Sql2oCustomerDao customerDao = new Sql2oCustomerDao(sql2o);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = animalDao.getAll();
            model.put("animals", animals);
            return new ModelAndView(model, "animalslist.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/type", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            List<Animal> animals;
            if(type.equals("all")){
                animals = animalDao.getAll();
            }else{
                animals = animalDao.findByType(type);
            }
            model.put("animals", animals);
            return new ModelAndView(model, "animalslist.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String type = request.queryParams("type");
            String breed = request.queryParams("breed");
            Animal newAnimal = new Animal(name, gender, type, breed);
            animalDao.add(newAnimal);
            response.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/animals/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            animalDao.clearAllAnimals();
            response.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/animals/:animalId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(request.params("animalId"));
            Animal currentAnimal = animalDao.findById(animalId);
            model.put("animal", currentAnimal);
            return new ModelAndView(model, "animal-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/:animalId/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(request.params("animalId"));
            Animal currentAnimal = animalDao.findById(animalId);
            model.put("animal", currentAnimal);
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/:animalId/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String type = request.queryParams("type");
            String breed = request.queryParams("breed");
            int animalId = Integer.parseInt(request.params("animalId"));
            animalDao.update(animalId, name, gender, type, breed);
            response.redirect("/animals/" + animalId);
            return null;
        }, new HandlebarsTemplateEngine());

        get("/animals/:animalId/adopt", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Customer> customers = customerDao.getAll();
            model.put("customers", customers);
            int animalId = Integer.parseInt(request.params("animalId"));
            Animal currentAnimal = animalDao.findById(animalId);
            model.put("animal", currentAnimal);
            return new ModelAndView(model, "adopt.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/:animalId/adopt", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int ownerId = Integer.parseInt(request.queryParams("owner"));
            System.out.println(ownerId);
            int animalId = Integer.parseInt(request.params("animalId"));
            animalDao.updateOwnerId(animalId, ownerId);
            response.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/animals/:animalId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(request.params("animalId"));
            animalDao.deleteById(animalId);
            response.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());



        get("/customers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Customer> customers = customerDao.getAll();
            model.put("customers", customers);
            return new ModelAndView(model, "customerslist.hbs");
        }, new HandlebarsTemplateEngine());

        get("/customers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "customer-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/customers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String type = request.queryParams("type");
            String breed = request.queryParams("breed");
            Customer newCustomer = new Customer(name, phone, type, breed);
            customerDao.add(newCustomer);
            response.redirect("/customers");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/customers/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            customerDao.clearAllCustomers();
            response.redirect("/customers");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/customers/:customerId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int customerId = Integer.parseInt(request.params("customerId"));
            Customer currentCustomer = customerDao.findById(customerId);
            model.put("customer", currentCustomer);
            return new ModelAndView(model, "customer-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/customers/:customerId/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int customerId = Integer.parseInt(request.params("customerId"));
            Customer currentCustomer = customerDao.findById(customerId);
            model.put("customer", currentCustomer);
            return new ModelAndView(model, "customer-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/customers/:customerId/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String type = request.queryParams("type");
            String breed = request.queryParams("breed");
            int customerId = Integer.parseInt(request.params("customerId"));
            customerDao.update(customerId, name, phone, type, breed);
            response.redirect("/customers/" + customerId);
            return null;
        }, new HandlebarsTemplateEngine());

        get("/customers/:customerId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int customerId = Integer.parseInt(request.params("customerId"));
            customerDao.deleteById(customerId);
            response.redirect("/customers");
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
