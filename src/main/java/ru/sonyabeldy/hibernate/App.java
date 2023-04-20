package ru.sonyabeldy.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sonyabeldy.hibernate.model.Item;
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
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("Test cascading", 30);

            person.addItem(new Item("Item 1"));
            person.addItem(new Item("Item 2"));
            person.addItem(new Item("Item 3"));

            session.persist(person);


//            Person person = session.get(Person.class, 2);
//            Item item = session.get(Item.class, 1);
//            //из старого владельца
//            item.getOwner().getItems().remove(item);
//
//            item.setOwner(person);
//            person.getItems().add(item);





//            //SQL
//            session.remove(person);
//
//            //Было правильное состояние Hibernate кэша
//            person.getItems().forEach(i -> i.setOwner(null));

//            Person person = session.get(Person.class, 3);
//            List<Item> items = person.getItems();
//
////         SQL
//            for (Item item: items
//                 ) {
//                session.remove(item);
//            }
////            Не порождает SQL, но необходимо для того, чтобы в кэше все было верно
//            person.getItems().clear();


//            Person person = new Person("Test person", 30);
//            Item newItem = new Item("Item from Hibernate 2", person);
//            person.setItems(new ArrayList<>(Collections.singletonList(newItem)));
//
//            session.save(person);
//            session.save(newItem);


//            Добавление человека
//            Person person = session.get(Person.class, 2);
//
//            Item newItem = new Item("Item from Hibernate", person);
//            person.getItems().add(newItem);
//
//            session.save(newItem);



//           Item item = session.get(Item.class, 5);
//            System.out.println(item);
//
//            Person person = item.getOwner();
//            System.out.println(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
