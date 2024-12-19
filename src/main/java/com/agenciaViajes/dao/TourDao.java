package com.agenciaViajes.dao;

import com.agenciaViajes.domain.Tour;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourDao extends JpaRepository <Tour,Long> {
    public List<Tour> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup); //query 1 de consultadas ampliadas (sencillas)
    
    //Ejemplo de método utilizando Consultas con JPQL
    @Query(value="SELECT a FROM Tour a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Tour> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
    
    //Ejemplo de método utilizando Consultas con SQL nativo
    @Query(nativeQuery=true,
            value="SELECT * FROM tour where tour.precio BETWEEN :precioInf AND :precioSup ORDER BY tour.descripcion ASC")
    public List<Tour> metodoNativo(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
}