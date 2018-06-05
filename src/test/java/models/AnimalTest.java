package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void NewTaskObjectGetsCorrectlyCreated_true() throws Exception {
        Animal animal = setupNewAnimal();
        assertEquals(true, animal instanceof Animal);
    }



    private Animal setupNewAnimal() {
        return new Animal("liila", "female", "dog", "german shepherd");
    }

    @Test
    public void AnimalInstantiatesWithname_true() throws Exception {
        Animal animal = setupNewAnimal();
        assertEquals("liila", animal.getName());
    }

    @Test
    public void AnimalInstantiatesWithGender_true() throws Exception {
        Animal animal = setupNewAnimal();
        assertEquals("female", animal.getGender());
    }

    @Test
    public void AnimalInstantiatesWithType_true() throws Exception {
        Animal animal = setupNewAnimal();
        assertEquals("dog", animal.getType());
    }

    @Test
    public void AnimalInstantiatesWithBreed_true() throws Exception {
        Animal animal = setupNewAnimal();
        assertEquals("german shepherd", animal.getBreed());
    }

    @Test
    public void setId_setsId_1() throws Exception {
        Animal animal = setupNewAnimal();
        animal.setId(1);
        assertEquals(1, animal.getId());
    }

    @Test
    public void setOwnerId_setsOwnerId_1() throws Exception {
        Animal animal = setupNewAnimal();
        animal.setOwnerId(1);
        assertEquals(1, animal.getOwnerId());
    }

}