package ru.javarush.country.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.entity.City;

import java.util.List;

public class CityDAOImpl implements CityDAO{

    private final SessionFactory sessionFactory;

    public CityDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<City> getItems(int offset, int limit) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c", City.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public int getTotalCount() {
        Query<Integer> query = sessionFactory.getCurrentSession().createQuery("select count(c) from City c", Integer.class);
        return query.uniqueResult().intValue();
    }
}
