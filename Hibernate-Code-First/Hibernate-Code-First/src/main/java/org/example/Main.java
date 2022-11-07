package org.example;

import Utils.Em;
import entities.Car;
import entities.Truck;
import entities.Vehicle;
import relations.manyToMany.FavouriteFood;
import relations.manyToMany.Person;
import relations.oneToMany.Author;
import relations.oneToMany.Post;
import relations.oneToOne.MotorBike;
import relations.oneToOne.PlateNumber;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Em.entityManager();

        //test - oneToOne
//        entityManager.getTransaction().begin();
//        PlateNumber pl = new PlateNumber();
//        pl.setNumber("CO1223");
//        entityManager.persist(pl);
//        MotorBike mb = new MotorBike();
//        mb.setModel("Yamaha");
//        mb.setPlateNumber(pl);
//        entityManager.persist(mb);
//        entityManager.getTransaction().commit();
        //test - oneToMany
//        entityManager.getTransaction().begin();
//        Author author = new Author("Peter");
//        Post post1 = new Post("First one");
//        Post post2 = new Post("Second one");
//        author.addPost(post1);
//        author.addPost(post2);
//        post1.setAuthor(author);
//        post2.setAuthor(author);
//        entityManager.persist(author);
//        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Person person1 = new Person("Peter");
        Person person2 = new Person("John");
        FavouriteFood fv1 = new FavouriteFood("Banana");
        FavouriteFood fv2 = new FavouriteFood("Strawberry");
        person1.addFavouriteFood(fv1);
        person1.addFavouriteFood(fv2);
        entityManager.persist(person1);
        entityManager.getTransaction().commit();

    }
}