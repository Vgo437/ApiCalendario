package calendario.api.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import calendario.api.dominio.entidades.Usuario;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario = ?1")
    Usuario obtener(String usuario);

    @Query("SELECT u FROM Usuario u WHERE u.usuario=?1 AND u.clave=?2")
    Usuario login(String usuario, String clave);
}
