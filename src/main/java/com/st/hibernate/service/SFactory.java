package com.st.hibernate.service;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;

public class SFactory {
    private static EntityManagerFactory entityManagerFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public static EntityManagerFactory getFactory(){
        return entityManagerFactory;
    }

    public static void closeFactory(){
        entityManagerFactory.close();
    }

}
