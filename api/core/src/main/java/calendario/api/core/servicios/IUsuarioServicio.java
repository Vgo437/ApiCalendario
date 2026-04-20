package calendario.api.core.servicios;

import calendario.api.dominio.dtos.UsuarioLoginDto;

public interface IUsuarioServicio {

    UsuarioLoginDto login(String nombreUsuario, String clave);
}
