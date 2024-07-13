package cl.alke.AlkeWallet.controller;

import cl.alke.AlkeWallet.model.Transferencia;
import cl.alke.AlkeWallet.model.Usuario;
import cl.alke.AlkeWallet.service.TransferenciaService;
import cl.alke.AlkeWallet.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public String mostrarTransferencia(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        model.addAttribute("usuario", usuarioActual);

        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        // Filtrar el usuario actual de la lista de usuarios
        usuarios = usuarios.stream()
                .filter(usuario -> !usuario.getEmail().equals(usuarioActual.getEmail()))
                .collect(Collectors.toList());

        model.addAttribute("usuarios", usuarios);
        Transferencia transferencia = new Transferencia();
        // Inicializar el campo 'recepcion' a null
        transferencia.setRecepcion(null);
        model.addAttribute("transferencia", transferencia);

        return "transferencia";
    }

    @PostMapping
    public String realizarTransferencia(int monto, String recepcion, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        Usuario usuarioDestino = usuarioService.findByEmail(recepcion);

        if (usuarioActual != null && usuarioDestino != null) {
            if (usuarioActual.getBalance() >= monto) {
                // Crear la transferencia
                Transferencia transferencia = new Transferencia();
                transferencia.setEnvio(usuarioActual);
                transferencia.setRecepcion(usuarioDestino);
                transferencia.setMonto(monto);
                transferencia.setFecha(new Date());
                transferenciaService.saveTransferencia(transferencia);

                // Actualizar los balances
                usuarioActual.setBalance(usuarioActual.getBalance() - monto);
                usuarioDestino.setBalance(usuarioDestino.getBalance() + monto);
                usuarioService.saveUsuario(usuarioActual);
                usuarioService.saveUsuario(usuarioDestino);

                redirectAttributes.addFlashAttribute("mensajeExito", "La transferencia fue realizada correctamente.");
                return "redirect:/transferencia";
            } else {
                model.addAttribute("error", "Saldo insuficiente");
                return "transferencia";
            }
        } else {
            model.addAttribute("error", "Usuario destino no encontrado");
            return "transferencia";
        }
    }
}
