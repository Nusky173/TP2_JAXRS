package fr.istic.taa.jaxrs.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {

    private static final EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal;

    static {
        emf = Persistence.createEntityManagerFactory("dev");
        threadLocal = new ThreadLocal<EntityManager>();
    }

    /**
     * Getter for Entity Manager
     * @return Entity Manager
     */
    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();

        if (em == null) {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    /**
     * Close Entity Manager
     */
    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            em.close();
            threadLocal.set(null);
        }
    }

    /**
     * Close Entity Manager Factory
     */
    public static void closeEntityManagerFactory() {
        emf.close();
    }

    /**
     * Begin Transaction
     */
    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    /**
     * Rollback
     */
    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    /**
     * Commit
     */
    public static void commit() {
        getEntityManager().getTransaction().commit();
    }
}