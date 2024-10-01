package com.example.Nutriologa.Analia.Roman.controller;

import com.example.Nutriologa.Analia.Roman.model.Direccion;

import com.example.Nutriologa.Analia.Roman.model.Servicio;
import com.example.Nutriologa.Analia.Roman.model.Usuario;
import com.example.Nutriologa.Analia.Roman.repository.UsuarioRepository;
import com.example.Nutriologa.Analia.Roman.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/crear")
    public ResponseEntity<Direccion> crearDireccion(@RequestBody Direccion direccion, Authentication authentication) {
        String correoUsuario = authentication.getName();
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Aquí es donde tienes que usar la instancia del objeto, no la clase.
        direccion.setUsuario(usuario);  // Cambia esto para usar la instancia 'direccion'

        Direccion nuevaDireccion = direccionService.guardarServicio(direccion);  // Guarda la dirección con el usuario asociado
        return ResponseEntity.ok(nuevaDireccion);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Map<String, Object>>> listarDirecciones(Authentication authentication) {
        String correoUsuario = authentication.getName();
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Direccion> direcciones = direccionService.obtenerServiciosPorUsuario(usuario);  // Cambia el nombre del método si es necesario

        List<Map<String, Object>> respuesta = new ArrayList<>();
        for (Direccion direccion : direcciones) {
            Map<String, Object> direccionConDetalles = new HashMap<>();
            direccionConDetalles.put("id", direccion.getId());
            direccionConDetalles.put("direccion", direccion.getDireccion());
            respuesta.add(direccionConDetalles);
        }

        return ResponseEntity.ok(respuesta);
    }



    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        direccionService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Direccion> actualizarServicio(@PathVariable Long id, @RequestBody Direccion servicioActualizado) {
        Direccion direccion = direccionService.obtenerServicioPorId(id);

        direccion.setDireccion(servicioActualizado.getDireccion());


        Direccion servicioActualizadoResponse = direccionService.guardarServicio(direccion);
        return ResponseEntity.ok(servicioActualizadoResponse);
    }
}