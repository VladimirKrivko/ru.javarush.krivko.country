package ru.javarush.country.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.entity.City;

import java.util.List;
import java.util.Optional;

public class CityHibernateDao implements CityDao {

    private final SessionFactory sessionFactory;

    public CityHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<City> getById(Integer id) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :id", City.class);
        query.setParameter("id", id);
        try {
            City city = query.getSingleResult();
            return Optional.of(city);
        } catch (NoResultException e) {
            return Optional.empty();
        }
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
