package fr.istic.taa.jaxrs.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "getProfessionalFromLogin", query = "Select p From Professional p Where p.login = :login"),
        @NamedQuery(name = "getAgendaOfProfessional", query = "Select p.agenda From Professional p Where p.id = :id")
})
public class Professional extends User implements Serializable {

    public Agenda agenda;

    /**
     * Representation of a Professional
     * @param firstName of Professional
     * @param lastName of Professional
     * @param login of Professional
     * @param password of Professional
     * @param agenda of Professional
     */
    public Professional(String firstName, String lastName, String login, String password, Agenda agenda) {
        super(firstName, lastName, login, password);
        this.agenda = agenda;
    }

    public Professional() {

    }

    /**
     * Get Agenda of Professional
     * @return agenda
     */
    @OneToOne
    public Agenda getAgenda() {
        return agenda;
    }

    /**
     * Set Agenda of Professional
     * @param agenda of professional
     */
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public String toString() {
        return "Professional{" +
                "agenda=" + agenda.toString() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
