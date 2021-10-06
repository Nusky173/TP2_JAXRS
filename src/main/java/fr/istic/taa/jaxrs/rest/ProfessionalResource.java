package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.concrete.ProfessionalDao;
import fr.istic.taa.jaxrs.entities.Agenda;

import javax.ws.rs.*;

@Path("/professional")
@Produces({"application/json"})
public class ProfessionalResource {

    @GET
    @Path("/{id}")
    public Agenda diffuseUrl(@PathParam("id") long id) {
        final var professionalDao = new ProfessionalDao();
        return professionalDao.findProfessionalAgenda(id);
    }


}
