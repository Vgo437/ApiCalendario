package calendario.api.infraestructura.integracion;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import calendario.api.core.integracion.IFestivoServicioExterno;
import calendario.api.dominio.dtos.FestivoDto;

@Service
public class FestivoServicioExterno implements IFestivoServicioExterno {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${servicioexterno.festivos.url}")
    private String urlBase;

    @Override
    public List<FestivoDto> obtener(int anio) {
        String url = urlBase + "/obtener/" + anio;

        ResponseEntity<FestivoDto[]> response = restTemplate.exchange(url, HttpMethod.GET, null, FestivoDto[].class);

        FestivoDto[] cuerpo = response.getBody();
        return cuerpo == null ? List.of() : Arrays.asList(cuerpo);
    }

}
