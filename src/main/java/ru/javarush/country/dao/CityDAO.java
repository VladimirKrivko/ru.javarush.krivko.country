package ru.javarush.country.dao;

import ru.javarush.country.entity.City;

import java.util.List;

public interface CityDAO {
    public List<City> getItems(int offset, int limit);

    public int getTotalCount();
}
