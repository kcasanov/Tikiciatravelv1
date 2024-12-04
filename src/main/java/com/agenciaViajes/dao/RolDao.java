/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.agenciaViajes.dao;

import com.agenciaViajes.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kenda
 */
public interface RolDao extends JpaRepository<Rol, Long> {
    
}
