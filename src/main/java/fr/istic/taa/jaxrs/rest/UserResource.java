package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.Infrastructure.User.*;
import fr.istic.taa.jaxrs.dao.concrete.IndividualDao;
import fr.istic.taa.jaxrs.dao.concrete.UserDao;
import fr.istic.taa.jaxrs.entities.Individual;
import fr.istic.taa.jaxrs.entities.Professional;
import fr.istic.taa.jaxrs.entities.User;

import javax.ws.rs.*;
import java.util.List;

@Path("/users")
@Produces({"application/json"})
public class UserResource {

    @GET
    public List<User> getUsers() {
        return new UserDao().findAll();
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id")long id) {
        return new UserDao().findOne(id);
    }

    @POST
    @Path("/professional")
    @Consumes({"application/json"})
    public Professional addProfessional(Professional professional) {
        final var prof = new AddProfessional();
        prof.addProfessional(professional);
        return professional;
    }

    @POST
    @Path("/individual")
    @Consumes({"application/json"})
    public Individual addIndividual(Individual individual) {
        final var individualDao = new IndividualDao();
        individualDao.save(individual);
        return individual;
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id")long id){
        final var temp = new DeleteUser();
        temp.deleteUser(id);
    }

    @POST
    @Path("/log")
    @Consumes({"application/json"})
    public User logUser(@QueryParam("login") String login, @QueryParam("password") String password) {
        final var result = new UserDao().findUserLogAndPassword(login, password);
        if( result != null ) {
            System.out.println("Bienvenue : " + login );
            return result;
        } else {
            return null;
        }
    }


}
