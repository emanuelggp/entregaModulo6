package cl.alke.AlkeWallet.service;

import cl.alke.AlkeWallet.dao.CuentaDao;
import cl.alke.AlkeWallet.model.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaService {
    @Autowired
    CuentaDao cuentaDao;

    @Transactional
    public void saveCuenta(Cuenta cuenta) {
        cuentaDao.save(cuenta);
    }

    @Transactional
    public Cuenta getCuentaById(Long idCuenta) {
        return cuentaDao.findById(idCuenta).orElse(null);
    }
}
