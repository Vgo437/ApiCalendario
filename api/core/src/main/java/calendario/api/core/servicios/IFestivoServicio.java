package calendario.api.core.servicios;

import java.util.List;

import calendario.api.dominio.dtos.FestivoDto;

public interface IFestivoServicio {

    List<FestivoDto> obtener(int anio);
}
