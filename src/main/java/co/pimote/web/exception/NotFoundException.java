package co.pimote.web.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to represent 404 errors for JSON.
 */
@ResponseStatus(value= NOT_FOUND, reason="Not found")
public class NotFoundException extends RuntimeException {
}
