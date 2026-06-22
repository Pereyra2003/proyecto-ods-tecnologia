package com.vecinal.alertas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Data
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String liderNombre;
    private String comunidad;
    private String tipoIncidente; 
    private String descripcion;
    private String fotoUrl;
    
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}