/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agenciaViajes.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author kenda
 */
@Data
@EqualsAndHashCode(callSuper=false)

public class Item extends Tour{
    private int cantidad; //Almacenar la cantidad de items de un Tour

    public Item() {
    }

    public Item(Tour tour) {
        super.setIdTour(tour.getIdTour());
        super.setCategoria(tour.getCategoria());
        super.setDescripcion(tour.getDescripcion());
        super.setDetalle(tour.getDetalle());
        super.setPrecio(tour.getPrecio());
        super.setExistencias(tour.getExistencias());
        super.setActivo(tour.isActivo());
        super.setRutaImagen(tour.getRutaImagen());
        this.cantidad = 0;
    }
}
