package main.java.ru.java_mentor.karimov.dao;

import static main.java.ru.java_mentor.karimov.DBHelper.ConfigurateDbHibernate.getMySqlConfiguration;
import main.java.ru.java_mentor.karimov.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.List;

public class UserHibernateDAO implements UserDAO{

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        if (sessionFactory == null) {
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);;
        }
        return sessionFactory;
    }

    @Override
    public List<User> getAllUsers(){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = null;
        try{
            users = session.createQuery("from User").list();
            transaction.commit();
            session.close();
        }catch (HibernateException hibernateEx){
            try{
                transaction.rollback();
            }catch (RuntimeException runtimeEx){

            }
            hibernateEx.printStackTrace();
        }finally {
            if(session!= null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void insertUser(User user) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(user);
            transaction.commit();
            session.close();
        }catch (HibernateException hibernateEx){
            try{
                transaction.rollback();
            }catch (RuntimeException runtimeEx){

            }
        }finally {
            if(session!= null) {
                session.close();
            }
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            User userEntity = session.get(User.class, user.getId());
            userEntity.setName(user.getName());
            userEntity.setAddress(user.getAddress());
            transaction.commit();
            session.close();
        }catch (HibernateException hibernateEx){
            try{
                transaction.rollback();
            }catch (RuntimeException runtimeEx){

            }
        }finally {
            if(session!= null) {
                session.close();
            }
        }
    }

    @Override
    public User getUserByID(Long id){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;
        try{
            user = session.get(User.class, id);
            transaction.commit();
            session.close();
        }catch (HibernateException hibernateEx){
            try{
                transaction.rollback();
            }catch (RuntimeException runtimeEx){

            }
        }finally {
            if(session!= null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public void deleteUser(Long id){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;
        try{
            user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();
        }catch (HibernateException hibernateEx){
            try{
                transaction.rollback();
            }catch (RuntimeException runtimeEx){

            }
        }finally {
            if(session!= null) {
                session.close();
            }
        }
    }
}
