package fr.istic.taa.jaxrs.dao.concrete;

import fr.istic.taa.jaxrs.dao.AbstractJpaDao;
import fr.istic.taa.jaxrs.entities.Meeting;
import fr.istic.taa.jaxrs.entities.Agenda;

import java.util.List;

/**
 * Dao for Agenda
 */
public class AgendaDao extends AbstractJpaDao<Long, Agenda> {

    public AgendaDao() {
        super(Agenda.class);
    }

    /**
     * List Meetings of en Agenda from DB
     * @param id of Agenda
     * @return Meeting list
     */
    public List<Meeting> listMeeting(long id) {
        return this.entityManager.createNamedQuery("listMeetingOfAgenda", Meeting.class)
                .setParameter("id", id)
                .getResultList();
    }

    public Agenda connectToAgenda(String login, String password) {
        return this.entityManager.createNamedQuery("connectToAgenda", Agenda.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
    }
}
