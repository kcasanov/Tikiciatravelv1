/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agenciaViajes.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "tour")
public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tour")
    private Long idTour;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    private String detalle;
    private Double precio;
    private int existencias;
    //private Long idCategoria; => reemplazzar con el ManyToOne
    
    @ManyToOne
    @JoinColumn(name="id_categoria")
    Categoria categoria;
    
    
    
    public Tour() {
    }

    public Tour(String tour, boolean activo) {
        this.descripcion = tour;
        this.activo = activo;
    }
}
