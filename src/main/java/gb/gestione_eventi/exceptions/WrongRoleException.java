package gb.gestione_eventi.exceptions;

public class WrongRoleException extends RuntimeException {
    public WrongRoleException(String message) {
        super(message);
    }
}
