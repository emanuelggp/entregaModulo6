package cl.alke.AlkeWallet.dao;

import cl.alke.AlkeWallet.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionDao extends JpaRepository<Transaccion, Integer> {
}
