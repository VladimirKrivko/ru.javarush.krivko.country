package ru.javarush.country.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CityDAOImplTest {

    private SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    void setup() {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.properties")
                    .addAnnotatedClass(CityDAOImpl.class)
                    .buildSessionFactory();
        } catch (RuntimeException e) {
            //TODO: logger
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setupThis() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void tearThis() {
        session.getTransaction().commit();
    }

    @AfterAll
    void tear() {
        sessionFactory.close();
    }
}