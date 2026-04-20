package calendario.api.core.integracion;

import java.util.List;

import calendario.api.dominio.dtos.FestivoDto;

public interface IFestivoServicioExterno {

    List<FestivoDto> obtener(int anio);
}
