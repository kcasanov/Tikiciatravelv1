/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.agenciaViajes.dao;

import com.agenciaViajes.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kenda
 */
public interface VentaDao extends JpaRepository <Venta, Long>{
    
}
