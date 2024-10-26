package com.example.Nutriologa.Analia.Roman.controller;

import com.example.Nutriologa.Analia.Roman.model.Cita;
import com.example.Nutriologa.Analia.Roman.model.Pago;
import com.example.Nutriologa.Analia.Roman.service.CitaService;
import com.example.Nutriologa.Analia.Roman.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private CitaService citaService;

    // Endpoint para crear un pago asociado a una cita
    @PostMapping("/crear")
    public ResponseEntity<?> crearPago(@RequestBody Map<String, Object> datosPago) {
        try {
            Long idCita = Long.parseLong(datosPago.get("idCita").toString());
            Double monto = Double.parseDouble(datosPago.get("monto").toString());

            // Utilizar el método `obtenerCitaPorId` del servicio `CitaService`
            Cita cita = citaService.obtenerCitaPorId(idCita);
            if (cita == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cita no encontrada");
            }

            // Crear el pago asociado a la cita
            Pago pago = pagoService.crearPago(monto, cita);
            return ResponseEntity.ok(pago);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el pago");
        }
    }


    // Endpoint para actualizar el estado del pago
    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarPago(@RequestBody Map<String, Object> datosConfirmacion) {
        try {
            Long idPago = Long.parseLong(datosConfirmacion.get("idPago").toString());
            String estado = datosConfirmacion.get("estado").toString();
            String referencia = datosConfirmacion.get("referencia").toString();

            // Actualizar el estado del pago
            Pago pagoActualizado = pagoService.actualizarEstadoPago(idPago, estado, referencia);
            return ResponseEntity.ok(pagoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al confirmar el pago");
        }
    }
}