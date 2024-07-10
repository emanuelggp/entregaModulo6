package cl.alke.AlkeWallet.service;

import cl.alke.AlkeWallet.dao.TransaccionDao;
import cl.alke.AlkeWallet.model.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {
    @Autowired
    TransaccionDao transaccionDao;

    public void saveTransaccion(Transaccion transaccion) {
        transaccionDao.save(transaccion);
    }
}
