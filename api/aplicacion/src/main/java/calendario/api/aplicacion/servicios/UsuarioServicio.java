package calendario.api.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calendario.api.aplicacion.seguridad.SeguridadServicio;
import calendario.api.core.servicios.IUsuarioServicio;
import calendario.api.dominio.dtos.UsuarioLoginDto;
import calendario.api.dominio.entidades.Usuario;
import calendario.api.infraestructura.repositorios.IUsuarioRepositorio;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private SeguridadServicio servicioSeguridad;

    @Override
    public UsuarioLoginDto login(String nombreUsuario, String clave) {
        Usuario usuarioObtenido = repositorio.login(nombreUsuario, clave);
        UsuarioLoginDto userLoginResponseDto = new UsuarioLoginDto(usuarioObtenido);
        if (usuarioObtenido != null) {
            userLoginResponseDto.setToken(servicioSeguridad.generarToken(nombreUsuario));
        }
        return userLoginResponseDto;
    }

}
