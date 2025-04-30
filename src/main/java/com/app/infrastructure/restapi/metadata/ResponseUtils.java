package com.app.infrastructure.restapi.metadata;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mateo Huancho
 * @version 1.0
 * @since 2025-04-28
 */
public class ResponseUtils {

    private ResponseUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ResponseEntity<ApiResponse> buildResponse(Object data,
                                                            String codeInfo,
                                                            String tipoOperacion,
                                                            String mensajeOperacion,
                                                            boolean esExitoso,
                                                            HttpStatus httpStatus) {

        ApiResponse response = new ApiResponse();
        ApiResponseMeta meta = new ApiResponseMeta();

        meta.setResult(esExitoso ? "OK" : "ERROR");
        meta.setMensajes(Collections.singletonList(new Mensaje(codeInfo, tipoOperacion, mensajeOperacion)));
        meta.setAtributos(Collections.emptyMap());
        meta.setCantidadRegistros(1);

        response.setMeta(meta);
        response.setData(data);

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<ApiResponse> buildResponse(
            List<?> dataList,
            Page<?> page,
            String codeInfo,
            String tipoOperacion,
            String mensajeOperacion,
            boolean esExitoso,
            HttpStatus httpStatus) {

        ApiResponse response = new ApiResponse();
        ApiResponseMeta meta = new ApiResponseMeta();

        meta.setResult(esExitoso ? "OK" : "ERROR");
        meta.setMensajes(Collections.singletonList(new Mensaje(codeInfo, tipoOperacion, mensajeOperacion)));
        meta.setAtributos(Collections.emptyMap());

        meta.setCantidadRegistros(dataList.size());
        meta.setCantidadRegistrosTotal((int) page.getTotalElements());
        meta.setTotalPages(page.getTotalPages());
        meta.setCurrentPage(page.getNumber());
        meta.setPageSize(page.getSize());

        response.setMeta(meta);
        response.setData(dataList);

        return new ResponseEntity<>(response, httpStatus);
    }

}