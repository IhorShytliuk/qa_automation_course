package collections;

import java.util.HashSet;
import java.util.Set;

public class CatsAndDogs {
    public static void main(String[] args) {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();

        Set<Pets> pets = join(cats, dogs);
        printPets(pets);

        removeCats(pets, cats);
        printPets(pets);
    }

    public static Set<Cat> createCats() {
        Set<Cat> cats = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            cats.add(new Cat("Cat" + i));
        }
        return cats;
    }

    public static Set<Dog> createDogs() {
        Set<Dog> dogs = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            dogs.add(new Dog("Dog" + i));
        }

        return dogs;
    }

    public static Set<Pets> join(Set<Cat> cats, Set<Dog> dogs) {
        Set<Pets> animals = new HashSet<>();
        animals.addAll(cats);
        animals.addAll(dogs);

        return animals;
    }

    public static void removeCats(Set<Pets> pets, Set<Cat> cats) {
        for (Cat cat : cats) {
            pets.remove(cat);
        }
    }

    public static void printPets(Set<Pets> pets) {
        for (Pets pet : pets) {
            System.out.println(pet.name);
        }
        System.out.println();
    }

    public static class Pets {
        public String name;

        public Pets(String sname) {
            name = sname;
        }
    }

    public static class Cat extends Pets {
        public Cat(String name) {
            super(name);
        }
    }

    public static class Dog extends Pets {
        public Dog(String name) {
            super(name);
        }
    }
}