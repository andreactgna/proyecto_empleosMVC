package com.rayosoft.service.db;

import com.rayosoft.model.Solicitud;
import com.rayosoft.repository.SolicitudesRepository;
import com.rayosoft.service.ISolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudesServiceJPA implements ISolicitudesService {

    @Autowired
    private SolicitudesRepository solicitudRepo;

    @Override
    public void guardar(Solicitud solicitud) {
        solicitudRepo.save(solicitud);
    }

    @Override
    public void eliminar(int id) {
        solicitudRepo.deleteById(id);
    }

    @Override
    public List<Solicitud> buscarTodas() {
        return solicitudRepo.findAll();
    }

    @Override
    public Solicitud buscarPorId(Integer id) {
        Optional<Solicitud> optional= solicitudRepo.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<Solicitud> buscarTodas(Pageable page) {
        return solicitudRepo.findAll(page);
    }
}
