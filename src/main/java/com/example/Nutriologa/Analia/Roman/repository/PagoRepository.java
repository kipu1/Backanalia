package com.example.Nutriologa.Analia.Roman.repository;

import com.example.Nutriologa.Analia.Roman.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}