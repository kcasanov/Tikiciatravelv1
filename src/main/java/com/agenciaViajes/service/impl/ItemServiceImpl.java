/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agenciaViajes.service.impl;


import com.agenciaViajes.dao.FacturaDao;
import com.agenciaViajes.dao.TourDao;
import com.agenciaViajes.dao.UsuarioDao;
import com.agenciaViajes.dao.VentaDao;
import com.agenciaViajes.domain.Factura;
import com.agenciaViajes.domain.Item;
import com.agenciaViajes.domain.Usuario;
import com.agenciaViajes.domain.Venta;
import com.agenciaViajes.service.ItemService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author kenda
 */

@Service

public class ItemServiceImpl implements ItemService {
    @Autowired
    private HttpSession session;
    
    @Override
     public List<Item> gets(){
         @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        return listaItems;
     }
     
     @Override
     public Item get(Item item){
         @SuppressWarnings("unchecked")
         List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            for (Item i : listaItems) {
                if (Objects.equals(i.getIdTour(), item.getIdTour())) {
                    return i;
                }
            }
        }
        return null;
     }
     
     @Override
     public void delete(Item item){
         @SuppressWarnings("unchecked")
                 
                 
         List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            var posicion = -1;
            var existe = false;
            for (var i : listaItems) {
                posicion++;
                if (Objects.equals(i.getIdTour(), item.getIdTour())) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                listaItems.remove(posicion);
                session.setAttribute("listaItems", listaItems);
            }
        }
     }
     
@Override
    public void save(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems == null) {
            listaItems = new ArrayList<>();
        }
        var existe = false;
        for (var i : listaItems) {
            if (Objects.equals(i.getIdTour(), item.getIdTour())) {
                existe = true;
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
        session.setAttribute("listaItems", listaItems);
    }

    @Override
    public void update(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            for (var i : listaItems) {
                if (Objects.equals(i.getIdTour(), item.getIdTour())) {
                    i.setCantidad(item.getCantidad());
                    session.setAttribute("listaItems", listaItems);
                    break;
                }
            }
        }
    }
    
    
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private TourDao tourDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;

    @Override
    public void facturar() {
        //Se debe recuperar el usuario autenticado y obtener su idUsuario
        String username = "";
        var principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            if (principal != null) {
                username = principal.toString();
            }
        }

        if (username.isBlank()) {
            System.out.println("username en blanco...");
            return;
        }

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            System.out.println("Usuario no existe en usuarios...");
            return;
        }

        //Se debe registrar la factura incluyendo el usuario
        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);

        //Se debe registrar las ventas de cada tour -actualizando existencias-
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            double total = 0;
            for (Item i : listaItems) {
                var tour = tourDao.getReferenceById(i.getIdTour());
                if (tour.getExistencias() >= i.getCantidad()) {
                    Venta venta = new Venta(factura.getIdFactura(),
                            i.getIdTour(),
                            i.getPrecio(),
                            i.getCantidad());
                    ventaDao.save(venta);
                    tour.setExistencias(tour.getExistencias() - i.getCantidad());
                    tourDao.save(tour);
                    total += i.getCantidad()* i.getPrecio();
                }
            }

            //Se debe registrar el total de la venta en la factura
            factura.setTotal(total);
            facturaDao.save(factura);

            //Se debe limpiar el carrito la lista...
            listaItems.clear();
        }
    }

    @Override
    public double getTotal() {
        //Se debe registrar las ventas de cada tour -actualizando existencias-
        @SuppressWarnings("unchecked")
        var listaItems = (List<Item>) session.getAttribute("listaItems");
        double total = 0;
        if (listaItems != null) {
            for (Item i : listaItems) {
                total += i.getCantidad()* i.getPrecio();
            }
        }
        return total;
    }
     
}
