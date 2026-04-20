package calendario.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import calendario.api.core.servicios.IFestivoServicio;
import calendario.api.dominio.dtos.FestivoDto;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    @Autowired
    private IFestivoServicio servicio;

    @GetMapping(value = "/obtener/{anio}")
    public List<FestivoDto> obtener(@PathVariable int anio) {
        return servicio.obtener(anio);
    }

}
