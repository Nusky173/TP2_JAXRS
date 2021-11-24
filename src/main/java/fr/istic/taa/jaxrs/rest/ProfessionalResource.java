package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.concrete.ProfessionalDao;
import fr.istic.taa.jaxrs.entities.Agenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.*;

@Path("/professional")
@Produces({"application/json"})
public class ProfessionalResource {

    @GET
    @Path("/{id}")
    @Operation(summary = "Return the agenda linked to the professional of the id supplied",
            tags = {"agenda", "professional"},
            responses = {
                    @ApiResponse(description = "The agenda of the professional", content = @Content(
                            schema = @Schema(implementation = Agenda.class)
                    )),
                    @ApiResponse(responseCode = "404", description = "Professional not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied")
            })
    public Agenda diffuseUrl(@Parameter(description = "the id of the professional", required = true)@PathParam("id") long id) {
        final var professionalDao = new ProfessionalDao();
        return professionalDao.findProfessionalAgenda(id);
    }


}
