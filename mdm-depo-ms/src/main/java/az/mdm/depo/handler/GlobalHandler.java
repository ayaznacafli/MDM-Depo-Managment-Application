package az.mdm.depo.handler;

import az.mdm.depo.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Calendar;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ObjectError error = ex.getBindingResult().getAllErrors().stream().findAny().orElse(null);
        FieldError fieldError = (FieldError) error;
        ErrorDTO errorDto = new ErrorDTO(HttpStatus.BAD_REQUEST.value(),
                fieldError.getDefaultMessage(),
                fieldError.getDefaultMessage(),
                Calendar.getInstance());
        log.warn(fieldError.getDefaultMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuantityNotFoundException.class)
    public ResponseEntity handleException(QuantityNotFoundException ex)
            throws IOException {
        ErrorDTO errorDto = new ErrorDTO(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage(),
                Calendar.getInstance());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InstrumentNotFoundException.class)
    public ResponseEntity handleException(InstrumentNotFoundException ex)
            throws IOException {
        ErrorDTO errorDto = new ErrorDTO(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage(),
                Calendar.getInstance());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
