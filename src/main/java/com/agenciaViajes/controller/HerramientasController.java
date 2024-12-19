package com.agenciaViajes.controller;

import com.agenciaViajes.domain.Categoria;
import com.agenciaViajes.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.agenciaViajes.service.TourService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Herramientas")
public class HerramientasController {

    @Autowired
    private TourService tourService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var tours = tourService.getTours(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("tours", tours);
        model.addAttribute("totaltours", tours.size());
        model.addAttribute("categorias", categorias);
        return "/Herramientas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        var tours = categoriaService.getCategoria(categoria).getTours(); //asociacion entre tour + categoria 
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("tours", tours);
        model.addAttribute("totalTours", tours.size());
        model.addAttribute("categorias", categorias);
        return "/Herramientas/listado";
    }
    
    //Los métodos siguientes son para la prueba de consultas ampliadas
    @GetMapping("/listado2")
    public String listado2(Model model) {
        var tours = tourService.getTours(false);
        model.addAttribute("tours", tours);
        return "/Herramientas/listado2";
    }
    
    @PostMapping("/query2")
    public String consultaQuery2(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, Model model) {
        var tours = tourService.metodoJPQL(precioInf, precioSup);
        model.addAttribute("tours", tours);        
        model.addAttribute("totalTours", tours.size());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/Herramientas/listado2";
    }

}