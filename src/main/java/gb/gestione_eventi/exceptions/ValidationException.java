package gb.gestione_eventi.exceptions;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ToString
@Slf4j
@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorsList;

    public ValidationException(List<String> errorsList) {
        super("Errori di validazione");
        this.errorsList = errorsList;
        errorsList.forEach(log::info);
    }

    public ValidationException(String message) {
        super(message);
    }
}
