package ru.javarush.country.dao;

import ru.javarush.country.entity.Country;

import java.util.List;

public interface CountryDao {

    public List<Country> getAll();
}
