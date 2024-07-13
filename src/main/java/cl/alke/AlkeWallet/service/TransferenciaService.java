package cl.alke.AlkeWallet.service;

import cl.alke.AlkeWallet.dao.CuentaDao;
import cl.alke.AlkeWallet.dao.TransferenciaDao;
import cl.alke.AlkeWallet.model.Cuenta;
import cl.alke.AlkeWallet.model.Transferencia;
import cl.alke.AlkeWallet.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciaDao transferenciaDao;

    @Autowired
    CuentaDao cuentaDao;

    @Transactional
    public void saveTransferencia(Transferencia transferencia) {

        transferenciaDao.save(transferencia);
    }

    @Transactional
    public List<Transferencia> getTransferenciasByUsuario(Usuario usuario) {
        return transferenciaDao.findByEnvioRecepcion(usuario);
    }

    @Transactional
    public void realizarTransferencia(Transferencia transferencia) {
        // Validar que las cuentas de envío y recepción existen y tienen saldo suficiente
        Cuenta cuentaEnvio = cuentaDao.findById(transferencia.getEnvio().getIdUsuario()).orElseThrow(() -> new IllegalArgumentException("Cuenta de envío no encontrada"));
        Cuenta cuentaRecepcion = cuentaDao.findById(transferencia.getRecepcion().getIdUsuario()).orElseThrow(() -> new IllegalArgumentException("Cuenta de recepción no encontrada"));

        if (cuentaEnvio.getSaldo() < transferencia.getMonto()) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta de envío");
        }

        // Realizar la transferencia
        cuentaEnvio.setSaldo(cuentaEnvio.getSaldo() - transferencia.getMonto());
        cuentaRecepcion.setSaldo(cuentaRecepcion.getSaldo() + transferencia.getMonto());

        cuentaDao.save(cuentaEnvio);
        cuentaDao.save(cuentaRecepcion);

        // Guardar la transferencia
        transferencia.setFecha(new Date());
        transferenciaDao.save(transferencia);
    }
}
