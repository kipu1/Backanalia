package com.example.Nutriologa.Analia.Roman.repository;

import com.example.Nutriologa.Analia.Roman.model.Direccion;
import com.example.Nutriologa.Analia.Roman.model.Servicio;
import com.example.Nutriologa.Analia.Roman.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    List<Direccion> findByUsuario(Usuario usuario);

}
