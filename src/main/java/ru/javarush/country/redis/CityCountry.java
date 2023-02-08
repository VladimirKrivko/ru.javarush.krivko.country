package ru.javarush.country.redis;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.javarush.country.entity.Continent;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CityCountry {
    private Integer id;

    private String cityName;

    private String district;

    private Integer population;

    private String countryCode;

    private String alternativeCountryCode;

    private String countryName;

    private Continent continent;

    private String region;

    private BigDecimal countryArea;

    private Integer countryPopulation;

    private Set<Language> languages;
}