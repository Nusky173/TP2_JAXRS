package fr.istic.taa.jaxrs.dao.concrete;

import fr.istic.taa.jaxrs.dao.AbstractJpaDao;
import fr.istic.taa.jaxrs.entities.Agenda;
import fr.istic.taa.jaxrs.entities.Professional;

/**
 * Dao of Professional
 */
public class ProfessionalDao extends AbstractJpaDao<Long, Professional> {

    public ProfessionalDao() {
        super(Professional.class);
    }

    /**
     * Find Professional Agenda
     * @param id of Professional
     * @return Agenda associated to Professional
     */
    public Agenda findProfessionalAgenda(long id) {
        return this.entityManager.createNamedQuery("getAgendaOfProfessional", Agenda.class)
                .setParameter("id", id).getSingleResult();
    }


}
