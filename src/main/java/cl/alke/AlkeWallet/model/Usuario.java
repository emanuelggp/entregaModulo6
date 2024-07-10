package cl.alke.AlkeWallet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private int balance;

    // Relación con cuentas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cuenta> cuentas = new ArrayList<>();

    public void addCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        cuenta.setUsuario(this);
    }

    // Relación con transacciones
    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL)
    private List<Transaccion> envio;

    @OneToMany(mappedBy = "recepcion", cascade = CascadeType.ALL)
    private List<Transaccion> recepcion;

}
