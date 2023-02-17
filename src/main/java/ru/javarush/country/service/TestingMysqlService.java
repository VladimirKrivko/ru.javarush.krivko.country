package ru.javarush.country.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.javarush.country.dao.CityDAO;
import ru.javarush.country.dao.CityHibernateDAO;
import ru.javarush.country.dao.CountryDAO;
import ru.javarush.country.dao.CountryHibernateDAO;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

public class TestingMysqlService implements TestingService {

    private static final int STEP_OF_FETCH_DATA = 500;
    private final SessionFactory sessionFactory;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;

    public TestingMysqlService(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        cityDAO = new CityHibernateDAO(sessionFactory);
        countryDAO = new CountryHibernateDAO(sessionFactory);
    }

    public List<City> fetchData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            Transaction transaction = session.beginTransaction();

            List<Country> countries = countryDAO.getAll();

            int totalCount = cityDAO.getTotalCount();
            for (int i = 0; i < totalCount; i += STEP_OF_FETCH_DATA) {
                allCities.addAll(cityDAO.getItems(i, STEP_OF_FETCH_DATA));
            }
            transaction.commit();
            return allCities;
        }
    }

    public void fetchData(List<Integer> ids) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            for (Integer id : ids) {
                City city = cityDAO.getById(id).get();
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            transaction.commit();
        }
    }

    public void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }
}
