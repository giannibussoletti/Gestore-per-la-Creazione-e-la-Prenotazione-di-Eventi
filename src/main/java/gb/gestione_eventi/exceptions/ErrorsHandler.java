package gb.gestione_eventi.exceptions;

import gb.gestione_eventi.payloads.ErrorsDTO;
import gb.gestione_eventi.payloads.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO alreadyExists(AlreadyExistsException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO notFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsDTO unauthorizedException(UnauthorizedException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(WrongRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO wrongRoleError(WrongRoleException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO listValidationErrors(ValidationException ex) {
        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsList());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO listValidationErrors(NoResourceFoundException ex) {
        return new ErrorsDTO("Risorsa non trovata", LocalDateTime.now());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO listValidationErrors(HttpMessageNotReadableException ex) {
        return new ErrorsDTO("La richiesta non è stata fatta correttamente, c'è un errore nel body", LocalDateTime.now());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorsDTO accessDeniedErrors(AuthorizationDeniedException ex) {
        return new ErrorsDTO("Non si hanno i requisiti minimi di accesso", LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO InternalServerError(Exception ex) {
        System.out.println(ex.getMessage());
        return new ErrorsDTO("C'è stato un errore interno del server", LocalDateTime.now());
    }


}
