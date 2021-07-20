package de.neuefische.todobackend;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.persistence.OptimisticLockException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ComponentScan
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        log.error("Resource not found! Exception: ", ex);

        Map<String, Object> body = generateErrorResponse(
            "Resource not found!",
            ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ObjectOptimisticLockingFailureException.class, StaleObjectStateException.class,
        OptimisticLockException.class})
    public final ResponseEntity<Object> handleOptimisticLockException(Exception ex, WebRequest request) {
        log.error("Optimistic Lock! Resource was edited in the meantime. Please reload! Exception: ", ex);

        Map<String, Object> body = generateErrorResponse(
            "Optimistic Lock! Resource was edited in the meantime. Please reload!",
            ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(Exception ex, WebRequest request) {
        log.error("Given entity not processable! Exception: ", ex);

        Map<String, Object> body = generateErrorResponse(
            "Given entity not processable!", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
        log.error("Unknown error! Exception: ", ex);

        Map<String, Object> body = generateErrorResponse(
            "Unknown error!",
            ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> generateErrorResponse(String errorMessage, String exMessage) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", errorMessage);
        body.put("error", exMessage);
        body.put("timestamp", LocalDateTime.now());
        return body;
    }

}
