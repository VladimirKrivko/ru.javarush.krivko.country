package ru.javarush.country;

import org.hibernate.SessionFactory;
import ru.javarush.country.entity.City;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionProvider sessionProvider = new SessionProvider();
        SessionFactory sessionFactory = sessionProvider.getSessionFactory();

        TestingService testingService = new TestingService(sessionFactory);

        long startTime = new Date().getTime();
        List<City> cities = testingService.fetchData();
        long stopTime = new Date().getTime();

        System.out.println(stopTime - startTime);

        //      EAGER             LAZY
        //      960               438
        //      959               427

    }
}