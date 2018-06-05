package models;

import java.util.Objects;

public class Animal {
    private String name;
    private String gender;
    private String type;
    private String breed;
    private int id;
    private int ownerId;

    public Animal(String name, String gender, String type, String breed) {
        this.name = name;
        this.gender = gender;
        this.type = type;
        this.breed = breed;

    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return getId() == animal.getId() &&
                getOwnerId() == animal.getOwnerId() &&
                Objects.equals(getName(), animal.getName()) &&
                Objects.equals(getGender(), animal.getGender()) &&
                Objects.equals(getType(), animal.getType()) &&
                Objects.equals(getBreed(), animal.getBreed());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getGender(), getType(), getBreed(), getId(), getOwnerId());
    }
}

