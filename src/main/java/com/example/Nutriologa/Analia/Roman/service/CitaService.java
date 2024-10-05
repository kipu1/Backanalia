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
        // Convertir la fecha al rango de tiempo para el día completo
        LocalDateTime startOfDay = fecha.atStartOfDay(); // Inicio del día (00:00)
        LocalDateTime endOfDay = fecha.atTime(23, 59, 59); // Fin del día (23:59)

        // Buscar las citas ocupadas en ese día
        List<Cita> citas = citaRepository.findByFechaHoraBetween(startOfDay, endOfDay);

        // Retornar la lista de horarios ocupados
        return citas.stream()
                .map(Cita::getFechaHora) // Extraer el campo fechaHora de cada cita
                .collect(Collectors.toList());
    }
    // Otros métodos según sea necesario (e.g., encontrar por ID, eliminar, etc.)
}