package gb.gestione_eventi.exceptions;

public class UnathorizedException extends RuntimeException {
    public UnathorizedException(String message) {
        super(message);
    }
}
