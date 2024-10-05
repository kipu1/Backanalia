package com.example.Nutriologa.Analia.Roman.service;
import com.example.Nutriologa.Analia.Roman.model.Cita;
import com.example.Nutriologa.Analia.Roman.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;



    public Cita save(Cita cita) {
        return citaRepository.save(cita);
    }

    public List<Cita> findByAll() {return citaRepository.findAll();
    }
    public List<LocalDateTime> obtenerHorariosOcupados(LocalDate fecha) {
        // Obtener todas las citas en la fecha especificada
        List<Cita> citasEnFecha = citaRepository.findAll().stream()
                .filter(cita -> cita.getFechaHora().toLocalDate().equals(fecha))
                .collect(Collectors.toList());

        // Devolver los horarios ocupados (fecha y hora) de las citas en esa fecha
        return citasEnFecha.stream()
                .map(Cita::getFechaHora)
                .collect(Collectors.toList());
    }
    // Otros métodos según sea necesario (e.g., encontrar por ID, eliminar, etc.)
}