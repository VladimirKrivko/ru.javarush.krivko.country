package ru.javarush.country;

import org.hibernate.SessionFactory;
import ru.javarush.country.entity.City;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionProvider sessionProvider = new SessionProvider();
        SessionFactory sessionFactory = sessionProvider.getSessionFactory();

        Test test = new Test(sessionFactory);
        List<City> cities = test.fetchData();

        System.out.println(cities.size());
    }
}
