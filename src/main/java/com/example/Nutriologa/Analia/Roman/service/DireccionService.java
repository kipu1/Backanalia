package com.example.Nutriologa.Analia.Roman.service;

import com.example.Nutriologa.Analia.Roman.model.Direccion;
import com.example.Nutriologa.Analia.Roman.model.Servicio;
import com.example.Nutriologa.Analia.Roman.model.Usuario;
import com.example.Nutriologa.Analia.Roman.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public Direccion guardarServicio(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    public List<Direccion> obtenerServiciosPorUsuario(Usuario usuario) {
        return direccionRepository.findByUsuario(usuario);
    }
    public List<Direccion> obtenerTodasLasDirecciones() {
        return direccionRepository.findAll();  // Este método devuelve todas las direcciones de la base de datos
    }
    public void eliminarServicio(Long id) {
        direccionRepository.deleteById(id);
    }

    public Direccion obtenerServicioPorId(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    }
}
