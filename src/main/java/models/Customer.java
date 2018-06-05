package models;

public class Customer {
    private String name;
    private String phone;
    private String type;
    private String breed;
    private int id;

    public Customer(String name, String phone, String type, String breed){
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
