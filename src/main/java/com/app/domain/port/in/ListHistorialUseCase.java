package com.app.domain.port.in;

import com.app.domain.model.HistorialModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface ListHistorialUseCase {
    Page<HistorialModel> listarHistorial(Pageable pageable);
}
