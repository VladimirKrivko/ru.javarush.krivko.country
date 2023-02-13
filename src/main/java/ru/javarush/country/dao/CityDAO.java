package ru.javarush.country.dao;

import ru.javarush.country.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDAO {

    public List<City> getItems(int offset, int limit);

    public int getTotalCount();

    public Optional<City> getById(Integer id);
}