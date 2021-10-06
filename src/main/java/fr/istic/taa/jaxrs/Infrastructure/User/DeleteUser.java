package fr.istic.taa.jaxrs.Infrastructure.User;

import fr.istic.taa.jaxrs.dao.concrete.AgendaDao;
import fr.istic.taa.jaxrs.dao.concrete.MeetingDao;
import fr.istic.taa.jaxrs.dao.concrete.ProfessionalDao;
import fr.istic.taa.jaxrs.dao.concrete.UserDao;

public class DeleteUser {

    public DeleteUser(){}

    /**
     * This will also delete all the meets that this user could have take, or his agenda
     * @param id the id of the user to delete
     */
    public void deleteUser(long id) {
        final var userDao = new UserDao();
        final var professionalDao = new ProfessionalDao();
        if ( professionalDao.findOne(id) == null ) {
            final var meetingDao = new MeetingDao();
            meetingDao.findByIndiv(id).forEach(m -> {
                if (m != null) meetingDao.delete(m);
            });
        } else {
            final var agendaDao = new AgendaDao();
            agendaDao.deleteById(professionalDao.findProfessionalAgenda(id).getId());
        }
        userDao.deleteById(id);

    }
}
