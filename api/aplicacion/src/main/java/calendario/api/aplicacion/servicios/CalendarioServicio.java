package calendario.api.aplicacion.servicios;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calendario.api.core.integracion.IFestivoServicioExterno;
import calendario.api.core.servicios.ICalendarioServicio;
import calendario.api.dominio.dtos.FestivoDto;
import calendario.api.dominio.entidades.Calendario;
import calendario.api.dominio.entidades.Tipo;
import calendario.api.infraestructura.repositorios.ICalendarioRepositorio;
import calendario.api.infraestructura.repositorios.ITipoRepositorio;

@Service
public class CalendarioServicio implements ICalendarioServicio {

    private static final int ID_LABORAL = 1;
    private static final int ID_FIN_SEMANA = 2;
    private static final int ID_FESTIVO = 3;

    @Autowired
    private ICalendarioRepositorio repositorio;

    @Autowired
    private ITipoRepositorio repositorioTipo;

    @Autowired
    private IFestivoServicioExterno integracion;

    @Override
    public boolean generar(int anio) {
        try {
            List<FestivoDto> festivos = integracion.obtener(anio);
            Set<LocalDate> fechasFestivas = new HashSet<>();
            for (FestivoDto festivo : festivos) {
                fechasFestivas.add(LocalDate.parse(festivo.getFecha()));
            }

            Tipo tipoLaboral = repositorioTipo.findById(ID_LABORAL).orElseThrow();
            Tipo tipoFinSemana = repositorioTipo.findById(ID_FIN_SEMANA).orElseThrow();
            Tipo tipoFestivo = repositorioTipo.findById(ID_FESTIVO).orElseThrow();

            LocalDate desde = LocalDate.of(anio, 1, 1);
            LocalDate hasta = LocalDate.of(anio, 12, 31);

            repositorio.deleteAll(repositorio.listarPorPeriodo(desde, hasta));

            LocalDate fecha = desde;
            while (!fecha.isAfter(hasta)) {
                Calendario dia = new Calendario();
                dia.setFecha(fecha);
                dia.setDescripcion(nombreDiaSemana(fecha.getDayOfWeek()));

                if (fechasFestivas.contains(fecha)) {
                    dia.setTipo(tipoFestivo);
                } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    dia.setTipo(tipoFinSemana);
                } else {
                    dia.setTipo(tipoLaboral);
                }

                repositorio.save(dia);
                fecha = fecha.plusDays(1);
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Calendario> listar(int anio) {
        LocalDate desde = LocalDate.of(anio, 1, 1);
        LocalDate hasta = LocalDate.of(anio, 12, 31);
        return repositorio.listarPorPeriodo(desde, hasta);
    }

    private String nombreDiaSemana(DayOfWeek dia) {
        switch (dia) {
            case MONDAY: return "Lunes";
            case TUESDAY: return "Martes";
            case WEDNESDAY: return "Miercoles";
            case THURSDAY: return "Jueves";
            case FRIDAY: return "Viernes";
            case SATURDAY: return "Sabado";
            case SUNDAY: return "Domingo";
            default: return "";
        }
    }

}
