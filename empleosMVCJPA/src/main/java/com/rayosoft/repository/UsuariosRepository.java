package com.rayosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rayosoft.model.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
    List<Usuario> findByFechaRegistroNotNull();
    @Modifying
    @Query("UPDATE Usuario u SET u.estatus=0 WHERE u.id= :paramIdUsuario")
    int lock(@Param("paramIdUsuario") int idUsuario);

    @Modifying
    @Query("UPDATE Usuario u SET u.estatus=1 WHERE u.id= :paramIdUsuario")
    int unlock(@Param("paramIdUsuario") int idUsuario);
}
