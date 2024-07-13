package cl.alke.AlkeWallet.dao;

import cl.alke.AlkeWallet.model.Transferencia;
import cl.alke.AlkeWallet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransferenciaDao extends JpaRepository<Transferencia, Long> {
    @Query("SELECT t FROM Transferencia t WHERE t.envio = :usuario OR t.recepcion = :usuario")
    List<Transferencia> findByEnvioRecepcion(@Param("usuario") Usuario usuario);


}
