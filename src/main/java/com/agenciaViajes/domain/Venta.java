/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agenciaViajes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author kenda
 */

@Data
@Entity
@Table(name="venta")



public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Long idVenta;
    private Long idFactura;
    private Long idTour;    
    private double precio;
    private int cantidad; 

    public Venta() {
    }

    
    
    public Venta(Long idFactura, Long idTour, double precio, int cantidad) {
        this.idFactura = idFactura;
        this.idTour = idTour;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}