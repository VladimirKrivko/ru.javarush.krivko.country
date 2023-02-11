package ru.javarush.country.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.entity.City;

import java.util.List;
import java.util.Optional;

public class CityDAOImpl implements CityDAO {

    private final SessionFactory sessionFactory;

    public CityDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //TODO: Optional не работает! Переделать.
    @Override
    public City getById(Integer id) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :id", City.class);
        query.setParameter("id", id);
        City city = query.getSingleResult();
        City result = Optional.ofNullable(city).orElseGet(() -> {
            Query<City> defaultQuery = sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = 1", City.class);
            return defaultQuery.getSingleResult();
        });
        return result;
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
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(c) from City c", Long.class);
        return query.uniqueResult().intValue();
    }
}