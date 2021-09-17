package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.entities.Agenda;
import fr.istic.taa.jaxrs.entities.Professional;

import java.util.List;

/**
 * Dao of Professional
 */
public class ProfessionalDao extends AbstractJpaDao<Long, Professional> {

    public ProfessionalDao() {
        super(Professional.class);
    }

    /**
     * Find Professional by Login
     * @param login of Professional
     * @return Professional associated to Login
     */
    public Professional findByLogin(String login) {
        return this.entityManager.createNamedQuery("getProfessionalFromLogin", Professional.class).setParameter("login", login).getSingleResult();
    }

    /**
     * Find Professional Agenda
     * @param id of Professional
     * @return Agenda associated to Professional
     */
    public List<Agenda> findProfessionalAgenda(long id) {
        return this.entityManager.createNamedQuery("getAgendaOfProfessional", Agenda.class).setParameter("id", id).getResultList();
    }


}
