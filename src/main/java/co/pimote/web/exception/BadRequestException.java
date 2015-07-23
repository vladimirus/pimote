package co.pimote.web.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to represent 400 errors for JSON.
 */
@ResponseStatus(value= BAD_REQUEST, reason="Bad request")
public class BadRequestException extends RuntimeException {
}
