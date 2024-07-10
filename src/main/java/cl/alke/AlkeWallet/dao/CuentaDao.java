package cl.alke.AlkeWallet.dao;

import cl.alke.AlkeWallet.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaDao extends JpaRepository<Cuenta, Long> {
}
