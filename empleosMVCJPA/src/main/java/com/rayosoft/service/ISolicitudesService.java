package com.rayosoft.service;

import com.rayosoft.model.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISolicitudesService {
    void guardar(Solicitud solicitud);
    void eliminar(int id);
    List<Solicitud> buscarTodas();
    Solicitud buscarPorId(Integer id);
    Page<Solicitud> buscarTodas(Pageable page);
}
