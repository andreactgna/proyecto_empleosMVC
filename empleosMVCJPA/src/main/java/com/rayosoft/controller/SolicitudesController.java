package com.rayosoft.controller;

import com.rayosoft.model.Solicitud;
import com.rayosoft.model.Usuario;
import com.rayosoft.model.Vacante;
import com.rayosoft.service.ISolicitudesService;
import com.rayosoft.service.IUsuariosService;
import com.rayosoft.service.IVacantesService;
import com.rayosoft.util.Utileria;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/solicitudes")
public class SolicitudesController {

    @Value("${empleosapp.ruta.imagenes}")
    private String ruta;
    @Autowired
    private IVacantesService serviceVacantes;

    @Autowired
    private IUsuariosService serviceUsuarios;

    @Autowired
    private ISolicitudesService serviceSolicitudes;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Solicitud> lista = serviceSolicitudes.buscarTodas();
        model.addAttribute("solicitudes", lista);
        return "solicitudes/listSolicitudes";
    }

    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Solicitud> lista = serviceSolicitudes.buscarTodas(page);
        model.addAttribute("solicitudes", lista);
        return "solicitudes/listSolicitudes";
    }

    @GetMapping("/create/{id}")
    public String crear(Solicitud solicitud, @PathVariable Integer idVacante, Model model){
        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "solicitudes/formSolicitud";
    }

    @GetMapping("/delete/{id}")
    public String borrar(Solicitud solicitud, @PathVariable Integer idVacante, RedirectAttributes attributes){
        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
        attributes.addFlashAttribute("msg","La Solicitud fue eliminada");
        return "redirect:/solicitudes/indexPaginate";
    }

    @PostMapping("/save")
    public String guardar(Solicitud solicitud,
                          BindingResult result,
                          Model model,
                          HttpSession session,
                          RedirectAttributes attributes,
                          Authentication authentication,
                          @RequestParam("archivoCV") MultipartFile multiPart) {
        String username = authentication.getName();
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "solicitudes/formSolicitud";
        }

        if (!multiPart.isEmpty()) {
            String nombreArchivo = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreArchivo != null) { // La imagen si se subio
                // Procesamos la variable nombreImagen
                solicitud.setArchivo(nombreArchivo);
            }
        }
        Usuario usuario = serviceUsuarios.buscarPorUsername(username);
        solicitud.setUsuario(usuario);
        try {
            serviceSolicitudes.guardar(solicitud);
            attributes.addFlashAttribute("msg", "Gracias por enviar tu Solicitud");
        }catch (Exception e){
            attributes.addFlashAttribute("msg", "Solicitud Duplicada");
        }
        return "redirect:/";
    }
}
