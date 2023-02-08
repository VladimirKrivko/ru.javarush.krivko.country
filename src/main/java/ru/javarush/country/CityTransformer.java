package ru.javarush.country;

import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;
import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.redis.Language;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CityTransformer {

    public List<CityCountry> transformData(List<City> cities) {
        return cities.stream().map(city -> {
            CityCountry res = new CityCountry();
            res.setId(city.getId());
            res.setCityName(city.getName());
            res.setDistrict(city.getDistrict());
            res.setPopulation(city.getPopulation());

            Country country = city.getCountry();
            res.setCountryCode(country.getCode());
            res.setAlternativeCountryCode(country.getAlternativeCode());
            res.setCountryName(country.getName());
            res.setContinent(country.getContinent());
            res.setRegion(country.getRegion());
            res.setCountryArea(country.getArea());
            res.setCountryPopulation(country.getPopulation());

            Set<CountryLanguage> countryLanguages = country.getLanguages();
            res.setLanguages(getLanguages(countryLanguages));
            return res;
        }).collect(Collectors.toList());
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