package fr.istic.taa.jaxrs.jpa;

import fr.istic.taa.jaxrs.dao.concrete.AgendaDao;
import fr.istic.taa.jaxrs.dao.concrete.IndividualDao;
import fr.istic.taa.jaxrs.dao.concrete.MeetingDao;
import fr.istic.taa.jaxrs.dao.concrete.ProfessionalDao;
import fr.istic.taa.jaxrs.entities.Agenda;
import fr.istic.taa.jaxrs.entities.Individual;
import fr.istic.taa.jaxrs.entities.Meeting;
import fr.istic.taa.jaxrs.entities.Professional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;


public class MainJPA {

    private EntityManager manager;

    public MainJPA(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Main class
     *
     * @param args Some args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        MainJPA jpa = new MainJPA(manager);

        // Dao initialisation
        AgendaDao agendaDao = new AgendaDao();
        MeetingDao meetingDao = new MeetingDao();
        IndividualDao individualDao = new IndividualDao();
        ProfessionalDao professionalDao = new ProfessionalDao();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            jpa.createDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        jpa.readDB(agendaDao, meetingDao, individualDao, professionalDao);

        manager.close();
        System.out.println("END");
    }

    /**
     * Create DB
     */
    private void createDB() {
        Agenda agenda = new Agenda("http://url", "slecordiagenda", "triiiiix");
        Individual individual1 = new Individual("Alexis", "Leloup", "IndividualLogin1"
                , "1234", "0651515151");
        Individual individual2 = new Individual("Antoine", "Cornilleau", "IndividualLogin2"
                , "1234", "0653435152");
        Meeting meeting1 = new Meeting(new Date(), "meeting1", individual1);
        Meeting meeting2 = new Meeting(new Date(), "meeting2", individual2);
        agenda.addMeeting(meeting1);
        agenda.addMeeting(meeting2);
        manager.persist(agenda);
        manager.persist(new Professional("Simon", "Lecordier", "slecordier"
                , "684984", agenda));
        manager.persist(meeting1);
        manager.persist(meeting2);
        manager.persist(individual1);
        manager.persist(individual2);
    }

    /**
     * Read DB
     * @param agendaDao       Dao for Agenda object
     * @param meetingDao      Dao for Meeting object
     * @param individualDao   Dao for Meeting object
     * @param professionalDao Dao for Professional object
     */
    private void readDB(AgendaDao agendaDao, MeetingDao meetingDao, IndividualDao individualDao, ProfessionalDao professionalDao) {
        System.out.println("\n=======================\n");
        System.out.println("\nAgenda :");
        for (Agenda a : agendaDao.findAll()) {
            System.out.println(a.toString());
            //System.out.println("Agenda meeting is : ");
            //System.out.println(agendaDao.listMeeting(a.getId()));
        }


        System.out.println("\nMeeting :");
        for (Meeting m : meetingDao.findAll()) {
            System.out.println(m.toString());
        }

        System.out.println("\nIndividual :");
        for (Individual i : individualDao.findAll()) {
            System.out.println(i.toString());
        }

        System.out.println("\nProfessional :");
        for (Professional p : professionalDao.findAll()) {
            System.out.println(p.toString());
            System.out.println("Agenda of professional from DAO :");
            System.out.println(professionalDao.findProfessionalAgenda(p.getId()));
        }
    }


}
