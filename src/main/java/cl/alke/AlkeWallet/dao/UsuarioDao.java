package cl.alke.AlkeWallet.dao;

import cl.alke.AlkeWallet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    List<Usuario> findAll();

    Usuario findByEmail(String email);

    Optional<Usuario> findById(Long idUsuario);
}
