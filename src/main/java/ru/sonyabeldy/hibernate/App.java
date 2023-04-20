package ru.sonyabeldy.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sonyabeldy.hibernate.model.Citizen;
import ru.sonyabeldy.hibernate.model.Item;
import ru.sonyabeldy.hibernate.model.Passport;
import ru.sonyabeldy.hibernate.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Citizen.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Citizen citizen = new Citizen("Test person", 30);
            Passport passport = new Passport(citizen, 123123);

            citizen.setPassport(passport);

            session.persist(citizen);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
