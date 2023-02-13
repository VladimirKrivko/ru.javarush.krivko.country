package ru.javarush.country.dao;

import ru.javarush.country.entity.Country;

import java.util.List;

public interface CountryDAO {

    public List<Country> getAll();
}