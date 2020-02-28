package main.java.ru.java_mentor.karimov.dao;

import main.java.ru.java_mentor.karimov.DBHelper.ConfigurateDbHibernate;
import main.java.ru.java_mentor.karimov.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserHibernateDAO implements UserDAO{

    private static UserHibernateDAO instance;
    private static SessionFactory sessionFactory;

    private UserHibernateDAO(){
        this.sessionFactory = ConfigurateDbHibernate.getSessionFactory();

    }

    public static UserHibernateDAO getInstance(){
        if(instance == null){
            instance = new UserHibernateDAO();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers(){
        Session session = this.sessionFactory.openSession();
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
        Session session = this.sessionFactory.openSession();
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
        Session session = this.sessionFactory.openSession();
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
        Session session = this.sessionFactory.openSession();
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
        Session session = this.sessionFactory.openSession();
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
