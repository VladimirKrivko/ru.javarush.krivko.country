package ru.javarush.country;

import org.hibernate.SessionFactory;
import ru.javarush.country.entity.City;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionProvider sessionProvider = new SessionProvider();
        SessionFactory sessionFactory = sessionProvider.getSessionFactory();

        TestingService testingService = new TestingService(sessionFactory);
        List<City> cities = testingService.fetchData();

        System.out.println(cities.size());
    }
}
