/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agenciaViajes.controller;

import com.agenciaViajes.domain.Tour;
import com.agenciaViajes.service.CategoriaService;
import com.agenciaViajes.service.impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.agenciaViajes.service.TourService;

@Controller
@Slf4j
@RequestMapping("/tour")
public class TourController {
    
    @Autowired
    private TourService tourService;
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var tours = tourService.getTours(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("tours", tours);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalTours", tours.size());
        return "/tour/listado";
    }
    
    @GetMapping("/nuevo")
    public String tourNuevo(Tour tour) {
        return "/tour/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String tourGuardar(Tour tour,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            tourService.save(tour);
            tour.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "tour", 
                            tour.getIdTour()));
        }
        tourService.save(tour);
        return "redirect:/tour/listado";
    }

    @GetMapping("/eliminar/{idTour}")
    public String tourEliminar(Tour tour) {
        tourService.delete(tour);
        return "redirect:/tour/listado";
    }

    @GetMapping("/modificar/{idTour}")
    public String tourModificar(Tour tour, Model model) {
        tour = tourService.getTour(tour);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("tour", tour);
        model.addAttribute("categorias", categorias);
        return "/tour/modifica";
    }
    
}

