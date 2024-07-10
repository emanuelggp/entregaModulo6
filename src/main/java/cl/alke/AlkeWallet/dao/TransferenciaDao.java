package cl.alke.AlkeWallet.dao;

import cl.alke.AlkeWallet.model.Transferencia;
import cl.alke.AlkeWallet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaDao extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByEnvioOrRecepcion(Usuario envio, Usuario recepcion);
}
