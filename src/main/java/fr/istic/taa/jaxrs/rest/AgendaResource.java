package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.Infrastructure.Meeting.BookMeet;
import fr.istic.taa.jaxrs.Infrastructure.Meeting.Exception.BookMeetWithProfessionalException;
import fr.istic.taa.jaxrs.Infrastructure.Meeting.Exception.UnexistingAgendaException;
import fr.istic.taa.jaxrs.dao.concrete.AgendaDao;
import fr.istic.taa.jaxrs.entities.Agenda;
import fr.istic.taa.jaxrs.entities.Meeting;
import fr.istic.taa.jaxrs.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.*;
import java.util.List;

@Path("/agenda")
@Produces({"application/json"})
public class AgendaResource {

    @GET
    @Operation(summary = "Find all the agendas async",
            tags = {"agendas"},
            description = "Returns all the agendas of the applications in cascade.",
            responses = {
                    @ApiResponse(description = "All the agendas", content = @Content(
                            schema = @Schema(implementation = Agenda.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Invalid request supplied"),
                    @ApiResponse(responseCode = "404", description = "Users not found")
            })
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
    @Operation(summary = "Get all the meetings of a specific agenda",
            tags = {"agenda", "meeting"},
            description = "Return a list of the meetings included in the specific agenda",
            responses = {
                    @ApiResponse(description = "A list of Meetings", content = @Content(
                            schema = @Schema(implementation = Meeting.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
                    @ApiResponse(responseCode = "404", description = "Agenda not found")
            })
    public List<Meeting> getMeetings(@Parameter(
            description = "The id of the agenda that we want to get all the meetings",
            required = true)@PathParam("id")long id) {
        return new AgendaDao().listMeeting(id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete an agenda of the app",
            tags = {"agenda"},
            responses = {
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
                    @ApiResponse(responseCode = "404", description = "agenda not found"),
            })
    public void deleteAgenda(@Parameter(
            description = "the id of the agenda to be deleted",
            required = true)@PathParam("id") long id ) {
        final var agendaDao = new AgendaDao();
        agendaDao.deleteById(id);
    }

    @POST
    @Path("/connect")
    @Operation(summary = "Connect to an agenda",
            tags = {"users"},
            description = "Connect to a professional agenda to get all the meetings in purpose to reserved one",
            responses = {
                    @ApiResponse(description = "The user that match with the login entries", content = @Content(
                            schema = @Schema(implementation = Meeting.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Invalid request supplied"),
                    @ApiResponse(responseCode = "404", description = "Agenda not found")
            })
    public List<Meeting> connectToAgenda(@Parameter(
            description = "The login and the password that match the agenda informations",
            required = true)@QueryParam("login") String login,
                                         @QueryParam("password") String password) {
        final var agendaObject = new AgendaDao().connectToAgenda(login, password);
        return getMeetings(agendaObject.getId());
    }

    @POST
    @Consumes("application/json")
    @Operation(summary = "Create an agenda to the app for a professional",
            tags = {"agenda", "professional"},
            responses = {
                    @ApiResponse(responseCode = "405", description = "Invalid input")
            })
    public Agenda createAgenda(@Parameter(
            description = "agenda object required to be added into the app",
            required = true)Agenda agenda){
        final var agendaDao = new AgendaDao();
        agendaDao.save(agenda);
        return agenda;
    }

    @POST
    @Path("{idAgenda}/book")
    @Consumes({"application/json"})
    @Operation(summary = "Book a meet in the agenda of a professional",
            tags = {"Meeting", "professional", "individual", ""},
            responses = {
                    @ApiResponse(description = "Return the meet that the user book on an agenda", content = @Content(
                            schema = @Schema(implementation = Meeting.class)
                    )),
                    @ApiResponse(responseCode = "404", description = "No request found"),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
                    @ApiResponse(responseCode = "500", description = "Invalid argument or method")
            })
    public Meeting bookMeet(@Parameter(
            description = "Id of the user, and the agenda and the meet to be booked",
            required = true)@QueryParam("idUser") long idUser, @PathParam("idAgenda")long idAgenda,
                            Meeting  meeting)
            throws BookMeetWithProfessionalException, UnexistingAgendaException {
        try {
            return new BookMeet(meeting, idUser, idAgenda).bookAMeeting();
        } catch (BookMeetWithProfessionalException | UnexistingAgendaException e) {
            throw e;
        }

    }
}
