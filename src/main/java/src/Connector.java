package src;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Connector implements Serializable {

    private EntityManagerFactory managerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    public Connector() {
        managerFactory = Persistence.createEntityManagerFactory("default");
        manager = managerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    private static final String DRIVER = "org.postgresql.Driver";

    private static Connection init() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "O2l4e0g3");
        connection.createStatement().execute("create table if not exists dat(d bytea)");
        return connection;
    }

    public void sendOne(Dot dot) {
        try {
            transaction.begin();
            manager.persist(dot);
            transaction.commit();
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
        }
    }

    public void clearAll() {
        try {
            init().createStatement().execute("delete from dat");
        } catch (Exception e) {
            err();
        }
    }

    public ArrayList<Dot> getAll() {
        try {
            transaction.begin();

            ArrayList<Dot> res = new ArrayList<Dot>(manager.createQuery("select e from Dot e", Dot.class).getResultList());

            transaction.commit();
            return res;
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            return new ArrayList<Dot>();
        }
    }
    private static void err() {
        System.out.println("ОшибОчка");
    }
}
