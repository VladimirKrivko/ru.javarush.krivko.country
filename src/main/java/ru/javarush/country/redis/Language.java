package ru.javarush.country.redis;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Language {
    private String name;
    private Boolean official;
    private BigDecimal percentage;
}