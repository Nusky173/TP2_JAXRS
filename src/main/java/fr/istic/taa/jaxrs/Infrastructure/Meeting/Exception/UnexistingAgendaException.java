package fr.istic.taa.jaxrs.Infrastructure.Meeting.Exception;

import java.util.concurrent.ExecutionException;

public class UnexistingAgendaException extends ExecutionException {
    public UnexistingAgendaException(String message) {
        super();

    }
}
