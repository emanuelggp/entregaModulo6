package cl.alke.AlkeWallet.controller;

import cl.alke.AlkeWallet.model.Usuario;
import cl.alke.AlkeWallet.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/")
    public String showLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }

    @PostMapping("/login")
    public String login(Usuario usuario, Model model, HttpSession session) {
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Password: " + usuario.getPassword());

        if (usuarioService.validar(usuario.getEmail(), usuario.getPassword())) {
            // Obtener el usuario desde la base de datos
            Usuario usuarioLogueado = usuarioService.findByEmail(usuario.getEmail());
            session.setAttribute("usuarioActual", usuarioLogueado);
            return "redirect:/inicio";
        } else {
            model.addAttribute("errorMessage", "Email o contraseña incorrectos.");
            return "login";
        }
    }
}
