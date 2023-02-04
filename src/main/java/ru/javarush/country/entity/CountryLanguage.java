package ru.javarush.country.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(schema = "world", name = "country_language")
public class CountryLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "language", length = 30, nullable = false)
    private String language;

    @Column(name = "is_official", columnDefinition = "BIT", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isOfficial;

    @Column(name = "percentage", nullable = false)
    private BigDecimal percentage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryLanguage that = (CountryLanguage) o;
        return  id.equals(that.id) &&
                country.equals(that.country) &&
                language.equals(that.language) &&
                isOfficial.equals(that.isOfficial) &&
                percentage.equals(that.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, language, isOfficial, percentage);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CountryLanguage{");
        sb.append("id=").append(id);
        sb.append(", country=").append(country);
        sb.append(", language='").append(language).append('\'');
        sb.append(", isOfficial=").append(isOfficial);
        sb.append(", percentage=").append(percentage);
        sb.append('}');
        return sb.toString();
    }
}
