package com.example.Nutriologa.Analia.Roman.service;

import com.example.Nutriologa.Analia.Roman.model.Cita;
import com.example.Nutriologa.Analia.Roman.model.Pago;
import com.example.Nutriologa.Analia.Roman.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    // Método para crear un pago
    public Pago crearPago(Double monto, Cita cita) {
        Pago pago = new Pago(monto, "PENDIENTE", null, cita);
        return pagoRepository.save(pago);
    }

    // Método para actualizar el estado del pago
    public Pago actualizarEstadoPago(Long idPago, String estado, String referencia) {
        Pago pago = pagoRepository.findById(idPago).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pago.setEstado(estado);
        pago.setReferencia(referencia);
        return pagoRepository.save(pago);
    }
}