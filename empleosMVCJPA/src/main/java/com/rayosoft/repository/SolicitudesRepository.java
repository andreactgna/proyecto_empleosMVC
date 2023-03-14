package com.rayosoft.repository;

import com.rayosoft.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudesRepository extends JpaRepository<Solicitud,Integer> {
}
