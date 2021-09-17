package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.entities.Meeting;
import java.util.Date;
import java.util.List;

/**
 * Dao for Meeting
 */
public class MeetingDao extends AbstractJpaDao<Long, Meeting> {

    public MeetingDao() {
        super(Meeting.class);
    }

    /**
     * Find Meeting By Date
     * @param d Date of Meeting
     * @return Meeting
     */
    private List<Meeting> findByDate(Date d) {
        return this.entityManager.createQuery("Select m From Meeting m Where m.date = " + d, Meeting.class).getResultList();

    }

}
