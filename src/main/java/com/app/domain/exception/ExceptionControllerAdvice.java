package com.app.domain.exception;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import com.app.infrastructure.restapi.metadata.ApiResponse;
import com.app.infrastructure.restapi.metadata.ApiResponseMeta;
import com.app.infrastructure.restapi.metadata.Mensaje;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import static com.app.domain.constants.Constants.ERROR;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAllUncaughtException(Exception ex, WebRequest request) {
        Mensaje mensaje = new Mensaje("500", ERROR, "INTERNAL SERVER ERROR: " + ex.getLocalizedMessage() + " - ");
        return buildErrorResponse(Collections.singletonList(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        Mensaje mensaje = new Mensaje("403", ERROR, "ERROR PERMISO");
        return buildErrorResponse(Collections.singletonList(mensaje), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ NoHandlerFoundException.class, NoResourceFoundException.class })
    public ResponseEntity<ApiResponse> handleNotFoundExceptions(Exception ex) {
        Mensaje mensaje = new Mensaje("404", "ERROR",
                ex.getLocalizedMessage());
        return buildErrorResponse(Collections.singletonList(mensaje), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest request) {
        Mensaje mensaje = new Mensaje("400", ERROR, "BAD_REQUEST");
        return buildErrorResponse(Collections.singletonList(mensaje), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return buildErrorResponse(
                Collections.singletonList(new Mensaje("404", ERROR, ex.getLocalizedMessage())),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
            WebRequest request) {
        Mensaje mensaje = new Mensaje("400", ERROR, "INVALIDO BODY");
        return buildErrorResponse(Collections.singletonList(mensaje), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
        return buildErrorResponse(ex.getMensajes(), HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ApiResponse> buildErrorResponse(List<Mensaje> mensajes, HttpStatus status) {
        ApiResponse response = new ApiResponse();
        ApiResponseMeta meta = new ApiResponseMeta();
        meta.setResult("FALLO");
        meta.setMensajes(mensajes);
        meta.setAtributos(new HashMap<>());
        response.setMeta(meta);
        response.setData(null);

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Mensaje> mensajes = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Mensaje("VALIDATION_ERROR", ERROR, error.getField() + ": " + error.getDefaultMessage()))
                .toList();

        ApiResponseMeta meta = new ApiResponseMeta();
        meta.setResult(ERROR);
        meta.setMensajes(mensajes);

        ApiResponse response = new ApiResponse();
        response.setMeta(meta);
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
