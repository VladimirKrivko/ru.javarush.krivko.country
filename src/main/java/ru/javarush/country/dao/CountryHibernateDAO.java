package ru.javarush.country.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.entity.Country;

import java.util.List;

public class CountryHibernateDAO implements CountryDAO {

    private final SessionFactory sessionFactory;

    public CountryHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Country> getAll() {
        Query<Country> query = sessionFactory.getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class);
        return query.list();
    }
}
