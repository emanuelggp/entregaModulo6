package cl.alke.AlkeWallet.controller;

import cl.alke.AlkeWallet.model.Cuenta;
import cl.alke.AlkeWallet.model.Usuario;
import cl.alke.AlkeWallet.service.CuentaService;
import cl.alke.AlkeWallet.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public String showDeposito(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        usuarioActual = usuarioService.findByEmailWithCuentas(usuarioActual.getEmail());

        model.addAttribute("usuario", usuarioActual);
        return "deposito";
    }

    @PostMapping
    public String transaction(int monto, String action, Model model, HttpSession session) {
        // Obtener el usuario actual de la sesión
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        usuarioActual = usuarioService.findByEmailWithCuentas(usuarioActual.getEmail());

        if (usuarioActual != null) {
            Cuenta cuenta = usuarioActual.getCuentas().get(0); // Asumiendo que hay una cuenta

            if (action.equals("depositar")) {
                cuenta.setSaldo(cuenta.getSaldo() + monto);
            } else if (action.equals("retirar")) {
                if (cuenta.getSaldo() >= monto) {
                    cuenta.setSaldo(cuenta.getSaldo() - monto);
                } else {
                    model.addAttribute("error", "Saldo insuficiente");
                    return "deposito";
                }
            }
            cuentaService.saveCuenta(cuenta);
            usuarioActual.setBalance(usuarioActual.getBalance() + (action.equals("depositar") ? monto : -monto));
            usuarioService.saveUsuario(usuarioActual);
        }

        return "redirect:/deposito";
    }
}
