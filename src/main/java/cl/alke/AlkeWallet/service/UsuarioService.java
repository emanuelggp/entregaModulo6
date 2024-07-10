package cl.alke.AlkeWallet.service;

import cl.alke.AlkeWallet.dao.UsuarioDao;
import cl.alke.AlkeWallet.model.Cuenta;
import cl.alke.AlkeWallet.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    CuentaService cuentaService;

    public List<Usuario> getUsuario() {
        return usuarioDao.findAll();
    }
    //inicio de sesión
    public boolean validar(String email, String password) {
        Usuario usuario = usuarioDao.findByEmail(email);
        return usuario != null && usuario.getPassword().equals(password);
    }

    // Guardar usuario
    public void saveUsuario(Usuario usuario) {
        usuarioDao.save(usuario);

        // Crear una nueva cuenta para el usuario
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldo(0); // Saldo inicial 0
        cuenta.setUsuario(usuario); // Relación con el usuario
        cuentaService.saveCuenta(cuenta); // Guardar la cuenta asociada al usuario

        // Agregar la cuenta recién creada a la lista de cuentas del usuario
        usuario.addCuenta(cuenta);
        usuarioDao.save(usuario); // Actualizar el usuario con la nueva cuenta
    }

    @Transactional
    public Usuario findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }
    @Transactional
    public Usuario findByEmailWithCuentas(String email) {
        Usuario usuario = usuarioDao.findByEmail(email);
        if (usuario != null) {
            usuario.getCuentas().size();
        }
        return usuario;
    }

}
