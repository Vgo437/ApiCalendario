package calendario.api.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calendario.api.core.integracion.IFestivoServicioExterno;
import calendario.api.core.servicios.IFestivoServicio;
import calendario.api.dominio.dtos.FestivoDto;

@Service
public class FestivoServicio implements IFestivoServicio {

    @Autowired
    private IFestivoServicioExterno integracion;

    @Override
    public List<FestivoDto> obtener(int anio) {
        return integracion.obtener(anio);
    }

}
