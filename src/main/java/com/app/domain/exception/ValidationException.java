package com.app.domain.exception;


import com.app.infrastructure.restapi.metadata.Mensaje;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ValidationException extends RuntimeException {
    private final transient List<Mensaje> mensajes;

    public ValidationException(List<Mensaje> mensajes) {
        super(mensajes.stream().map(Mensaje::getMessage).collect(Collectors.joining(", ")));
        this.mensajes = mensajes;
    }

}
