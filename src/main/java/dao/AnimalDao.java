package dao;

import models.Animal;

import java.util.List;

public interface AnimalDao {
    List<Animal> getAll();

    // CREATE
    void add(Animal animal);

    // READ
    Animal findById(int id);
    List<Animal> findByType(String type);

    //UPDATE
    void update(int id, String name, String gender, String type, String breed);
    void updateOwnerId(int id, int ownerId);

    // DELETE
    void deleteById(int id);
    void clearAllAnimals();
}