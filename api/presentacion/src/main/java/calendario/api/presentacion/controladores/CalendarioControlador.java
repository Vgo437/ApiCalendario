package calendario.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import calendario.api.core.servicios.ICalendarioServicio;
import calendario.api.dominio.entidades.Calendario;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioControlador {

    @Autowired
    private ICalendarioServicio servicio;

    @GetMapping(value = "/generar/{anio}")
    public boolean generar(@PathVariable int anio) {
        return servicio.generar(anio);
    }

    @GetMapping(value = "/listar/{anio}")
    public List<Calendario> listar(@PathVariable int anio) {
        return servicio.listar(anio);
    }

}
