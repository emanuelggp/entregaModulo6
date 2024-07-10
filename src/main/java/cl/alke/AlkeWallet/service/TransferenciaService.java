package cl.alke.AlkeWallet.service;

import cl.alke.AlkeWallet.dao.TransferenciaDao;
import cl.alke.AlkeWallet.model.Transferencia;
import cl.alke.AlkeWallet.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciaDao transferenciaDao;

    @Transactional
    public void saveTransferencia(Transferencia transferencia) {
        transferenciaDao.save(transferencia);
    }

    @Transactional
    public List<Transferencia> getTransferenciasByUsuario(Usuario usuario) {
        return transferenciaDao.findByEnvioOrRecepcion(usuario, usuario);
    }
}
