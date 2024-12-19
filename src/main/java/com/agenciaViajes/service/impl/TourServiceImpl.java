package com.agenciaViajes.service.impl;

import com.agenciaViajes.domain.Tour;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.agenciaViajes.dao.TourDao;
import com.agenciaViajes.service.TourService;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourDao tourDao;

    @Override
    @Transactional(readOnly = true)
    public List<Tour> getTours(boolean activos) {
        var lista = tourDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Tour getTour(Tour tour) {
        return tourDao.findById(tour.getIdTour()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Tour tour) {
        tourDao.save(tour);
    }

    @Override
    @Transactional
    public void delete(Tour tour) {
        tourDao.delete(tour);
    }

    // Lista de tour con precio entre ordendados por descripción ConsultaAmpliada
    @Transactional(readOnly = true)
    public List<Tour> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup) {
        return tourDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tour> metodoJPQL(double precioInf, double precioSup) {
        return tourDao.metodoJPQL(precioInf, precioSup);
    }

    @Transactional(readOnly = true)
    public List<Tour> metodoNativo(double precioInf, double precioSup) {
        return tourDao.metodoNativo(precioInf, precioSup);
    }
}
