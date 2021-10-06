package fr.istic.taa.jaxrs.Infrastructure.User;

import fr.istic.taa.jaxrs.dao.concrete.AgendaDao;
import fr.istic.taa.jaxrs.dao.concrete.ProfessionalDao;
import fr.istic.taa.jaxrs.entities.Professional;

public class AddProfessional {

    public AddProfessional() {
    }

    /**
     *
     * @param professional to add to the user list if his agenda is already created.
     */
    public void addProfessional(Professional professional) {
        final var agenda = new AgendaDao().findOne(professional.getAgenda().getId());
        final var professionalDao = new ProfessionalDao();
        if (agenda != null ) {
            professionalDao.save(professional);
        }

    }

}
