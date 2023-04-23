package ru.sonyabeldy.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.ls.LSOutput;
import ru.sonyabeldy.hibernate.model.*;

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
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            Person person = session.get(Person.class, 1);
            System.out.println("Got Person from table");
            System.out.println(person);

            session.getTransaction().commit();
            System.out.println("Session close");

            //Открываем сессию и транзакцию еще раз
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("In second session");

            person = session.merge(person);

            //альтернативный вариант загрузки сущностей
            List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId", Item.class)
                    .setParameter("personId", person.getId()).getResultList();
            System.out.println(items);

//            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();

//            System.out.println("Out of session");
//            System.out.println(person.getItems());

        } finally {
            sessionFactory.close();
        }
    }
}
