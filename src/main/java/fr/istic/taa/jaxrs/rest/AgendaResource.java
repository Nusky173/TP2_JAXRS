package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.Infrastructure.Meeting.BookMeet;
import fr.istic.taa.jaxrs.Infrastructure.Meeting.Exception.BookMeetWithProfessionalException;
import fr.istic.taa.jaxrs.Infrastructure.Meeting.Exception.UnexistingAgendaException;
import fr.istic.taa.jaxrs.dao.AgendaDao;
import fr.istic.taa.jaxrs.dao.MeetingDao;
import fr.istic.taa.jaxrs.entities.Agenda;
import fr.istic.taa.jaxrs.entities.Meeting;

import javax.ws.rs.*;
import java.util.List;

@Path("/agenda")
@Produces({"application/json"})
public class AgendaResource {

    @GET
    public List<Agenda> getAgendas() {
        return new AgendaDao().findAll();
    }

    /**
     *
     * @param id id of an agenda
     * @return return all of the meetings that have been take.
     */
    @GET
    @Path("/{id}")
    public List<Meeting> getMeetings(@PathParam("id")long id) {
        return new AgendaDao().listMeeting(id);
    }

    @DELETE
    @Path("/{id}")
    public void deleteAgenda(@PathParam("id") long id ) {
        final var agendaDao = new AgendaDao();
        agendaDao.deleteById(id);
    }

    @POST
    @Path("/connect")
    public List<Meeting> connectToAgenda(@QueryParam("login") String login,
                                         @QueryParam("password") String password) {
        final var agendaObject = new AgendaDao().connectToAgenda(login, password);
        return getMeetings(agendaObject.getId());
    }

    @POST
    @Consumes("application/json")
    public Agenda createAgenda(Agenda agenda){
        final var agendaDao = new AgendaDao();
        agendaDao.save(agenda);
        return agenda;
    }

    @POST
    @Path("{idAgenda}/book")
    @Consumes({"application/json"})
    public Meeting bookMeet(@QueryParam("idUser") long idUser, @PathParam("idAgenda")long idAgenda,
                            Meeting  meeting)
            throws BookMeetWithProfessionalException, UnexistingAgendaException {
        try {
            return new BookMeet(meeting, idUser, idAgenda).bookAMeeting();
        } catch (BookMeetWithProfessionalException | UnexistingAgendaException e) {
            throw e;
        }

    }
}
