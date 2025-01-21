package org.example.entities;

import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class familiAcogidaTest {

    @Test
    public void createTablesTest(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }
}