package cl.alke.AlkeWallet.controller;

import cl.alke.AlkeWallet.model.Transferencia;
import cl.alke.AlkeWallet.model.Usuario;
import cl.alke.AlkeWallet.service.TransferenciaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public String showHistorial(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        if (usuarioActual != null) {
            List<Transferencia> transferencias = transferenciaService.getTransferenciasByUsuario(usuarioActual);
            model.addAttribute("transferencias", transferencias);

        }
        return "historial";
    }
}

