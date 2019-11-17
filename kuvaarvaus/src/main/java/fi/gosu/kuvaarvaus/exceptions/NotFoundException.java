package fi.gosu.kuvaarvaus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RuntimeException which return 404 httpstatus when throw it
 *
 * @author Ville Nupponen
 * @since 0.1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
}
