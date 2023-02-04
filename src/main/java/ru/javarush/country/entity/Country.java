package ru.javarush.country.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "world", name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @Column(name = "code_2", length = 2, nullable = false)
    private String code_2;

    @Column(name = "name", length = 52, nullable = false)
    private String name;

    @Column(name = "continent", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Continent continent;

    @Column(name = "region", length = 26, nullable = false)
    private String region;

    @Column(name = "surface_area", nullable = false)
    private BigDecimal area;

    @Column(name = "indep_year")
    private Short yearOfIndependence;

    @Column(name = "population", nullable = false)
    private Integer population;

    @Column(name = "life_expectancy")
    private BigDecimal lifeExpectancy;

    @Column(name = "gnp")
    private BigDecimal GNP;

    @Column(name = "gnpo_id")
    private BigDecimal GNPOId;

    @Column(name = "local_name", length = 45, nullable = false)
    private String localName;

    @Column(name = "government_form", length = 45, nullable = false)
    private String government;

    @Column(name = "head_of_state", length = 60)
    private String headOfState;

    @OneToOne
    @JoinColumn(name = "capital")
    private City capital;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Set<CountryLanguage> languages;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode_2() {
        return code_2;
    }

    public void setCode_2(String code_2) {
        this.code_2 = code_2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Short getYearOfIndependence() {
        return yearOfIndependence;
    }

    public void setYearOfIndependence(Short yearOfIndependence) {
        this.yearOfIndependence = yearOfIndependence;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public BigDecimal getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(BigDecimal lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public BigDecimal getGNP() {
        return GNP;
    }

    public void setGNP(BigDecimal GNP) {
        this.GNP = GNP;
    }

    public BigDecimal getGNPOId() {
        return GNPOId;
    }

    public void setGNPOId(BigDecimal GNPOId) {
        this.GNPOId = GNPOId;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public Set<CountryLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<CountryLanguage> languages) {
        this.languages = languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return  id.equals(country.id) &&
                code.equals(country.code) &&
                code_2.equals(country.code_2) &&
                name.equals(country.name) &&
                continent == country.continent &&
                region.equals(country.region) &&
                area.equals(country.area) &&
                Objects.equals(yearOfIndependence, country.yearOfIndependence) &&
                population.equals(country.population) &&
                Objects.equals(lifeExpectancy, country.lifeExpectancy) &&
                Objects.equals(GNP, country.GNP) &&
                Objects.equals(GNPOId, country.GNPOId) &&
                localName.equals(country.localName) &&
                government.equals(country.government) &&
                Objects.equals(headOfState, country.headOfState) &&
                Objects.equals(capital, country.capital) &&
                Objects.equals(languages, country.languages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, code_2, name, continent, region, area, yearOfIndependence,
                            population, lifeExpectancy, GNP, GNPOId, localName, government,
                            headOfState, capital, languages);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Country{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", code_2='").append(code_2).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", continent=").append(continent);
        sb.append(", region='").append(region).append('\'');
        sb.append(", area=").append(area);
        sb.append(", yearOfIndependence=").append(yearOfIndependence);
        sb.append(", population=").append(population);
        sb.append(", lifeExpectancy=").append(lifeExpectancy);
        sb.append(", GNP=").append(GNP);
        sb.append(", GNPOId=").append(GNPOId);
        sb.append(", localName='").append(localName).append('\'');
        sb.append(", government='").append(government).append('\'');
        sb.append(", headOfState='").append(headOfState).append('\'');
        sb.append(", capital=").append(capital);
        sb.append(", languages=").append(languages);
        sb.append('}');
        return sb.toString();
    }
}