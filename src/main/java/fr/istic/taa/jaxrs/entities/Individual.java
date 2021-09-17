package fr.istic.taa.jaxrs.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQuery(name = "findByTel", query = "Select i From Individual i Where i.tel = :tel")
public class Individual extends User implements Serializable {

    public String tel;

    /**
     * Representation of an Individual user
     * @param firstName of User
     * @param lastName of User
     * @param login of User
     * @param password of User
     * @param tel of User
     */
    public Individual(String firstName, String lastName, String login, String password, String tel) {
        super(firstName, lastName, login, password);
        this.tel = tel;
    }

    public Individual() {
        super();
    }

    /**
     * Getter for tel
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Setter for tel
     * @param tel of Individual
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "tel='" + tel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
