package com.vecinal.alertas.controller;

import com.vecinal.alertas.model.Reporte;
import com.vecinal.alertas.repository.ReporteRepository;
import com.vecinal.alertas.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String listarReportes(Model model) {
        model.addAttribute("reportes", reporteRepository.findAll());
        return "index";
    }

    @GetMapping("/nuevo")
    public String formularioReporte(Model model) {
        model.addAttribute("reporte", new Reporte());
        return "formulario";
    }
@PostMapping("/guardar")
    public String guardarReporte(@ModelAttribute Reporte reporte, 
                                 @RequestParam("imagenArchivo") MultipartFile archivo) {
        try {
            if (!archivo.isEmpty()) {
                String urlFoto = cloudinaryService.subirImagen(archivo);
                reporte.setFotoUrl(urlFoto);
            }
            reporteRepository.save(reporte);
        } catch (Exception e) { // <-- CAMBIADO A Exception PARA EVITAR EL ERROR DE COMPILACIÓN
            e.printStackTrace();
        }
        return "redirect:/reportes?exito"; // <-- Agrégale el '?exito' aquí
    }
}