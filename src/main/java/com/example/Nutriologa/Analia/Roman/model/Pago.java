package com.example.Nutriologa.Analia.Roman.model;
import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double monto; // Monto de la cita

    @Column(nullable = false)
    private String estado; // Estado del pago (ej. "PENDIENTE", "COMPLETADO")

    @Column(nullable = true)
    private String referencia; // Referencia de la transacción en PayPhone

    @OneToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;

    // Constructor vacío requerido por JPA
    public Pago() {
    }

    // Constructor con parámetros
    public Pago(Double monto, String estado, String referencia, Cita cita) {
        this.monto = monto;
        this.estado = estado;
        this.referencia = referencia;
        this.cita = cita;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}