package ru.javarush.country.redis;

import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CityTransformer {

    public List<CityCountry> transformData(List<City> cities) {
        return cities
                .stream()
                .map(this::transformCity)
                .collect(Collectors.toList());
    }

    private CityCountry transformCity(City city) {
        CityCountry cityCountry = new CityCountry();
        cityCountry.setId(city.getId());
        cityCountry.setCityName(city.getName());
        cityCountry.setDistrict(city.getDistrict());
        cityCountry.setPopulation(city.getPopulation());

        Country country = city.getCountry();
        cityCountry.setCountryCode(country.getCode());
        cityCountry.setAlternativeCountryCode(country.getAlternativeCode());
        cityCountry.setCountryName(country.getName());
        cityCountry.setContinent(country.getContinent());
        cityCountry.setRegion(country.getRegion());
        cityCountry.setCountryArea(country.getArea());
        cityCountry.setCountryPopulation(country.getPopulation());

        Set<CountryLanguage> countryLanguages = country.getLanguages();
        cityCountry.setLanguages(getLanguages(countryLanguages));
        return cityCountry;
    }

    private Set<Language> getLanguages(Set<CountryLanguage> countryLanguages) {
        return countryLanguages.stream().map(cl -> {
            Language language = new Language();
            language.setName(cl.getLanguageName());
            language.setOfficial(cl.getOfficial());
            language.setPercentage(cl.getPercentage());
            return language;
        }).collect(Collectors.toSet());
    }
}