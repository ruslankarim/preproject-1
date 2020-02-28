package main.java.ru.java_mentor.karimov.dao;

public class UserDaoFactory {
    public UserJdbcDAO getJdbcDao(){
        return UserJdbcDAO.getInstance();
    }

    public UserHibernateDAO getHibernateDao(){
        return UserHibernateDAO.getInstance();
    }
}
