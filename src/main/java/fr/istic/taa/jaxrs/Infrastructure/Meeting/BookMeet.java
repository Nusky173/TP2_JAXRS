package fr.istic.taa.jaxrs.Infrastructure.Meeting;

import fr.istic.taa.jaxrs.Infrastructure.Meeting.Exception.BookMeetWithProfessionalException;
import fr.istic.taa.jaxrs.Infrastructure.Meeting.Exception.UnexistingAgendaException;
import fr.istic.taa.jaxrs.Infrastructure.Shared.GetUserClass;
import fr.istic.taa.jaxrs.dao.concrete.AgendaDao;
import fr.istic.taa.jaxrs.dao.concrete.IndividualDao;
import fr.istic.taa.jaxrs.dao.concrete.MeetingDao;
import fr.istic.taa.jaxrs.entities.Meeting;

public class BookMeet {

    public Meeting meeting;

    public long idUser;
    public long idAgenda;

    public BookMeet(Meeting meeting, long idUser, long idAgenda){
        this.meeting = meeting;
        this.idUser = idUser;
        this.idAgenda = idAgenda;
    }

    /**
     * @return The Meeting thas has be booked by the user {idUser} with the professional linked to agenda {idAgenda}
     * @throws BookMeetWithProfessionalException A professional can't book a meet with his professional account
     * @throws UnexistingAgendaException
     */
    public Meeting bookAMeeting() throws BookMeetWithProfessionalException, UnexistingAgendaException {
        final var agendaDao = new AgendaDao();
        final var agenda = agendaDao.findOne(idAgenda);
        //only individual can book a meeting
        final var isIndividual = new GetUserClass(idUser).isIndividual();
        if ( agenda == null) {
            throw new UnexistingAgendaException("This agenda does not exist");
        } else {
            if (isIndividual) {
                final var individualDao = new IndividualDao().findOne(idUser);
                meeting.setPatient(individualDao);
                final var meetingDao = new MeetingDao();
                meetingDao.save(meeting);
                agenda.addMeeting(meeting);
                agendaDao.update(agenda);
                return meeting;
            } else {
                throw new BookMeetWithProfessionalException("\"Impossible to book a meet with a professional account.\"");
            }
        }

    }
}
