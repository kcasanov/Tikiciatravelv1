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
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author kenda
 */
@Data
@Entity
@Table(name="factura")

public class Factura implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_factura")
    private Long idFactura;
    private Long idUsuario;
    private Date fecha;
    private double total;
    private int estado;
    
    
    public Factura() {
    }

    public Factura(Long idUSuario) {
        this.idUsuario = idUSuario;
        this.fecha = Calendar.getInstance().getTime();
        this.estado=1;
    }
}
