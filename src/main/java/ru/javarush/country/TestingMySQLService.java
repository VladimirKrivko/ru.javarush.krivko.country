package ru.javarush.country;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.javarush.country.dao.CityDAO;
import ru.javarush.country.dao.CityDAOImpl;
import ru.javarush.country.dao.CountryDAO;
import ru.javarush.country.dao.CountryDAOImpl;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class TestingMySQLService {

    private final SessionFactory sessionFactory;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;

    public TestingMySQLService(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        cityDAO = new CityDAOImpl(sessionFactory);
        countryDAO = new CountryDAOImpl(sessionFactory);
    }

    public List<City> fetchData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            Transaction transaction = session.beginTransaction();

            List<Country> countries = countryDAO.getAll();

            int totalCount = cityDAO.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityDAO.getItems(i, step));
            }
            transaction.commit();
            return allCities;
        }
    }

    public void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }
}