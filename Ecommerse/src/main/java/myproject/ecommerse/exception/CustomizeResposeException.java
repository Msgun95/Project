package myproject.ecommerse.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizeResposeException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception e,
                                                                  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);


    }
//    @ExceptionHandler(CustomerNotFoundException.class)
//    public final ResponseEntity<ErrorDetails> handleCustomerNotFound(CustomerNotFoundException e,
//                                                                    WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//
//
//    }
    @ExceptionHandler(AddressNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleAddressNotFound(AddressNotFoundException e,
                                                                    WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);


    }
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
//            WebRequest request) {
//
//        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
//                "Total Erros: "+ ex.getErrorCount() +" First Error " +
//                        ex.getFieldError().getDefaultMessage(),
//                request.getDescription(false));
//        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
//
//
//    }

}
