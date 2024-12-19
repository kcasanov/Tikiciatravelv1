/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.agenciaViajes.service;

import com.agenciaViajes.domain.Tour;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourService {
    
    // Se obtiene un listado de Tour en un List
    public List<Tour> getTours(boolean activos);
 
    // Se obtiene un Tour, a partir del id de un tour
    public Tour getTour(Tour tour);
    
    // Se inserta un nuevo tour si el id del tour esta vacío
    // Se actualiza un tour si el id del tour NO esta vacío
    public void save(Tour tour);
    
    // Se elimina el tour que tiene el id pasado por parámetro
    public void delete(Tour tour);
    
    public List<Tour> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup); //query 1 de consultadas ampliadas (sencillas)
    
     @Query(value="SELECT a FROM tour a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Tour> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
    
    @Query(nativeQuery=true,
            value="SELECT * FROM tour where tour.precio BETWEEN :precioInf AND :precioSup ORDER BY tour.descripcion ASC")
    public List<Tour> metodoNativo(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
    
}
