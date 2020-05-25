package ru.geekbrains;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.ls.LSOutput;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // INSERT
        EntityManager em = emFactory.createEntityManager();

        Customer customer1 = new Customer("Alex","F");
        Customer customer2 = new Customer("Ivan","A");
        Customer customer3 = new Customer("Jhon","N");

        Product product1 = new Product("Product1",120);
        Product product2 = new Product("Product2",80);
        Product product3 = new Product("Product3",150);

        em.getTransaction().begin();
        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.getTransaction().commit();

        //DELETE
        Customer customer = em.find(Customer.class,2L);
        Product product = em.find(Product.class,2L);

        em.getTransaction().begin();
        em.remove(customer);
        em.remove(product);
        em.getTransaction().commit();

       //SELECT
        // Список покупателей с их списками заказов
        List<Customer> customers = em.createQuery("from Customer c",Customer.class).getResultList();
        customers.forEach(System.out::println);
        // Список товаров с их списком покупателей
        List<Product> products = em.createQuery("from Product p",Product.class).getResultList();
        products.forEach(System.out::println);


    }
}
