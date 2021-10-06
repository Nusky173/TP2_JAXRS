package fr.istic.taa.jaxrs.dao.concrete;

import fr.istic.taa.jaxrs.dao.AbstractJpaDao;
import fr.istic.taa.jaxrs.entities.User;

public class UserDao extends AbstractJpaDao<Long, User> {

    public UserDao() {
        super(User.class);
    }

    public User findUserLogAndPassword(String login, String password) {
        return this.entityManager.createNamedQuery("findUserByLogAndPassword", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
    }


}
